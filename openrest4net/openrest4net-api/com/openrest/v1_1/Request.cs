using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class Request
    {
        public string type;

        /** Empty constructor required for initialization from JSON-encoded string. */
        protected Request(string type)
        {
            this.type = type;
        }
    }
}
