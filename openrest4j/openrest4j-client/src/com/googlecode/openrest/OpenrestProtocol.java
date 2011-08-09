package com.googlecode.openrest;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.type.TypeReference;

public class OpenrestProtocol {
	private OpenrestProtocol() {}
	
    public static <T> T get(URL url, TypeReference<Response<T>> responseType) throws IOException, OpenrestException {
        try {
            return RestJsonClient.get(url, responseType).value;
        } catch (RestJsonHttpException e) {
            final Response<?> response = (Response<?>) e.value();
            if (response != null) {
                throw new OpenrestException(response.error, response.errorMessage, e);
            } else {
                throw e;
            }
        }
    }

    public static <T> T set(URL url, Object obj, TypeReference<Response<T>> responseType) throws IOException, OpenrestException {
        try {
            return RestJsonClient.put(url, obj, responseType).value;
        } catch (RestJsonHttpException e) {
            final Response<?> response = (Response<?>) e.value();
            if (response != null) {
                throw new OpenrestException(response.error, response.errorMessage, e);
            } else {
                throw e;
            }
        }
    }

    public static <T> T add(URL url, Object obj, TypeReference<Response<T>> responseType) throws IOException, OpenrestException {
        try {
            return RestJsonClient.post(url, obj, responseType).value;
        } catch (RestJsonHttpException e) {
            final Response<?> response = (Response<?>) e.value();
            if (response != null) {
                throw new OpenrestException(response.error, response.errorMessage, e);
            } else {
                throw e;
            }
        }
    }

    public static void remove(URL url) throws IOException, OpenrestException {
        try {
            RestJsonClient.delete(url, new TypeReference<Response<Object>>() {});
        } catch (RestJsonHttpException e) {
            final Response<?> response = (Response<?>) e.value();
            if (response != null) {
                throw new OpenrestException(response.error, response.errorMessage, e);
            } else {
                throw e;
            }
        }
    }
}
