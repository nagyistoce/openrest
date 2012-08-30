using System;
using com.openrest.v1_1;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class OrderConfirmation
    {
        public OrderConfirmation(Order order, IDictionary<string, string> message)
        {
            this.order = order;
            this.message = message;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public OrderConfirmation() { }

        /** The confirmed order. */
        public Order order;

        /** The restaurant's confirmation message in various locales. */
        public IDictionary<string, string> message = new Dictionary<string, string>();
    }
}
