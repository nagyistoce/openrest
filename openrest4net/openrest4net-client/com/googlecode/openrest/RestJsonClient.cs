using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.IO;
using Newtonsoft.Json;

namespace com.googlecode.openrest
{
    /**
     * A client for a RESTful web-service that supports JSON representations.
     * @author DL
     */
    class RestJsonClient
    {
        private RestJsonClient() { }

        public static T Get<T>(Uri uri)
        {
            return Go<T>(uri, "GET", null);
        }

        public static T Put<T>(Uri uri, object requestObj)
        {
            return Go<T>(uri, "PUT", requestObj);
        }

        public static T Post<T>(Uri uri, object requestObj)
        {
            return Go<T>(uri, "POST", requestObj);
        }

        public static T Delete<T>(Uri uri)
        {
            return Go<T>(uri, "DELETE", null);
        }

        public static Image GetImage<T>(Uri uri)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(uri);
            request.Method = "GET";
            try
            {
                using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
                {
                    using (Stream stream = response.GetResponseStream())
                    {
                        return new Image(response.ContentType, StreamUtils.ReadFully(stream, (int) response.ContentLength));
                    }
                }

            }
            catch (WebException e)
            {
                HttpWebResponse response = ((HttpWebResponse)e.Response);
                throw new RestJsonHttpException<T>(response.StatusCode, StreamToObject<T>(response.GetResponseStream()));
            }
        }

        public static T Put<T>(Uri uri, string imageFilename, Image image)
        {
            string boundary = Utils.ToUnixTime(DateTime.UtcNow).ToString();

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(uri);
            request.Method = "PUT";
            request.ContentType = "multipart/form-data; boundary=" + boundary;
            try
            {
                using (Stream stream = request.GetRequestStream())
                {
                    using (StreamWriter writer = new StreamWriter(stream, Encoding.UTF8))
                    {
                        // Send binary file.
                        writer.WriteLine("--" + boundary);
                        writer.WriteLine("Content-Disposition: form-data; name=\"source\"; filename=\"" + imageFilename + "\"");
                        writer.WriteLine("Content-Type: " + image.ContentType);
                        writer.WriteLine("Content-Transfer-Encoding: binary");
                        writer.WriteLine();
                        writer.Flush();

                        stream.Write(image.Content, 0, image.Content.Length);
                        writer.WriteLine();

                        // End of multipart/form-data.
                        writer.WriteLine("--" + boundary + "--");
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
                throw new RestJsonHttpException<T>(response.StatusCode, StreamToObject<T>(response.GetResponseStream()));
            }
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
                throw new RestJsonHttpException<T>(response.StatusCode, StreamToObject<T>(response.GetResponseStream()));
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
