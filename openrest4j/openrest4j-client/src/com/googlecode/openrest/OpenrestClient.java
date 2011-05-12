package com.googlecode.openrest;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import org.codehaus.jackson.type.TypeReference;

/**
 * An openrest API client.
 * @author DL
 */
public class OpenrestClient {
    public OpenrestClient(URL restaurantUrl, String accessToken) {
        this.restaurantUrl = restaurantUrl;
        this.accessToken = accessToken;
    }

    public OpenrestClient(URL restaurantUrl) {
        this(restaurantUrl, null);
    }

    ///////////////////////////////////////////////////////////////////////////
    
    public Restaurant restaurant() throws IOException, OpenrestException {
        return get(restaurantUrl, new TypeReference<Response<Restaurant>>() {});
    }

    public Restaurant setRestaurant(Restaurant restaurant) throws IOException, OpenrestException {
        return set(new URL(restaurantUrl.toString() + "?access_token=" + accessToken),
                restaurant, new TypeReference<Response<Restaurant>>() {});
    }
    
    public void removeRestaurant() throws IOException, OpenrestException {
        remove(new URL(restaurantUrl.toString() + "?access_token=" + accessToken));
    }

    public Image image() throws IOException, OpenrestException {
        return RestJsonClient.getImage(new URL(restaurantUrl.toString() + "/picture"),
                new TypeReference<Response<Object>>() {});
    }

    public void setImage(String imageFilename, Image image) throws IOException, OpenrestException {
        RestJsonClient.put(new URL(restaurantUrl.toString() + "/picture" + "?access_token=" + accessToken),
                imageFilename, image, new TypeReference<Response<Object>>() {});
    }

    public void removeImage() throws IOException, OpenrestException {
        remove(new URL(restaurantUrl.toString() + "/picture" + "?access_token=" + accessToken));
    }

    public Image icon() throws IOException, OpenrestException {
        return RestJsonClient.getImage(new URL(restaurantUrl.toString() + "/picture?type=icon"),
                new TypeReference<Response<Object>>() {});
    }

    public void setIcon(String imageFilename, Image icon) throws IOException, OpenrestException {
        RestJsonClient.put(new URL(restaurantUrl.toString() + "/picture?type=icon" + "&access_token=" + accessToken),
                imageFilename, icon, new TypeReference<Response<Object>>() {});
    }

    public void removeIcon() throws IOException, OpenrestException {
        remove(new URL(restaurantUrl.toString() + "/picture?type=icon" + "&access_token=" + accessToken));
    }

    ///////////////////////////////////////////////////////////////////////////

    public Menu menu() throws IOException, OpenrestException {
        return get(new URL(restaurantUrl.toString() + "/menu"), new TypeReference<Response<Menu>>() {});
    }

    ///////////////////////////////////////////////////////////////////////////

    public Staff staff() throws IOException, OpenrestException {
        return get(new URL(restaurantUrl.toString() + "/staff/" + "?access_token=" + accessToken),
                new TypeReference<Response<Staff>>() {});
    }

    public Staff setStaff(Staff staff) throws IOException, OpenrestException {
        return set(new URL(restaurantUrl.toString() + "/staff/" + "?access_token=" + accessToken),
                staff, new TypeReference<Response<Staff>>() {});
    }

    ///////////////////////////////////////////////////////////////////////////

    public Notifications notifications() throws IOException, OpenrestException {
        return get(new URL(restaurantUrl.toString() + "/notifications/" + "?access_token=" + accessToken),
                new TypeReference<Response<Notifications>>() {});
    }

    public Notifications setNotifications(Notifications notifications) throws IOException, OpenrestException {
        return set(new URL(restaurantUrl.toString() + "/notifications/" + "?access_token=" + accessToken),
                notifications, new TypeReference<Response<Notifications>>() {});
    }

    ///////////////////////////////////////////////////////////////////////////

    public Menu items() throws IOException, OpenrestException {
        return get(new URL(restaurantUrl.toString() + "/items/"), new TypeReference<Response<Menu>>() {});
    }

    public Item item(String itemId) throws IOException, OpenrestException {
        return get(new URL(restaurantUrl.toString() + "/items/" + URLEncoder.encode(itemId, "UTF-8")),
                new TypeReference<Response<Item>>() {});
    }

    public Item setItem(String itemId, Item item) throws IOException, OpenrestException {
        return set(new URL(restaurantUrl.toString() + "/items/" + URLEncoder.encode(itemId, "UTF-8") +
                "?access_token=" + accessToken),
                item, new TypeReference<Response<Item>>() {});
    }

    public Item addItem(Item item) throws IOException, OpenrestException {
        return add(new URL(restaurantUrl.toString() + "/items/" + "?access_token=" + accessToken),
                item, new TypeReference<Response<Item>>() {});
    }

    public Image itemImage(String itemId) throws IOException, OpenrestException {
        return RestJsonClient.getImage(new URL(restaurantUrl.toString() + "/items/" +
                URLEncoder.encode(itemId, "UTF-8") + "/picture"),
                new TypeReference<Response<Object>>() {});
    }

    public void setItemImage(String itemId, String imageFilename, Image image) throws IOException, OpenrestException {
        RestJsonClient.put(new URL(restaurantUrl.toString() + "/items/" + URLEncoder.encode(itemId, "UTF-8") +
                "/picture" + "?access_token=" + accessToken), imageFilename, image,
                new TypeReference<Response<Object>>() {});
    }

    public void removeItemImage(String itemId) throws IOException, OpenrestException {
        remove(new URL(restaurantUrl.toString() + "/items/" + URLEncoder.encode(itemId, "UTF-8") +
                "/picture" + "?access_token=" + accessToken));
    }

    ///////////////////////////////////////////////////////////////////////////

    public Menu tags() throws IOException, OpenrestException {
        return get(new URL(restaurantUrl.toString() + "/tags/"), new TypeReference<Response<Menu>>() {});
    }

    public Tag tag(String tagId) throws IOException, OpenrestException {
        return get(new URL(restaurantUrl.toString() + "/tags/" + URLEncoder.encode(tagId, "UTF-8")),
                new TypeReference<Response<Tag>>() {});
    }

    public Tag setTag(String tagId, Tag tag) throws IOException, OpenrestException {
        return set(new URL(restaurantUrl.toString() + "/tags/" + URLEncoder.encode(tagId, "UTF-8") +
                "?access_token=" + accessToken),
                tag, new TypeReference<Response<Tag>>() {});
    }

    public Tag addTag(Tag tag) throws IOException, OpenrestException {
        return add(new URL(restaurantUrl.toString() + "/tags/" + "?access_token=" + accessToken),
                tag, new TypeReference<Response<Tag>>() {});
    }

    public void removeTag(String tagId) throws IOException, OpenrestException {
        remove(new URL(restaurantUrl.toString() + "/tags/" + URLEncoder.encode(tagId, "UTF-8") +
                "?access_token=" + accessToken));
    }

    ///////////////////////////////////////////////////////////////////////////

    public Menu categories() throws IOException, OpenrestException {
        return get(new URL(restaurantUrl.toString() + "/categories/"), new TypeReference<Response<Menu>>() {});
    }

    public Menu setCategories(Menu categoriesMenu) throws IOException, OpenrestException {
        return set(new URL(restaurantUrl.toString() + "/categories/" + "?access_token=" + accessToken),
                categoriesMenu, new TypeReference<Response<Menu>>() {});
    }

    public Category category(String categoryId) throws IOException, OpenrestException {
        return get(new URL(restaurantUrl.toString() + "/categories/" + URLEncoder.encode(categoryId, "UTF-8")),
                new TypeReference<Response<Category>>() {});
    }

    public Category setCategory(String categoryId, Category category) throws IOException, OpenrestException {
        return set(new URL(restaurantUrl.toString() + "/categories/" + URLEncoder.encode(categoryId, "UTF-8") +
                "?access_token=" + accessToken),
                category, new TypeReference<Response<Category>>() {});
    }

    public Category addCategory(Category category) throws IOException, OpenrestException {
        return add(new URL(restaurantUrl.toString() + "/categories/" + "?access_token=" + accessToken),
                category, new TypeReference<Response<Category>>() {});
    }

    public void removeCategory(String categoryId) throws IOException, OpenrestException {
        remove(new URL(restaurantUrl.toString() + "/categories/" + URLEncoder.encode(categoryId, "UTF-8") +
                "?access_token=" + accessToken));
    }

    ///////////////////////////////////////////////////////////////////////////

    public List<Order> orders(String status, java.util.Date since, java.util.Date until, String userId,
    		String ordering, Integer limit, Boolean restaurantView) throws IOException, OpenrestException {
    	final QueryStringBuilder query = new QueryStringBuilder();
    	if (status != null) {
    		query.append("status", status);
    	}
    	if (since != null) {
    		query.append("since", Long.toString(since.getTime()));
    	}
    	if (until != null) {
    		query.append("until", Long.toString(until.getTime()));
    	}
    	if (userId != null) {
    		query.append("userId", userId);
    	}
    	if (ordering != null) {
    		query.append("ordering", ordering);
    	}
    	if (limit != null) {
    		query.append("limit", limit.toString());
    	}
    	if (restaurantView != null) {
    		query.append("restaurantView", restaurantView.toString());
    	}
    	if (accessToken != null) {
    		query.append("access_token", accessToken);
    	}
    	
        return get(new URL(restaurantUrl.toString() + "/orders/" + query.toString()),
                new TypeReference<Response<List<Order>>>() {});
    }

    public Order order(String orderId) throws IOException, OpenrestException {
        return get(new URL(restaurantUrl.toString() + "/orders/" + URLEncoder.encode(orderId, "UTF-8") +
                ((accessToken != null) ? ("?access_token=" + accessToken) : "")),
                new TypeReference<Response<Order>>() {});
    }

    public OrderConfirmation addOrder(Order order) throws IOException, OpenrestException {
        return add(new URL(restaurantUrl.toString() + "/orders/"),
                order, new TypeReference<Response<OrderConfirmation>>() {});
    }

    ///////////////////////////////////////////////////////////////////////////

    private static <T> T get(URL url, TypeReference<Response<T>> responseType) throws IOException, OpenrestException {
        try {
            return RestJsonClient.get(url, responseType).value;
        } catch (RestJsonHttpException e) {
            final Response<?> response = (Response<?>) e.value();
            if (response != null) {
                throw new OpenrestException(e.httpErrorCode(), response.error, response.errorMessage, e);
            } else {
                throw e;
            }
        }
    }

    private static <T> T set(URL url, Object obj, TypeReference<Response<T>> responseType) throws IOException, OpenrestException {
        try {
            return RestJsonClient.put(url, obj, responseType).value;
        } catch (RestJsonHttpException e) {
            final Response<?> response = (Response<?>) e.value();
            if (response != null) {
                throw new OpenrestException(e.httpErrorCode(), response.error, response.errorMessage, e);
            } else {
                throw e;
            }
        }
    }

    private static <T> T add(URL url, Object obj, TypeReference<Response<T>> responseType) throws IOException, OpenrestException {
        try {
            return RestJsonClient.post(url, obj, responseType).value;
        } catch (RestJsonHttpException e) {
            final Response<?> response = (Response<?>) e.value();
            if (response != null) {
                throw new OpenrestException(e.httpErrorCode(), response.error, response.errorMessage, e);
            } else {
                throw e;
            }
        }
    }

    private static void remove(URL url) throws IOException, OpenrestException {
        try {
            RestJsonClient.delete(url, new TypeReference<Response<Object>>() {});
        } catch (RestJsonHttpException e) {
            final Response<?> response = (Response<?>) e.value();
            if (response != null) {
                throw new OpenrestException(e.httpErrorCode(), response.error, response.errorMessage, e);
            } else {
                throw e;
            }
        }
    }

    private final URL restaurantUrl;
    private final String accessToken;
}
