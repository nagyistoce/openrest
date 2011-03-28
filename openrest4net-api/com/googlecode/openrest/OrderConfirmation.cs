using System;

namespace com.googlecode.openrest
{
    class OrderConfirmation
    {
        public OrderConfirmation(Order order, String message)
        {
            this.order = order;
            this.message = message;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        internal OrderConfirmation() { }

        /** The confirmed order. */
        public Order order;

        /** The restaurant's confirmation message. */
        public string message;
    }
}
