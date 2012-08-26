using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class OpenrestObject
    {
        protected OpenrestObject(string type) {
            this.type = type;
        }

        public string type;
    }
}
