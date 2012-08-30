using System;

namespace com.openrest.v1_1
{
    public class QueryEmailRequest : Request
    {
        public const string TYPE = "query_email";

        public string email;

        /** Empty constructor required for initialization from JSON-encoded string. */
        public QueryEmailRequest() : base(TYPE)
        {
        }

        public QueryEmailRequest(string email)
            : base(TYPE)
        {
            this.email = email;
        }
    }
}
