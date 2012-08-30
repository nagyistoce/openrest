using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class SubmitOrderRequest : Request
    {
        public const string TYPE = "submit_order";

        public string accessToken;
        public Order order;
        public string comment;

        /** Empty constructor required for initialization from JSON-encoded string. */
        public SubmitOrderRequest()
            : base(TYPE) {}

        public SubmitOrderRequest(string accessToken, Order order, string comment)
            : base(TYPE)
        {
            this.accessToken = accessToken;
            this.order = order;
            this.comment = comment;
        }
    }
}
