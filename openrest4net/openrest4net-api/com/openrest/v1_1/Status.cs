using System;
using System.Collections.Generic;
using Newtonsoft.Json;

namespace com.openrest.v1_1
{
    public class Status
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
        public Status() { }

        [JsonIgnore]
        public DateTime Until
        {
            get
            {
                return Utils.FromUnixTime(until.Value);
            }

            set
            {
                until = Utils.ToUnixTime(value);
            }
        }

        public string status;
        public long? until;
    }
}
