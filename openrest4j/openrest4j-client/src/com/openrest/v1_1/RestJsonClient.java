package com.openrest.v1_1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * A client for a RESTful web-service that supports JSON representations.
 * @author DL
 */
public class RestJsonClient {
    private static final ObjectMapper mapper = new ObjectMapper();
    
    private final Integer connectTimeout;
    private final Integer readTimeout;

    public RestJsonClient(Integer connectTimeout, Integer readTimeout) {
    	this.connectTimeout = connectTimeout;
    	this.readTimeout = readTimeout;
    }
    
    public RestJsonClient() {
    	this(null, null);
    }

    public <T> T get(URL url, TypeReference<T> responseType) throws IOException {
        return go(url, "GET", null, responseType);
    }

    public <T> T put(URL url, Object requestObj, TypeReference<T> responseType) throws IOException {
        return go(url, "PUT", requestObj, responseType);
    }

    public <T> T post(URL url, Object requestObj, TypeReference<T> responseType) throws IOException {
        return go(url, "POST", requestObj, responseType);
    }

    public <T> T delete(URL url, TypeReference<T> responseType) throws IOException {
        return go(url, "DELETE", null, responseType);
    }

    public <T> Image getImage(URL url, TypeReference<T> responseType) throws IOException {
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        if (connectTimeout != null) {
        	conn.setConnectTimeout(connectTimeout.intValue());
        }
        if (readTimeout != null) {
        	conn.setReadTimeout(readTimeout);
        }
        conn.setRequestMethod("GET");
        conn.connect();

        return parseImageResponse(conn, responseType);

        // Avoid conn.disconnect() so the underlying socket can be reused
    }

    public <T> T put(URL url, String imageFilename, Image image, TypeReference<T> responseType) throws IOException {
        final String boundary = Long.toHexString(System.currentTimeMillis());

        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        if (connectTimeout != null) {
        	conn.setConnectTimeout(connectTimeout.intValue());
        }
        if (readTimeout != null) {
        	conn.setReadTimeout(readTimeout);
        }
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        conn.connect();

        final PrintStream printer = new PrintStream(conn.getOutputStream(), false, "UTF-8");
        try {
            // Send binary file.
            printer.println("--" + boundary);
            printer.println("Content-Disposition: form-data; name=\"source\"; filename=\"" + imageFilename + "\"");
            printer.println("Content-Type: " + image.contentType());
            printer.println("Content-Transfer-Encoding: binary");
            printer.println();
            printer.write(image.content());
            printer.println();

            // End of multipart/form-data.
            printer.println("--" + boundary + "--");
        } finally {
            printer.close();
        }

        return parseJsonResponse(conn, responseType);
        
        // Avoid conn.disconnect() so the underlying socket can be reused
    }

    private <T> T go(URL url, String method, Object requestObj, TypeReference<T> responseType) throws IOException {
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        if (connectTimeout != null) {
        	conn.setConnectTimeout(connectTimeout.intValue());
        }
        if (readTimeout != null) {
        	conn.setReadTimeout(readTimeout);
        }
        conn.setRequestMethod(method);
        conn.setRequestProperty("Accept", "application/json");
        if (requestObj != null) {
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        }
        conn.connect();

        if (requestObj != null) {
            final OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            try {
                mapper.writeValue(writer, requestObj);
            } finally {
                writer.close();
            }
        }

        return parseJsonResponse(conn, responseType);

        // Avoid conn.disconnect() so the underlying socket can be reused
    }

    private static <T> T parseJsonResponse(HttpURLConnection conn, TypeReference<T> responseType) throws IOException {
        final int httpStatusCode = conn.getResponseCode();
        if ((httpStatusCode / 100) == 2) { // 2xx status codes
            final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            try {
                return streamToObject(br, responseType);
            } finally {
                br.close();
            }
        } else {
            final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            try {
                throw new RestJsonHttpException(httpStatusCode, streamToObject(br, responseType));
            } finally {
                br.close();
            }
        }
    }

    private static <T> Image parseImageResponse(HttpURLConnection conn, TypeReference<T> responseType) throws IOException {
        final int httpStatusCode = conn.getResponseCode();
        if ((httpStatusCode / 100) == 2) { // 2xx status codes
            final String contentType = conn.getContentType();
            final byte[] content = new byte[conn.getContentLength()];

            final DataInputStream dis = new DataInputStream(new BufferedInputStream(conn.getInputStream()));
            try {
                dis.readFully(content);
            } finally {
                dis.close();
            }

            return new Image(contentType, content);
            
        } else {
            final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            try {
                throw new RestJsonHttpException(httpStatusCode, streamToObject(br, responseType));
            } finally {
                br.close();
            }
        }
    }

    private static <T> T streamToObject(BufferedReader br, TypeReference<T> responseType) throws IOException {
        // Make sure the entire response is read (allows connection reuse)
        T response = null;
        String line;
        while ((line = br.readLine()) != null) {
            if ((line.length() > 0) && (line.charAt(0) == '{')) {
                response = mapper.readValue(line, responseType);
            }
        }
        return response;
    }
}
