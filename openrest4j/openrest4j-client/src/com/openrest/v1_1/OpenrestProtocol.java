package com.openrest.v1_1;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.type.TypeReference;

import com.googlecode.openrest.v1_1.OpenrestException;
import com.googlecode.openrest.v1_1.Response;

public class OpenrestProtocol {
	private final RestJsonClient restJsonClient;
	
	public OpenrestProtocol(Integer connectTimeout, Integer readTimeout) {
		restJsonClient = new RestJsonClient(connectTimeout, readTimeout);
	}
	
	public OpenrestProtocol() {
		this(null, null);
	}
	
    public <T> T post(URL url, Object obj, TypeReference<Response<T>> responseType) throws IOException, OpenrestException {
        try {
        	final Response<T> response = restJsonClient.post(url, obj, responseType);
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

    public RestJsonClient getRestJsonClient() {
    	return restJsonClient;
    }
    
    private static <T> void verifyResponse(Response<T> response) throws OpenrestException {
    	if (response.error != null) {
    		throw new OpenrestException(response.error, response.errorMessage);
    	}
    }
}
