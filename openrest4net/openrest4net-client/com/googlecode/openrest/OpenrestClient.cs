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
                return RestJsonClient.GetImage<Response<object>>(new Uri(restaurantUri + "/picture"));
            }
        }

        public void SetImage(string imageFilename, Image image)
        {
            RestJsonClient.Put<Response<object>>(new Uri(restaurantUri + "/picture" + "?access_token=" + accessToken),
                    imageFilename, image);
        }

        public void RemoveImage()
        {
            Remove(new Uri(restaurantUri + "/picture" + "?access_token=" + accessToken));
        }

        public Image Icon
        {
            get
            {
                return RestJsonClient.GetImage<Response<object>>(new Uri(restaurantUri + "/picture?type=icon"));
            }
        }

        public void SetIcon(string iconFilename, Image icon)
        {
            RestJsonClient.Put<Response<object>>(new Uri(restaurantUri + "/picture?type=icon" + "&access_token=" + accessToken),
                    iconFilename, icon);
        }

        public void RemoveIcon()
        {
            Remove(new Uri(restaurantUri + "/picture?type=icon" + "?access_token=" + accessToken));
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
            try
            {
                return RestJsonClient.Post<Response<T>>(uri, value).value;
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

        private readonly Uri restaurantUri;
        private readonly string accessToken;
    }
}
