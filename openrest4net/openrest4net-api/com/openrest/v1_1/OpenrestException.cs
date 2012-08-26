using System;
using System.Collections.Generic;
using System.Net;

namespace com.openrest.v1_1
{
    public class OpenrestException : ApplicationException
    {
        public OpenrestException(string error, string errorMessage)
        {
            this.error = error;
            this.errorMessage = errorMessage;
        }

        public OpenrestException(string error, string errorMessage, Exception cause)
            : base(cause.Message, cause)
        {
            this.error = error;
            this.errorMessage = errorMessage;
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

        private readonly string error;
        private readonly string errorMessage;
    }
}
