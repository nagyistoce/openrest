using System;
using System.Collections.Generic;

namespace com.googlecode.openrest
{
    /**
     * Restaurant information.
     * @author DL
     */
    public class Restaurant
    {
        public Restaurant(string id, string name, string description, Contact contact,
            int deliveryCharge, int minOrderPrice, Address address,
            IList<LatLng> deliveryArea, string welcomeMessage, string confirmationMessage,
            ColorScheme colorScheme, Availability openTimes, Availability deliveryTimes,
            bool inactive, IList<string> deliveryTypes, IDictionary<string, int> deliveryMins,
            string timezone, IList<string> paymentTypes, IDictionary<string, int> minPayments,
            string link, IDictionary<string, string> properties)
        {
            this.id = id;
            this.name = name;
            this.description = description;
            this.contact = contact;
            this.deliveryCharge = deliveryCharge;
            this.minOrderPrice = minOrderPrice;
            this.address = address;
            this.deliveryArea = deliveryArea;
            this.welcomeMessage = welcomeMessage;
            this.confirmationMessage = confirmationMessage;
            this.colorScheme = colorScheme;
            this.openTimes = openTimes;
            this.deliveryTimes = deliveryTimes;
            this.inactive = inactive;
            this.deliveryTypes = deliveryTypes;
            this.deliveryMins = deliveryMins;
            this.timezone = timezone;
            this.paymentTypes = paymentTypes;
            this.minPayments = minPayments;
            this.link = link;
            this.properties = properties;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Restaurant() { }

        /** The restaurant's unique id. */
        public string id;

        /** The restaurant's name. */
        public string name;

        /** The restaurant's description or tagline. */
        public string description;

        /** The restaurant's contact. */
        public Contact contact;

        /** The delivery charge (in "cents"). */
        public int? deliveryCharge = 0;

        /** The minimum allowed order price (in "cents"). */
        public int? minOrderPrice = 0;

        /** The address of this restaurant. */
        public Address address;

        /** The delivery area (polygon vertices). */
        public IList<LatLng> deliveryArea = new List<LatLng>();

        /** The restaurant's welcome message. */
        public string welcomeMessage;

        /** The default order confirmation message. */
        public string confirmationMessage;

        /** The color scheme. */
        public ColorScheme colorScheme;

        /** Restaurant availability. */
        public Availability openTimes = new Availability();

        /** Deliveries availability. */
        public Availability deliveryTimes = new Availability();

        /** Whether the restaurant is deactivated (i.e. suspended or disabled). */
        public bool? inactive = false;

        /** Available delivery methods. */
        public IList<string> deliveryTypes = Delivery.ALL_DELIVERY_TYPES;

        /**
         * Maps delivery methods to delivery times (maximum number of minutes till
         * order arrives).
         */
        public IDictionary<string, int> deliveryMins = new Dictionary<string, int>();

        /** The current status. */
        public Status status;

        /** The current delivery status. */
        public Status deliveryStatus;

        /** Human readable status string. */
        public string openTimesStr;

        /** Human readable delivery status string. */
        public string deliveryTimesStr;

        /**
         * The restaurant's timezone.
         * @see http://en.wikipedia.org/wiki/List_of_tz_database_time_zones
         */
        public string timezone;

        /** Available payment methods. */
        public IList<string> paymentTypes = Payment.ALL_PAYMENT_TYPES;

        /**
         * Maps available payment types to minimal charge allowed per payment, e.g.
         * "credit cards can only be used for paying $5 or more". Non-referenced
         * payment types have zero minimum by default.
         */
        public IDictionary<string, int> minPayments = new Dictionary<string, int>();

        /** Official restaurant web-site. */
        public string link;
    
        /**
         * Map of user-defined extended properties. Developers should use unique
         * keys, e.g. "com.googlecode.openrestext".
         */
        public IDictionary<string, string> properties = new Dictionary<string, string>();
    }
}
