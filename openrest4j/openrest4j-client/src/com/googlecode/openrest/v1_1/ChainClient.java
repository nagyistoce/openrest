package com.googlecode.openrest.v1_1;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.type.TypeReference;

/**
 * The openrest client for a single chain.
 * @author DL
 */
public class ChainClient extends OrganizationClient {
    
    public ChainClient(URL organizationApiUrl, String accessToken, OpenrestProtocol protocol) {
    	super(organizationApiUrl, accessToken, protocol);
    }

    public ChainClient(URL organizationApiUrl) {
        this(organizationApiUrl, null, null);
    }
	
    ///////////////////////////////////////////////////////////////////////////
    
    public Chain getChain() throws IOException, OpenrestException {
        return protocol.get(organizationApiUrl, new TypeReference<Response<Chain>>() {});
    }

    public Chain setChain(Chain chain) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.set(new URL(organizationApiUrl.toString() + query),
        		chain, new TypeReference<Response<Chain>>() {});
    }
}
