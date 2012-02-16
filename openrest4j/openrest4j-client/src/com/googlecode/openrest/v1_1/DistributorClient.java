package com.googlecode.openrest.v1_1;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.type.TypeReference;

/**
 * The openrest client for a single distributor.
 * @author DL
 */
public class DistributorClient extends OrganizationClient {
    public DistributorClient(URL organizationApiUrl, String accessToken, OpenrestProtocol protocol) {
    	super(organizationApiUrl, accessToken, protocol);
    }

    public DistributorClient(URL organizationApiUrl) {
        this(organizationApiUrl, null, null);
    }
	
    ///////////////////////////////////////////////////////////////////////////
    
    public Distributor getDistributor() throws IOException, OpenrestException {
        return protocol.get(organizationApiUrl, new TypeReference<Response<Distributor>>() {});
    }

    public Distributor setDistributor(Distributor distributor) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.set(new URL(organizationApiUrl.toString() + query),
        		distributor, new TypeReference<Response<Distributor>>() {});
    }
}
