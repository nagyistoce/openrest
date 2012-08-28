using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class Distributor : Organization
    {
        public const string TYPE = "distributor";

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Distributor() : base(TYPE) { }
    }
}
