using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class Request
    {
        public string type;

        protected Request(string type)
        {
            this.type = type;
        }
    }
}
