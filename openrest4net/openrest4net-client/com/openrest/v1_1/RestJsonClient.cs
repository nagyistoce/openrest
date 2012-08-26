using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.IO;
using Newtonsoft.Json;

namespace com.openrest.v1_1
{
    /**
     * A client for a RESTful web-service that supports JSON representations.
     * @author DL
     */
    class RestJsonClient
    {
        public RestJsonClient() { }

        public T Get<T>(Uri uri)
        {
            return Go<T>(uri, "GET", null);
        }

        public T Put<T>(Uri uri, object requestObj)
        {
            return Go<T>(uri, "PUT", requestObj);
        }

        public T Post<T>(Uri uri, object requestObj)
        {
            return Go<T>(uri, "POST", requestObj);
        }

        public T Delete<T>(Uri uri)
        {
            return Go<T>(uri, "DELETE", null);
        }

        private static T Go<T>(Uri uri, string method, object requestObj)
        {
            HttpWebRequest request = (HttpWebRequest) WebRequest.Create(uri);
            request.Method = method;
            request.Accept = "application/json";
            try
            {
                if (requestObj != null)
                {
                    request.ContentType = "application/json; charset=UTF-8";
                    byte[] content = Encoding.UTF8.GetBytes(JsonConvert.SerializeObject(requestObj));
                    request.ContentLength = content.Length;

                    using (Stream stream = request.GetRequestStream())
                    {
                        stream.Write(content, 0, content.Length);
                    }
                }

                using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
                {
                    return StreamToObject<T>(response.GetResponseStream());
                }
            }
            catch (WebException e)
            {
                HttpWebResponse response = ((HttpWebResponse)e.Response);
                throw new RestJsonHttpException(response.StatusCode);
            }
        }

        private static T StreamToObject<T>(Stream stream)
        {
            using (StreamReader reader = new StreamReader(stream, Encoding.UTF8))
            {
                string str = reader.ReadToEnd();
                return (T) JsonConvert.DeserializeObject(str, typeof(T));
            }
        }

    }
}
