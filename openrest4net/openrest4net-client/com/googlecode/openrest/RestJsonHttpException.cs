using System;
using System.IO;
using System.Net;

namespace com.googlecode.openrest
{
    /**
     * An exception thrown by a RESTful HTTP server, with an optional returned value.
     * @author DL
     */
    class RestJsonHttpException<T> : IOException
    {
        public RestJsonHttpException(HttpStatusCode httpStatusCode, T value)
        {
            this.httpStatusCode = httpStatusCode;
            this.value = value;
        }

        public HttpStatusCode HttpStatusCode
        {
            get
            {
                return httpStatusCode;
            }
        }

        public T Value
        {
            get
            {
                return value;
            }
        }

        private readonly HttpStatusCode httpStatusCode;
        private readonly T value;
    }
}
