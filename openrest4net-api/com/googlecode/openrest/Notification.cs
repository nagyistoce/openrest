using System;
using System.Collections.Generic;

namespace com.googlecode.openrest
{
    class Notification
    {
        /** Triggered when a new order is unhandled for some duration. */
        public const string NOTIFICATION_TYPE_DELAYED_ORDER = "delayed_order";
        /** Triggered when no one checks for existence of new orders for some duration. */
        public const string NOTIFICATION_TYPE_OFFLINE = "offline";

        /** All payment methods, in ascending alphabetic order! */
        public static readonly IList<string> ALL_NOTIFICATION_TYPES =
            new List<string> { NOTIFICATION_TYPE_DELAYED_ORDER, NOTIFICATION_TYPE_OFFLINE };

        public Notification(string restaurantId, string type, Contact contact, int durationMins)
        {
            this.restaurantId = restaurantId;
            this.type = type;
            this.contact = contact;
            this.durationMins = durationMins;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        internal Notification() { }

        public string restaurantId;
        public string type;
        public Contact contact;
        public int? durationMins = 0;
    }
}
