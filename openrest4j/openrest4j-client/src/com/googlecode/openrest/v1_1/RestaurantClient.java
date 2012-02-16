package com.googlecode.openrest.v1_1;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.codehaus.jackson.type.TypeReference;

/**
 * The openrest client for a single restaurant.
 * @author DL
 */
public class RestaurantClient extends OrganizationClient {
    public RestaurantClient(URL organizationApiUrl, String accessToken, OpenrestProtocol protocol) {
    	super(organizationApiUrl, accessToken, protocol);
    }

    public RestaurantClient(URL organizationApiUrl) {
        this(organizationApiUrl, null, null);
    }
	
    ///////////////////////////////////////////////////////////////////////////
    
    public Restaurant getRestaurant() throws IOException, OpenrestException {
        return protocol.get(organizationApiUrl, new TypeReference<Response<Restaurant>>() {});
    }

    public Restaurant setRestaurant(Restaurant restaurant) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.set(new URL(organizationApiUrl.toString() + query),
                restaurant, new TypeReference<Response<Restaurant>>() {});
    }
    
    public void removeRestaurant() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
    	protocol.remove(new URL(organizationApiUrl.toString() + query));
    }

    ///////////////////////////////////////////////////////////////////////////

    public Menu getMenu() throws IOException, OpenrestException {
        return protocol.get(new URL(organizationApiUrl.toString() + "/menu"), new TypeReference<Response<Menu>>() {});
    }

    ///////////////////////////////////////////////////////////////////////////

    public List<String> getFacebookAdmins() throws IOException, OpenrestException {
        return protocol.get(new URL(organizationApiUrl.toString() + "/staff/facebook"),
                new TypeReference<Response<List<String>>>() {});
    }

    ///////////////////////////////////////////////////////////////////////////

    public Notifications getNotifications() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.get(new URL(organizationApiUrl.toString() + "/notifications/" + query),
                new TypeReference<Response<Notifications>>() {});
    }

    public Notifications setNotifications(Notifications notifications) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.set(new URL(organizationApiUrl.toString() + "/notifications/" + query),
                notifications, new TypeReference<Response<Notifications>>() {});
    }

    ///////////////////////////////////////////////////////////////////////////

    public Menu getItems() throws IOException, OpenrestException {
        return protocol.get(new URL(organizationApiUrl.toString() + "/items/"), new TypeReference<Response<Menu>>() {});
    }

    public Item getItem(String itemId) throws IOException, OpenrestException {
        return protocol.get(new URL(organizationApiUrl.toString() + "/items/" + URLEncoder.encode(itemId, "UTF-8")),
                new TypeReference<Response<Item>>() {});
    }

    public Item setItem(String itemId, Item item) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.set(new URL(organizationApiUrl.toString() + "/items/" + URLEncoder.encode(itemId, "UTF-8") + query),
                item, new TypeReference<Response<Item>>() {});
    }

    public Item addItem(Item item) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.add(new URL(organizationApiUrl.toString() + "/items/" + query),
                item, new TypeReference<Response<Item>>() {});
    }

    public Image getItemImage(String itemId) throws IOException, OpenrestException {
        return protocol.getRestJsonClient().getImage(new URL(organizationApiUrl.toString() + "/items/" +
                URLEncoder.encode(itemId, "UTF-8") + "/picture"),
                new TypeReference<Response<Object>>() {});
    }

    public void setItemImage(String itemId, String imageFilename, Image image) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
    	protocol.getRestJsonClient().put(new URL(organizationApiUrl.toString() + "/items/" + URLEncoder.encode(itemId, "UTF-8") +
                "/picture" + query), imageFilename, image,
                new TypeReference<Response<Object>>() {});
    }
    
    public Picture getItemImageId(String itemId) throws IOException, OpenrestException {
        return protocol.get(new URL(organizationApiUrl.toString() + "/items/" + URLEncoder.encode(itemId, "UTF-8") +
                "/picture"), new TypeReference<Response<Picture>>() {});
    }
    
    public void setItemImageId(String itemId, Picture picture) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
		
        protocol.set(new URL(organizationApiUrl.toString() + "/items/" + URLEncoder.encode(itemId, "UTF-8") +
                "/picture" + query), picture, new TypeReference<Response<Object>>() {});
    }

    public void removeItemImage(String itemId) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
    	protocol.remove(new URL(organizationApiUrl.toString() + "/items/" + URLEncoder.encode(itemId, "UTF-8") +
                "/picture" + query));
    }

    ///////////////////////////////////////////////////////////////////////////

    public Menu getTags() throws IOException, OpenrestException {
        return protocol.get(new URL(organizationApiUrl.toString() + "/tags/"), new TypeReference<Response<Menu>>() {});
    }

    public Tag getTag(String tagId) throws IOException, OpenrestException {
        return protocol.get(new URL(organizationApiUrl.toString() + "/tags/" + URLEncoder.encode(tagId, "UTF-8")),
                new TypeReference<Response<Tag>>() {});
    }

    public Tag setTag(String tagId, Tag tag) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.set(new URL(organizationApiUrl.toString() + "/tags/" + URLEncoder.encode(tagId, "UTF-8") + query),
                tag, new TypeReference<Response<Tag>>() {});
    }

    public Tag addTag(Tag tag) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.add(new URL(organizationApiUrl.toString() + "/tags/" + query),
                tag, new TypeReference<Response<Tag>>() {});
    }

    public void removeTag(String tagId) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
    	protocol.remove(new URL(organizationApiUrl.toString() + "/tags/" + URLEncoder.encode(tagId, "UTF-8") + query));
    }

    ///////////////////////////////////////////////////////////////////////////

    public Menu getCategories() throws IOException, OpenrestException {
        return protocol.get(new URL(organizationApiUrl.toString() + "/categories/"), new TypeReference<Response<Menu>>() {});
    }

    public Menu setCategories(Menu categoriesMenu) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.set(new URL(organizationApiUrl.toString() + "/categories/" + query),
                categoriesMenu, new TypeReference<Response<Menu>>() {});
    }

    public Category getCategory(String categoryId) throws IOException, OpenrestException {
        return protocol.get(new URL(organizationApiUrl.toString() + "/categories/" + URLEncoder.encode(categoryId, "UTF-8")),
                new TypeReference<Response<Category>>() {});
    }

    public Category setCategory(String categoryId, Category category) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.set(new URL(organizationApiUrl.toString() + "/categories/" + URLEncoder.encode(categoryId, "UTF-8") + query),
                category, new TypeReference<Response<Category>>() {});
    }

    public Category addCategory(Category category) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
        return protocol.add(new URL(organizationApiUrl.toString() + "/categories/" + query),
                category, new TypeReference<Response<Category>>() {});
    }

    public void removeCategory(String categoryId) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	
    	protocol.remove(new URL(organizationApiUrl.toString() + "/categories/" + URLEncoder.encode(categoryId, "UTF-8") + query));
    }

    ///////////////////////////////////////////////////////////////////////////

    public List<Order> getOrders(String status, java.util.Date since, java.util.Date until, String userId,
    		String ordering, Integer limit, Boolean restaurantView) throws IOException, OpenrestException {
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
		query.append("access_token", accessToken);
    	
        return protocol.get(new URL(organizationApiUrl.toString() + "/orders/" + query.toString()),
                new TypeReference<Response<List<Order>>>() {});
    }

    public Order getOrder(String orderId, Boolean restaurantView, String shareToken) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	if (restaurantView != null) {
    		query.append("restaurantView", restaurantView.toString());
    	}
		query.append("shareToken", shareToken);
		query.append("access_token", accessToken);
    	
        return protocol.get(new URL(organizationApiUrl.toString() + "/orders/" + URLEncoder.encode(orderId, "UTF-8") + query.toString()),
                new TypeReference<Response<Order>>() {});
    }
    
    public Order setOrder(String orderId, Order order) throws IOException, OpenrestException {
        return protocol.set(new URL(organizationApiUrl.toString() + "/orders/" + URLEncoder.encode(orderId, "UTF-8") +
                "?access_token=" + accessToken),
                order, new TypeReference<Response<Order>>() {});
    }

    public OrderConfirmation addOrder(Order order) throws IOException, OpenrestException {
        return protocol.add(new URL(organizationApiUrl.toString() + "/orders/"),
                order, new TypeReference<Response<OrderConfirmation>>() {});
    }
    
    ///////////////////////////////////////////////////////////////////////////

    public ClubMembers getClubMembers() throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);

        return protocol.get(new URL(organizationApiUrl.toString() + "/club/" + query.toString()), new TypeReference<Response<ClubMembers>>() {});
    }
    
    public ClubMembers setClubMembers(ClubMembers clubMembers) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);

        return protocol.set(new URL(organizationApiUrl.toString() + "/club/" + query.toString()), clubMembers, new TypeReference<Response<ClubMembers>>() {});
    }
    
    ///////////////////////////////////////////////////////////////////////////
    
    public Billing getBilling(Integer year, Integer month) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
		query.append("access_token", accessToken);
		query.append("year", ((year != null) ? year.toString() : null));
		query.append("month", ((month != null) ? month.toString() : null));

        return protocol.get(new URL(organizationApiUrl.toString() + "/billing" + query.toString()), new TypeReference<Response<Billing>>() {});
    }
    
    public Billing setBilling(Billing billing) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);

        return protocol.set(new URL(organizationApiUrl.toString() + "/billing" + query.toString()),
        		billing, new TypeReference<Response<Billing>>() {});
    }
    
    public Cost addCost(Cost cost) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);

        return protocol.add(new URL(organizationApiUrl.toString() + "/billing/costs/" + query.toString()),
        		cost, new TypeReference<Response<Cost>>() {});
    }
    
    ///////////////////////////////////////////////////////////////////////////

    public List<Stats> getStats(Date since, Date until, String granularity,
    		String ref) throws IOException, OpenrestException {
    	
    	final QueryStringBuilder query = new QueryStringBuilder();
    	query.append("access_token", accessToken);
    	if (since != null) {
    		query.append("since", since.toString());
    	}
    	if (until != null) {
    		query.append("until", until.toString());
    	}
		query.append("granularity", granularity);
		query.append("ref", ref);
    	
        return protocol.get(new URL(organizationApiUrl.toString() + "/stats" + query.toString()),
        		new TypeReference<Response<List<Stats>>>() {});
    }
}
