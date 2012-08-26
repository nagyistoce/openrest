using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class OpenrestObject
    {
        public string type;

        protected OpenrestObject(string type)
        {
            this.type = type;
        }
    }
}
