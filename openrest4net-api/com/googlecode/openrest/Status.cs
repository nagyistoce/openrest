using System;
using System.Collections.Generic;

namespace com.googlecode.openrest
{
    class Status
    {
        /** Available. */
        public const string STATUS_AVAILABLE = "available";
        /** Unavailable. */
        public const string STATUS_UNAVAILABLE = "unavailable";

        public Status(string status, long? until)
        {
            this.status = status;
            this.until = until;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        internal Status() { }

        public string status;
        public long? until;
    }
}
