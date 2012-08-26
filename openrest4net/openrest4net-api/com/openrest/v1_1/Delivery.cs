using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class Delivery
    {
        /** Delivery to an address of your choice. */
        public const string DELIVERY_TYPE_DELIVERY = "delivery";
        /** Take-out from the restaurant. */
        public const string DELIVERY_TYPE_TAKEOUT = "takeout";

        /** All delivery methods, in ascending alphabetic order! */
        public static readonly IList<string> ALL_DELIVERY_TYPES =
            new List<string> { DELIVERY_TYPE_DELIVERY, DELIVERY_TYPE_TAKEOUT };

        public Delivery(string type, Address address, int? charge)
        {
            this.type = type;
            this.address = address;
            this.charge = charge;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Delivery() { }

        /** Delivery type. */
        public string type;

        /** Address to deliver to (valid only if type is DELIVERY_TYPE_DELIVERY) */
        public Address address;

        /** The delivery charge (in "cents"). */
        public int? charge = 0;
    }
}
