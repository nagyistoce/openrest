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
            Address address, string welcomeMessage, string confirmationMessage,
            ColorScheme colorScheme, Availability openTimes, Availability deliveryTimes,
            bool inactive, IList<DeliveryInfo> deliveryInfos, string timezone,
            IList<string> paymentTypes, IDictionary<string, int> minPayments,
            string link, string picture, string icon, IDictionary<string, string> properties)
        {
            this.id = id;
            this.name = name;
            this.description = description;
            this.contact = contact;
            this.address = address;
            this.welcomeMessage = welcomeMessage;
            this.confirmationMessage = confirmationMessage;
            this.colorScheme = colorScheme;
            this.openTimes = openTimes;
            this.deliveryTimes = deliveryTimes;
            this.inactive = inactive;
            this.deliveryInfos = deliveryInfos;
            this.timezone = timezone;
            this.paymentTypes = paymentTypes;
            this.minPayments = minPayments;
            this.link = link;
            this.picture = picture;
            this.icon = icon;
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

        /** The address of this restaurant. */
        public Address address;

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

        /** Information regarding the different delivery destinations. */
        public IList<DeliveryInfo> deliveryInfos = new List<DeliveryInfo>();

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

        /** Official restaurant web-site URL. */
        public string link;

        /** Restaurant picture URL (direct link). */
        public string picture;
    
        /** Restaurant icon URL (direct link). */
        public string icon;
    
        /**
         * Map of user-defined extended properties. Developers should use unique
         * keys, e.g. "com.googlecode.openrestext".
         */
        public IDictionary<string, string> properties = new Dictionary<string, string>();
    }
}
