using System;
using System.IO;

namespace com.googlecode.openrest
{
    /**
     * An exception thrown by a RESTful HTTP server, with an optional returned value.
     * @author DL
     */
    class RestJsonHttpException : IOException
    {
        public RestJsonHttpException(int httpErrorCode, Object value)
        {
            this.httpErrorCode = httpErrorCode;
            this.value = value;
        }

        public int HttpErrorCode {
            get
            {
                return httpErrorCode;
            }
        }
    
        public Object Value {
            get
            {
                return value;
            }
        }

        private readonly int httpErrorCode;
        private readonly Object value;
    }
}
