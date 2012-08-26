using System;
using System.IO;
using System.Net;

namespace com.openrest.v1_1
{
    /**
     * An exception thrown by a RESTful HTTP server, with an optional returned value.
     * @author DL
     */
    public class RestJsonHttpException : IOException
    {
        private readonly HttpStatusCode httpStatusCode;

        public RestJsonHttpException(HttpStatusCode httpStatusCode)
        {
            this.httpStatusCode = httpStatusCode;
        }

        public HttpStatusCode HttpStatusCode
        {
            get
            {
                return httpStatusCode;
            }
        }
    }
}
