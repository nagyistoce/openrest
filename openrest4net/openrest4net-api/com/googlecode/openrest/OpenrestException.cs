using System;
using System.Collections.Generic;
using System.Net;

namespace com.googlecode.openrest
{
    public class OpenrestException : ApplicationException
    {
        public OpenrestException(HttpStatusCode httpErrorCode, string error, string errorMessage)
        {
            this.httpErrorCode = httpErrorCode;
            this.error = error;
            this.errorMessage = errorMessage;
        }

        public OpenrestException(HttpStatusCode httpErrorCode, string error, string errorMessage, Exception cause)
            : base(cause.Message, cause)
        {
            this.httpErrorCode = httpErrorCode;
            this.error = error;
            this.errorMessage = errorMessage;
        }

        public HttpStatusCode HttpErrorCode
        {
            get
            {
                return httpErrorCode;
            }
        }

        public string Error
        {
            get
            {
                return error;
            }
        }

        public string ErrorMessage
        {
            get
            {
                return errorMessage;
            }
        }

        private readonly HttpStatusCode httpErrorCode;
        private readonly string error;
        private readonly string errorMessage;
    }
}
