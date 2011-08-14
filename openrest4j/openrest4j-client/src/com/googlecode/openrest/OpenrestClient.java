package com.googlecode.openrest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.type.TypeReference;

/**
 * The openrest client for a multi-restaurant site.
 * @author DL
 */
public class OpenrestClient {
    public OpenrestClient(URL apiUrl, String accessToken) {
        this.apiUrl = apiUrl;
        this.accessToken = accessToken;
    }

    public OpenrestClient(URL restaurantUrl) {
        this(restaurantUrl, null);
    }
    
    public RestaurantClient getRestaurantClient(String restaurantId) {
    	try {
    		return new RestaurantClient(new URL(apiUrl.toString() + "/restaurants/" + restaurantId), accessToken);
    	} catch (MalformedURLException e) {
    		throw new RuntimeException(e);
    	}
    }

    ///////////////////////////////////////////////////////////////////////////
    
    public List<Restaurant> getRestaurants(List<String> restaurantIds) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("ids", restaurantIds);
    	
        return OpenrestProtocol.get(new URL(apiUrl + "/restaurants/" + query.toString()), new TypeReference<Response<List<Restaurant>>>() {});
    }
    
    public List<RestaurantFullInfo> getRestaurantsFullInfo(List<String> restaurantIds) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("ids", restaurantIds);
    	
        return OpenrestProtocol.get(new URL(apiUrl + "/restaurants.full/" + query.toString()), new TypeReference<Response<List<RestaurantFullInfo>>>() {});
    }
    
    public Map<String, Menu> getMenus(List<String> restaurantIds) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("restaurantIds", restaurantIds);
    	
        return OpenrestProtocol.get(new URL(apiUrl + "/menus/" + query.toString()), new TypeReference<Response<Map<String, Menu>>>() {});
    }

    private final URL apiUrl;
    private final String accessToken;
}
