package com.googlecode.openrest.v1_0;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.codehaus.jackson.type.TypeReference;

/**
 * The openrest client for a single restaurant.
 * @author DL
 */
public class RestaurantClient {
    private final URL restaurantApiUrl;
    private final String accessToken;
    private final OpenrestProtocol protocol;
    
    public RestaurantClient(URL restaurantApiUrl, String accessToken, OpenrestProtocol protocol) {
        this.restaurantApiUrl = restaurantApiUrl;
        this.accessToken = accessToken;
        this.protocol = ((protocol != null) ? protocol : new OpenrestProtocol());
    }

    public RestaurantClient(URL restaurantApiUrl) {
        this(restaurantApiUrl, null, null);
    }
	
    ///////////////////////////////////////////////////////////////////////////
    
    public Restaurant getRestaurant() throws IOException, OpenrestException {
    	return getRestaurant(null);
    }
    
    public Restaurant getRestaurant(String locale) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("locale", locale);
        return protocol.get(new URL(restaurantApiUrl + query.toString()), new TypeReference<Response<Restaurant>>() {});
    }

    public Restaurant setRestaurant(Restaurant restaurant) throws IOException, OpenrestException {
        return protocol.set(new URL(restaurantApiUrl.toString() + "?access_token=" + accessToken),
                restaurant, new TypeReference<Response<Restaurant>>() {});
    }
    
    public void removeRestaurant() throws IOException, OpenrestException {
    	protocol.remove(new URL(restaurantApiUrl.toString() + "?access_token=" + accessToken));
    }

    public Image getImage() throws IOException, OpenrestException {
        return protocol.getRestJsonClient().getImage(new URL(restaurantApiUrl.toString() + "/picture"),
                new TypeReference<Response<Object>>() {});
    }

    public void setImage(String imageFilename, Image image) throws IOException, OpenrestException {
    	protocol.getRestJsonClient().put(new URL(restaurantApiUrl.toString() + "/picture" + "?access_token=" + accessToken),
                imageFilename, image, new TypeReference<Response<Object>>() {});
    }

    public void removeImage() throws IOException, OpenrestException {
    	protocol.remove(new URL(restaurantApiUrl.toString() + "/picture" + "?access_token=" + accessToken));
    }

    public Image getIcon() throws IOException, OpenrestException {
        return protocol.getRestJsonClient().getImage(new URL(restaurantApiUrl.toString() + "/picture?type=icon"),
                new TypeReference<Response<Object>>() {});
    }

    public void setIcon(String imageFilename, Image icon) throws IOException, OpenrestException {
    	protocol.getRestJsonClient().put(new URL(restaurantApiUrl.toString() + "/picture?type=icon" + "&access_token=" + accessToken),
                imageFilename, icon, new TypeReference<Response<Object>>() {});
    }

    public void removeIcon() throws IOException, OpenrestException {
    	protocol.remove(new URL(restaurantApiUrl.toString() + "/picture?type=icon" + "&access_token=" + accessToken));
    }

    ///////////////////////////////////////////////////////////////////////////

    public Menu getMenu() throws IOException, OpenrestException {
    	return getMenu(null);
    }
    
    public Menu getMenu(String locale) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("locale", locale);
        return protocol.get(new URL(restaurantApiUrl.toString() + "/menu" + query.toString()), new TypeReference<Response<Menu>>() {});
    }

    ///////////////////////////////////////////////////////////////////////////

    public Staff getStaff() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
        return protocol.get(new URL(restaurantApiUrl.toString() + "/staff/" + query.toString()),
                new TypeReference<Response<Staff>>() {});
    }

    public Staff setStaff(Staff staff) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
        return protocol.set(new URL(restaurantApiUrl.toString() + "/staff/" + query.toString()),
                staff, new TypeReference<Response<Staff>>() {});
    }
    
    public List<String> getFacebookAdmins() throws IOException, OpenrestException {
        return protocol.get(new URL(restaurantApiUrl.toString() + "/staff/facebook"),
                new TypeReference<Response<List<String>>>() {});
    }

    ///////////////////////////////////////////////////////////////////////////

    public Notifications getNotifications() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
        return protocol.get(new URL(restaurantApiUrl.toString() + "/notifications/" + query.toString()),
                new TypeReference<Response<Notifications>>() {});
    }

    public Notifications setNotifications(Notifications notifications) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
        return protocol.set(new URL(restaurantApiUrl.toString() + "/notifications/" + query.toString()),
                notifications, new TypeReference<Response<Notifications>>() {});
    }

    ///////////////////////////////////////////////////////////////////////////

    public Menu getItems() throws IOException, OpenrestException {
    	return getItems(null);
    }
    
    public Menu getItems(String locale) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("locale", locale);
        return protocol.get(new URL(restaurantApiUrl.toString() + "/items/" + query.toString()), new TypeReference<Response<Menu>>() {});
    }

    public Item getItem(String itemId) throws IOException, OpenrestException {
    	return getItem(itemId, null);
    }
    
    public Item getItem(String itemId, String locale) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("locale", locale);
        return protocol.get(new URL(restaurantApiUrl.toString() + "/items/" + URLEncoder.encode(itemId, "UTF-8") + query.toString()),
                new TypeReference<Response<Item>>() {});
    }

    public Item setItem(String itemId, Item item) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
        return protocol.set(new URL(restaurantApiUrl.toString() + "/items/" + URLEncoder.encode(itemId, "UTF-8") + query.toString()),
                item, new TypeReference<Response<Item>>() {});
    }

    public Item addItem(Item item) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
        return protocol.add(new URL(restaurantApiUrl.toString() + "/items/" + query.toString()),
                item, new TypeReference<Response<Item>>() {});
    }

    public Image getItemImage(String itemId) throws IOException, OpenrestException {
        return protocol.getRestJsonClient().getImage(new URL(restaurantApiUrl.toString() + "/items/" +
                URLEncoder.encode(itemId, "UTF-8") + "/picture"),
                new TypeReference<Response<Object>>() {});
    }

    public void setItemImage(String itemId, String imageFilename, Image image) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
    	protocol.getRestJsonClient().put(new URL(restaurantApiUrl.toString() + "/items/" + URLEncoder.encode(itemId, "UTF-8") +
                "/picture" + query.toString()), imageFilename, image,
                new TypeReference<Response<Object>>() {});
    }

    public void removeItemImage(String itemId) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
    	protocol.remove(new URL(restaurantApiUrl.toString() + "/items/" + URLEncoder.encode(itemId, "UTF-8") +
                "/picture" + query.toString()));
    }

    ///////////////////////////////////////////////////////////////////////////

    public Menu getTags() throws IOException, OpenrestException {
    	return getTags(null);
    }
    
    public Menu getTags(String locale) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("locale", locale);
        return protocol.get(new URL(restaurantApiUrl.toString() + "/tags/" + query.toString()), new TypeReference<Response<Menu>>() {});
    }

    public Tag getTag(String tagId) throws IOException, OpenrestException {
    	return getTag(tagId, null);
    }
    
    public Tag getTag(String tagId, String locale) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("locale", locale);
        return protocol.get(new URL(restaurantApiUrl.toString() + "/tags/" + URLEncoder.encode(tagId, "UTF-8") + query.toString()),
                new TypeReference<Response<Tag>>() {});
    }

    public Tag setTag(String tagId, Tag tag) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
        return protocol.set(new URL(restaurantApiUrl.toString() + "/tags/" + URLEncoder.encode(tagId, "UTF-8") + query.toString()),
                tag, new TypeReference<Response<Tag>>() {});
    }

    public Tag addTag(Tag tag) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
        return protocol.add(new URL(restaurantApiUrl.toString() + "/tags/" + query.toString()),
                tag, new TypeReference<Response<Tag>>() {});
    }

    public void removeTag(String tagId) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
    	protocol.remove(new URL(restaurantApiUrl.toString() + "/tags/" + URLEncoder.encode(tagId, "UTF-8") + query.toString()));
    }

    ///////////////////////////////////////////////////////////////////////////

    public Menu getCategories() throws IOException, OpenrestException {
    	return getCategories(null);
    }
    
    public Menu getCategories(String locale) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("locale", locale);
        return protocol.get(new URL(restaurantApiUrl.toString() + "/categories/" + query.toString()), new TypeReference<Response<Menu>>() {});
    }

    public Menu setCategories(Menu categoriesMenu) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
        return protocol.set(new URL(restaurantApiUrl.toString() + "/categories/" + query.toString()),
                categoriesMenu, new TypeReference<Response<Menu>>() {});
    }

    public Category getCategory(String categoryId) throws IOException, OpenrestException {
    	return getCategory(categoryId, null);
    }
    
    public Category getCategory(String categoryId, String locale) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("locale", locale);
        return protocol.get(new URL(restaurantApiUrl.toString() + "/categories/" + URLEncoder.encode(categoryId, "UTF-8") + query.toString()),
                new TypeReference<Response<Category>>() {});
    }

    public Category setCategory(String categoryId, Category category) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
        return protocol.set(new URL(restaurantApiUrl.toString() + "/categories/" + URLEncoder.encode(categoryId, "UTF-8") + query.toString()),
                category, new TypeReference<Response<Category>>() {});
    }

    public Category addCategory(Category category) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
        return protocol.add(new URL(restaurantApiUrl.toString() + "/categories/" + query.toString()),
                category, new TypeReference<Response<Category>>() {});
    }

    public void removeCategory(String categoryId) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
    	protocol.remove(new URL(restaurantApiUrl.toString() + "/categories/" + URLEncoder.encode(categoryId, "UTF-8") + query.toString()));
    }

    ///////////////////////////////////////////////////////////////////////////

    public List<Order> getOrders(String status, java.util.Date since, java.util.Date until, String userId,
    		String ordering, Integer limit, Boolean restaurantView) throws IOException, OpenrestException {
    	return getOrders(status, since, until, userId, ordering, limit, restaurantView, null);
    }
    
    public List<Order> getOrders(String status, java.util.Date since, java.util.Date until, String userId,
    		String ordering, Integer limit, Boolean restaurantView, String locale) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("status", status);
    	if (since != null) {
    		query.append("since", Long.toString(since.getTime()));
    	}
    	if (until != null) {
    		query.append("until", Long.toString(until.getTime()));
    	}
		query.append("userId", userId);
		query.append("ordering", ordering);
    	if (limit != null) {
    		query.append("limit", limit.toString());
    	}
    	if (restaurantView != null) {
    		query.append("restaurantView", restaurantView.toString());
    	}
		query.append("locale", locale);
		query.append("access_token", accessToken);
    	
        return protocol.get(new URL(restaurantApiUrl.toString() + "/orders/" + query.toString()),
                new TypeReference<Response<List<Order>>>() {});
    }

    public Order getOrder(String orderId, Boolean restaurantView, String shareToken) throws IOException, OpenrestException {
    	return getOrder(orderId, restaurantView, shareToken, null);
    }
    
    public Order getOrder(String orderId, Boolean restaurantView, String shareToken, String locale) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	if (restaurantView != null) {
    		query.append("restaurantView", restaurantView.toString());
    	}
		query.append("shareToken", shareToken);
		query.append("locale", locale);
		query.append("access_token", accessToken);
    	
        return protocol.get(new URL(restaurantApiUrl.toString() + "/orders/" + URLEncoder.encode(orderId, "UTF-8") + query.toString()),
                new TypeReference<Response<Order>>() {});
    }
    
    public Order setOrder(String orderId, Order order) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
    		
        return protocol.set(new URL(restaurantApiUrl.toString() + "/orders/" + URLEncoder.encode(orderId, "UTF-8") + query.toString()),
                order, new TypeReference<Response<Order>>() {});
    }

    public OrderConfirmation addOrder(Order order) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
        return protocol.add(new URL(restaurantApiUrl.toString() + "/orders/" + query.toString()),
                order, new TypeReference<Response<OrderConfirmation>>() {});
    }
    
    ///////////////////////////////////////////////////////////////////////////

    public ClubMembers getClubMembers() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
        return protocol.get(new URL(restaurantApiUrl.toString() + "/club/" + query.toString()), new TypeReference<Response<ClubMembers>>() {});
    }
    
    public ClubMembers setClubMembers(ClubMembers clubMembers) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
        return protocol.set(new URL(restaurantApiUrl.toString() + "/club/" + query.toString()), clubMembers, new TypeReference<Response<ClubMembers>>() {});
    }
}
