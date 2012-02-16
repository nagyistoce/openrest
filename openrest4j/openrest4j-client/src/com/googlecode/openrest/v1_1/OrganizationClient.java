package com.googlecode.openrest.v1_1;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.type.TypeReference;

/**
 * Base class for a single organization's openrest client.
 * @author DL
 */
public class OrganizationClient {
	protected final URL organizationApiUrl;
    protected final String accessToken;
    protected final OpenrestProtocol protocol;
    
    public OrganizationClient(URL organizationApiUrl, String accessToken, OpenrestProtocol protocol) {
        this.organizationApiUrl = organizationApiUrl;
        this.accessToken = accessToken;
        this.protocol = ((protocol != null) ? protocol : new OpenrestProtocol());
    }
	
    ///////////////////////////////////////////////////////////////////////////
    
    public Image getImage() throws IOException, OpenrestException {
        return protocol.getRestJsonClient().getImage(new URL(organizationApiUrl.toString() + "/picture"),
                new TypeReference<Response<Object>>() {});
    }
    
    public void setImage(String imageFilename, Image image) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
		
    	protocol.getRestJsonClient().put(new URL(organizationApiUrl.toString() + "/picture" + query),
                imageFilename, image, new TypeReference<Response<Object>>() {});
    }
    
    public Picture getImageId() throws IOException, OpenrestException {
        return protocol.get(new URL(organizationApiUrl.toString() + "/picture"),
        		new TypeReference<Response<Picture>>() {});
    }
    
    public void setImageId(Picture picture) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
		
        protocol.set(new URL(organizationApiUrl.toString() + "/picture" + query),
        		picture, new TypeReference<Response<Object>>() {});
    }

    public void removeImage() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
		
    	protocol.remove(new URL(organizationApiUrl.toString() + "/picture" + query));
    }

    public Image getIcon() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("type", "icon");
		
        return protocol.getRestJsonClient().getImage(new URL(organizationApiUrl.toString() + "/picture" + query),
                new TypeReference<Response<Object>>() {});
    }

    public void setIcon(String imageFilename, Image icon) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("type", "icon");
		query.append("access_token", accessToken);
		
    	protocol.getRestJsonClient().put(new URL(organizationApiUrl.toString() + "/picture" + query),
                imageFilename, icon, new TypeReference<Response<Object>>() {});
    }
    
    public Picture getIconId() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("type", "icon");
		
        return protocol.get(new URL(organizationApiUrl.toString() + "/picture" + query),
        		new TypeReference<Response<Picture>>() {});
    }
    
    public void setIconId(Picture picture) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("type", "icon");
		query.append("access_token", accessToken);
		
        protocol.set(new URL(organizationApiUrl.toString() + "/picture" + query),
        		picture, new TypeReference<Response<Object>>() {});
    }

    public void removeIcon() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("type", "icon");
		query.append("access_token", accessToken);
		
    	protocol.remove(new URL(organizationApiUrl.toString() + "/picture" + query));
    }
    
    public Image getNoImage() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("type", "no_image");
		
        return protocol.getRestJsonClient().getImage(new URL(organizationApiUrl.toString() + "/picture" + query),
                new TypeReference<Response<Object>>() {});
    }

    public void setNoImage(String imageFilename, Image noImage) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("type", "no_image");
		query.append("access_token", accessToken);
		
    	protocol.getRestJsonClient().put(new URL(organizationApiUrl.toString() + "/picture" + query),
                imageFilename, noImage, new TypeReference<Response<Object>>() {});
    }
    
    public Picture getNoImageId() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("type", "no_image");
		
        return protocol.get(new URL(organizationApiUrl.toString() + "/picture" + query),
        		new TypeReference<Response<Picture>>() {});
    }
    
    public void setNoImageId(Picture picture) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("type", "no_image");
		query.append("access_token", accessToken);
		
        protocol.set(new URL(organizationApiUrl.toString() + "/picture" + query),
        		picture, new TypeReference<Response<Object>>() {});
    }

    public void removeNoImage() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("type", "no_image");
		query.append("access_token", accessToken);
		
    	protocol.remove(new URL(organizationApiUrl.toString() + "/picture" + query));
    }

    ///////////////////////////////////////////////////////////////////////////

    public Staff getStaff() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.get(new URL(organizationApiUrl.toString() + "/staff/" + query.toString()),
                new TypeReference<Response<Staff>>() {});
    }

    public Staff setStaff(Staff staff) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.set(new URL(organizationApiUrl.toString() + "/staff/" + query.toString()),
                staff, new TypeReference<Response<Staff>>() {});
    }
}
