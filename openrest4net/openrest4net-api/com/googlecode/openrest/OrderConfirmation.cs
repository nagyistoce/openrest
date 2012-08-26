using System;
using com.openrest.v1_1;

namespace com.googlecode.openrest
{
    public class OrderConfirmation
    {
        public OrderConfirmation(Order order, String message)
        {
            this.order = order;
            this.message = message;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public OrderConfirmation() { }

        /** The confirmed order. */
        public Order order;

        /** The restaurant's confirmation message. */
        public string message;
    }
}
