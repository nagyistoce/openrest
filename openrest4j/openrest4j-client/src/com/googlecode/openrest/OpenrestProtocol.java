package com.googlecode.openrest;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.type.TypeReference;

public class OpenrestProtocol {
	private OpenrestProtocol() {}
	
    public static <T> T get(URL url, TypeReference<Response<T>> responseType) throws IOException, OpenrestException {
        try {
        	final Response<T> response = RestJsonClient.get(url, responseType);
        	verifyResponse(response);
        	return response.value;
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
            final Response<T> response = RestJsonClient.put(url, obj, responseType);
        	verifyResponse(response);
        	return response.value;
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
        	final Response<T> response = RestJsonClient.post(url, obj, responseType);
        	verifyResponse(response);
        	return response.value;
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
            final Response<Object> response = RestJsonClient.delete(url, new TypeReference<Response<Object>>() {});
        	verifyResponse(response);
        } catch (RestJsonHttpException e) {
            final Response<?> response = (Response<?>) e.value();
            if (response != null) {
                throw new OpenrestException(response.error, response.errorMessage, e);
            } else {
                throw e;
            }
        }
    }
    
    private static <T> void verifyResponse(Response<T> response) throws OpenrestException {
    	if (response.error != null) {
    		throw new OpenrestException(response.error, response.errorMessage);
    	}
    }
}
