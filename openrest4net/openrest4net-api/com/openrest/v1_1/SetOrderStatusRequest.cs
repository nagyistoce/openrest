using System;

namespace com.openrest.v1_1
{
    public class SetOrderStatusRequest : Request
    {
        private static readonly string TYPE = "set_order_status";

        public string accessToken;
        public string confirmationToken;
        public string orderId;
        public string status;
        public string comment;

        /** Empty constructor required for initialization from JSON-encoded string. */
        public SetOrderStatusRequest() : base(TYPE)
        {
        }

        public SetOrderStatusRequest(string accessToken, string confirmationToken, string orderId, string status, string comment) : base(TYPE)
        {
            this.accessToken = accessToken;
            this.confirmationToken = confirmationToken;
            this.orderId = orderId;
            this.status = status;
            this.comment = comment;
        }
    }
}
