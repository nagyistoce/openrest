using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class Order
    {
        /**
         * The order has been submitted by the user, and awaits her final approval.
         * The restaurant is not made of the order.
         */
        public const string ORDER_STATUS_SUBMITTED = "submitted";
        /**
         * The restaurant required further confirmation before the order is submitted
         * (e.g. validating the user's phone number by SMS).
         */
        public const string ORDER_STATUS_PENDING = "pending";
        /** The order has been approved by the user, and awaits processing by the restaurant. */
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

        public IDictionary<string, string> externalIds = new Dictionary<string, string>();

        /** The restaurant's unique id. */
        public string restaurantId;

        /** The order's locale. */
        public string locale;

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

        /**
         * Number of "takeout packs" (e.g. cutlery and condiments) to deliver with the order.
         * For environmental reasons, clients should be encouraged to set this to 0.
         */
        public int? takeoutPacks;

        /**
	     * Extra charges or discounts associated with the order, ordered by priority
	     * in descending order.
	     */
        public IList<Charge> charges = new List<Charge>();

        /** The order's creation timestamp. */
        public long? created;

        /** The order's last modification timestamp. */
        public long? modified;

        /** The ordering user. */
        public User user;

        /** The ordering club member. */
        public ClubMember clubMember;

        /** The order's status. */
        public string status;

        /** The order's share-token. */
        public String shareToken;

        /** Affiliate-id, for orders that came through affiliate marketing. */
        public string affiliate;

        /**
         * Affiliate-specific referrer-id for performance tracking, e.g. 
         * Facebook campaign id, iPhone application id, self-service station id.
         * 
         * TODO: "ref" is reserved in C#
         */
        //public string ref;

        /**
         * Whether or not the order was submitted and should be displayed with a
         * legacy "2-level hierarchy".
         */
        public bool? legacyHierarchy = false;
    
        /** Change log for this order. */
        public IList<LogEntry> log = new List<LogEntry>();
    
        /** The order in HTML format. */
        public String html;
    }
}
