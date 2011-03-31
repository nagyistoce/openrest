using System;
using System.Collections.Generic;

namespace com.googlecode.openrest
{
    /**
     * An openrest API client.
     * @author DL
     */
    public class OpenrestClient
    {
        public OpenrestClient(Uri restaurantUri, string accessToken)
        {
            this.restaurantUri = restaurantUri;
            this.accessToken = accessToken;
        }

        public OpenrestClient(Uri restaurantUri) : this(restaurantUri, null) { }

        ///////////////////////////////////////////////////////////////////////

        public Restaurant Restaurant
        {
            get
            {
                return Get<Restaurant>(restaurantUri);
            }
        }

        public Restaurant SetRestaurant(Restaurant restaurant) {
            return Set<Restaurant>(new Uri(restaurantUri + "?access_token=" + accessToken), restaurant);
        }

        public Image Image
        {
            get
            {
                return GetImage(new Uri(restaurantUri + "/picture"));
            }
        }

        public void SetImage(string imageFilename, Image image)
        {
            SetImage(new Uri(restaurantUri + "/picture" + "?access_token=" + accessToken), imageFilename, image);
        }

        public void RemoveImage()
        {
            Remove(new Uri(restaurantUri + "/picture" + "?access_token=" + accessToken));
        }

        public Image Icon
        {
            get
            {
                return GetImage(new Uri(restaurantUri + "/picture?type=icon"));
            }
        }

        public void SetIcon(string iconFilename, Image icon)
        {
            SetImage(new Uri(restaurantUri + "/picture?type=icon" + "&access_token=" + accessToken), iconFilename, icon);
        }

        public void RemoveIcon()
        {
            Remove(new Uri(restaurantUri + "/picture?type=icon" + "?access_token=" + accessToken));
        }

        ///////////////////////////////////////////////////////////////////////

        public Menu Menu
        {
            get
            {
                return Get<Menu>(new Uri(restaurantUri + "/menu"));
            }
        }

        ///////////////////////////////////////////////////////////////////////

        public Staff Staff
        {
            get
            {
                return Get<Staff>(new Uri(restaurantUri + "/staff/" + "?access_token=" + accessToken));
            }
        }

        public Staff SetStaff(Staff staff)
        {
            return Set(new Uri(restaurantUri + "/staff/" + "?access_token=" + accessToken), staff);
        }

        ///////////////////////////////////////////////////////////////////////

        public Notifications Notifications
        {
            get
            {
                return Get<Notifications>(new Uri(restaurantUri + "/notifications/" + "?access_token=" + accessToken));
            }
        }

        public Notifications SetNotifications(Notifications notifications)
        {
            return Set(new Uri(restaurantUri + "/notifications/" + "?access_token=" + accessToken), notifications);
        }

        ///////////////////////////////////////////////////////////////////////

        public Menu Items
        {
            get
            {
                return Get<Menu>(new Uri(restaurantUri + "/items/"));
            }
        }

        public Item Item(string itemId)
        {
            return Get<Item>(new Uri(restaurantUri + "/items/" + itemId));
        }

        public Item SetItem(string itemId, Item item)
        {
            return Set<Item>(new Uri(restaurantUri + "/items/" + itemId + "?access_token=" + accessToken), item);
        }

        public Item AddItem(Item item)
        {
            return Add<Item>(new Uri(restaurantUri + "/items/" + "?access_token=" + accessToken), item);
        }

        public Image ItemImage(string itemId)
        {
            return GetImage(new Uri(restaurantUri + "/items/" + itemId + "/picture"));
        }

        public void SetItemImage(string itemId, string imageFilename, Image image)
        {
            SetImage(new Uri(restaurantUri + "/items/" + itemId + "/picture" + "?access_token=" + accessToken), imageFilename, image);
        }

        public void RemoveItemImage(string itemId)
        {
            Remove(new Uri(restaurantUri + "/items/" + itemId + "/picture" + "?access_token=" + accessToken));
        }

        ///////////////////////////////////////////////////////////////////////

        public Menu Tags
        {
            get
            {
                return Get<Menu>(new Uri(restaurantUri + "/tags/"));
            }
        }

        public Tag Tag(string tagId)
        {
            return Get<Tag>(new Uri(restaurantUri + "/tags/" + tagId));
        }

        public Tag SetTag(string tagId, Tag tag)
        {
            return Set<Tag>(new Uri(restaurantUri + "/tags/" + tagId + "?access_token=" + accessToken), tag);
        }

        public Tag AddTag(Tag tag)
        {
            return Add<Tag>(new Uri(restaurantUri + "/tags/" + "?access_token=" + accessToken), tag);
        }

        public void RemoveTag(string tagId)
        {
            Remove(new Uri(restaurantUri + "/tags/" + tagId + "?access_token=" + accessToken));
        }

        ///////////////////////////////////////////////////////////////////////

        public Menu Categories
        {
            get
            {
                return Get<Menu>(new Uri(restaurantUri + "/categories/"));
            }
        }

        public Menu SetCategories(Menu categoriesMenu)
        {
            return Set<Menu>(new Uri(restaurantUri + "/categories/" + "?access_token=" + accessToken), categoriesMenu);
        }

        public Category Category(string categoryId)
        {
            return Get<Category>(new Uri(restaurantUri + "/categories/" + categoryId));
        }

        public Category SetCategory(string categoryId, Category category)
        {
            return Set<Category>(new Uri(restaurantUri + "/categories/" + categoryId + "?access_token=" + accessToken), category);
        }

        public Category AddCategory(Category category)
        {
            return Add<Category>(new Uri(restaurantUri + "/categories/" + "?access_token=" + accessToken), category);
        }

        public void RemoveCategory(string categoryId)
        {
            Remove(new Uri(restaurantUri + "/categories/" + categoryId + "?access_token=" + accessToken));
        }

        ///////////////////////////////////////////////////////////////////////

        public IList<Order> Orders
        {
            get
            {
                return Get<IList<Order>>(new Uri(restaurantUri + "/orders/" + "?access_token=" + accessToken));
            }
        }

        public Order Order(string orderId)
        {
            return Get<Order>(new Uri(restaurantUri + "/orders/" + orderId + "?access_token=" + accessToken));
        }

        public OrderConfirmation AddOrder(Order order)
        {
            return Add<Order, OrderConfirmation>(new Uri(restaurantUri + "/orders/"), order);
        }

        ///////////////////////////////////////////////////////////////////////

        private static T Get<T>(Uri uri)
        {
            try
            {
                return RestJsonClient.Get<Response<T>>(uri).value;
            }
            catch (RestJsonHttpException<Response<T>> e) {
                Response<T> response = e.Value;
                if (response != null)
                {
                    throw new OpenrestException(e.HttpStatusCode, response.error, response.errorMessage, e);
                }
                else
                {
                    throw e;
                }
            }
        }

        private static T Set<T>(Uri uri, T value)
        {
            try
            {
                return RestJsonClient.Put<Response<T>>(uri, value).value;
            }
            catch (RestJsonHttpException<Response<T>> e) {
                Response<T> response = e.Value;
                if (response != null)
                {
                    throw new OpenrestException(e.HttpStatusCode, response.error, response.errorMessage, e);
                }
                else
                {
                    throw e;
                }
            }
        }

        private static T Add<T>(Uri uri, T value)
        {
            return Add<T, T>(uri, value);
        }

        private static R Add<T, R>(Uri uri, T value)
        {
            try
            {
                return RestJsonClient.Post<Response<R>>(uri, value).value;
            }
            catch (RestJsonHttpException<Response<R>> e) {
                Response<R> response = e.Value;
                if (response != null)
                {
                    throw new OpenrestException(e.HttpStatusCode, response.error, response.errorMessage, e);
                }
                else
                {
                    throw e;
                }
            }
        }

        private static void Remove(Uri uri)
        {
            try
            {
                RestJsonClient.Delete<Response<object>>(uri);
            }
            catch (RestJsonHttpException<Response<object>> e) {
                Response<object> response = e.Value;
                if (response != null)
                {
                    throw new OpenrestException(e.HttpStatusCode, response.error, response.errorMessage, e);
                }
                else
                {
                    throw e;
                }
            }
        }

        private static Image GetImage(Uri uri)
        {
            try
            {
                return RestJsonClient.GetImage<Response<object>>(uri);
            }
            catch (RestJsonHttpException<Response<object>> e)
            {
                Response<object> response = e.Value;
                if (response != null)
                {
                    throw new OpenrestException(e.HttpStatusCode, response.error, response.errorMessage, e);
                }
                else
                {
                    throw e;
                }
            }
        }

        private static void SetImage(Uri uri, string imageFilename, Image image)
        {
            try
            {
                RestJsonClient.Put<Response<object>>(uri, imageFilename, image);
            }
            catch (RestJsonHttpException<Response<object>> e)
            {
                Response<object> response = e.Value;
                if (response != null)
                {
                    throw new OpenrestException(e.HttpStatusCode, response.error, response.errorMessage, e);
                }
                else
                {
                    throw e;
                }
            }
        }

        private readonly Uri restaurantUri;
        private readonly string accessToken;
    }
}
