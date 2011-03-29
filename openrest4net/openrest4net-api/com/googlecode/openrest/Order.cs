using System;
using System.Collections.Generic;

namespace com.googlecode.openrest
{
    public class Order
    {
        /**
         * The restaurant required further confirmation before the order is submitted
         * (e.g. validating the user's phone number by SMS).
         */
        public const string ORDER_STATUS_PENDING = "pending";
        /** The order has been submitted, and awaits processing by the restaurant. */
        public const string ORDER_STATUS_NEW = "new";
        /** The order has been processed and accepted by the restaurant. */
        public const string ORDER_STATUS_ACCEPTED = "accepted";
        /** The order has been canceled. */
        public const string ORDER_STATUS_CANCELLED = "canceled";

        public Order(string id, string restaurantId, IList<OrderItem> orderItems, string comment,
            int price, Delivery delivery, Contact contact, IList<Payment> payments,
            long created, long modified, User user, string status, string shareToken)
        {
            this.id = id;
            this.restaurantId = restaurantId;
            this.orderItems = orderItems;
            this.comment = comment;
            this.price = price;
            this.delivery = delivery;
            this.contact = contact;
            this.payments = payments;
            this.created = created;
            this.modified = modified;
            this.user = user;
            this.status = status;
            this.shareToken = shareToken;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Order() { }

        /** The order's unique id. */
        public string id;

        /** The restaurant's unique id. */
        public string restaurantId;

        /** The ordered items. */
        public IList<OrderItem> orderItems = new List<OrderItem>();

        /** Comment to the restaurant (as opposed to the delivery person!). */
        public string comment;

        /** Total price of the order. */
        public int? price = 0;

        /* Delivery method. */
        public Delivery delivery;

        /* Contact details. */
        public Contact contact;

        /* Payments. */
        public IList<Payment> payments = new List<Payment>();

        /** The order's creation timestamp. */
        public long? created;

        /** The order's last modification timestamp. */
        public long? modified;

        /** The ordering user. */
        public User user;

        /** The order's status. */
        public string status;

        /** The order's share-token. */
        public String shareToken;
    }
}
