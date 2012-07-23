package com.openrest.v1_1;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.type.TypeReference;

import com.googlecode.openrest.v1_1.OpenrestException;
import com.googlecode.openrest.v1_1.Response;

/**
 * The Openrest client.
 * @author DL
 */
public class OpenrestClient {
    private final URL apiUrl;
    private final OpenrestProtocol protocol;
    
    public OpenrestClient(URL apiUrl, Integer connectTimeout, Integer readTimeout) {
        this.apiUrl = apiUrl;
        protocol = new OpenrestProtocol(connectTimeout, readTimeout);
    }
    
    public OpenrestClient(URL apiUrl) {
        this(apiUrl, null, null);
    }
    
    public <T> T request(Request request, TypeReference<Response<T>> responseType) throws IOException, OpenrestException {
        return protocol.post(apiUrl, request, responseType);
    }
}
