package com.googlecode.openrest.v1_1;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.type.TypeReference;

/**
 * The openrest client for a single distributer.
 * @author DL
 */
public class DistributorClient {
    private final URL distributorApiUrl;
    private final String accessToken;
    private final OpenrestProtocol protocol;
    
    public DistributorClient(URL distributorApiUrl, String accessToken, OpenrestProtocol protocol) {
        this.distributorApiUrl = distributorApiUrl;
        this.accessToken = accessToken;
        this.protocol = ((protocol != null) ? protocol : new OpenrestProtocol());
    }

    public DistributorClient(URL distributerApiUrl) {
        this(distributerApiUrl, null, null);
    }
	
    ///////////////////////////////////////////////////////////////////////////
    
    public Distributor getDistributor() throws IOException, OpenrestException {
        return protocol.get(distributorApiUrl, new TypeReference<Response<Distributor>>() {});
    }

    public Distributor setDistributor(Distributor distributor) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.set(new URL(distributorApiUrl.toString() + query.toString()),
        		distributor, new TypeReference<Response<Distributor>>() {});
    }
    
    public Image getPicture() throws IOException, OpenrestException {
        return protocol.getRestJsonClient().getImage(new URL(distributorApiUrl.toString() + "/picture"),
                new TypeReference<Response<Object>>() {});
    }

    public void setPicture(String pictureFilename, Image picture) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
    	protocol.getRestJsonClient().put(new URL(distributorApiUrl.toString() + "/picture" + query.toString()),
    			pictureFilename, picture, new TypeReference<Response<Object>>() {});
    }

    public void removeImage() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
    	protocol.remove(new URL(distributorApiUrl.toString() + "/picture" + query.toString()));
    }

    public Image getIcon() throws IOException, OpenrestException {
        return protocol.getRestJsonClient().getImage(new URL(distributorApiUrl.toString() + "/icon"),
                new TypeReference<Response<Object>>() {});
    }

    public void setIcon(String iconFilename, Image icon) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
    	protocol.getRestJsonClient().put(new URL(distributorApiUrl.toString() + "/icon" + query.toString()),
    			iconFilename, icon, new TypeReference<Response<Object>>() {});
    }

    public void removeIcon() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
    	protocol.remove(new URL(distributorApiUrl.toString() + "/icon" + query.toString()));
    }
    
    public Image getNoImagePicture() throws IOException, OpenrestException {
        return protocol.getRestJsonClient().getImage(new URL(distributorApiUrl.toString() + "/no_image_picture"),
                new TypeReference<Response<Object>>() {});
    }

    public void setNoImagePicture(String noImagePictureFilename, Image noImagePicture) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
    	protocol.getRestJsonClient().put(new URL(distributorApiUrl.toString() + "/no_image_picture" + query.toString()),
    			noImagePictureFilename, noImagePicture, new TypeReference<Response<Object>>() {});
    }

    public void removeNoImagePicture() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
    	protocol.remove(new URL(distributorApiUrl.toString() + "/no_image_picture" + query.toString()));
    }

    ///////////////////////////////////////////////////////////////////////////

    public Staff getStaff() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.get(new URL(distributorApiUrl.toString() + "/staff/" + query.toString()),
                new TypeReference<Response<Staff>>() {});
    }

    public Staff setStaff(Staff staff) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.set(new URL(distributorApiUrl.toString() + "/staff/" + query.toString()),
                staff, new TypeReference<Response<Staff>>() {});
    }
}
