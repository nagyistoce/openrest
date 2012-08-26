using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class Restaurant : Organization
    {
        public const string TYPE = "restaurant";

	    /** Restaurant system is used for demonstration only. Orders will not be handled. */
        public const string STATE_DEMO = "demo";
        /** Restaurant system is under construction. Orders will not be handled. */
        public const string STATE_UNDER_CONSTRUCTION = "under_construction";
        /** Restaurant system is operational. Orders are accepted. */
        public const string STATE_OPERATIONAL = "operational";
        /** Restaurant system is permanently closed. Orders will not be handled. */
        public const string STATE_CLOSED = "closed";
        /** Restaurant system is operational, but only used to display information. */
        public const string STATE_INFO = "info";


        /** The restaurant's welcome message. */
        public const string MESSAGE_TYPE_WELCOME = "welcome";
        /** The restaurant's order confirmation message. */
        public const string MESSAGE_TYPE_ORDER_CONFIRMATION = "order_confirmation";

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Restaurant() : base(TYPE) { }

        /** The distributor in charge of this restaurant. */
        public string distributorId;
    
        /** The chain this restaurant is part of. */
        public string chainId;
    
        /** Maps message types (e.g. MESSAGE_TYPE_WELCOME) to their text in various locales. */
        public IDictionary<string, IDictionary<string, string>> messages = new Dictionary<string, IDictionary<string, string>>();

        /** Restaurant availability. */
        public Availability openTimes = new Availability();

        /** Deliveries availability. */
        public Availability deliveryTimes = new Availability();

        /** Whether the restaurant is deactivated (i.e. suspended or disabled). */
        public bool? inactive = false;

        /** Information regarding the different delivery destinations. */
        public IList<DeliveryInfo> deliveryInfos = new List<DeliveryInfo>();

        /** The restaurant's currency (ISO 4217). */
        public string currency;
    
        /** Available payment methods. */
        public IList<string> paymentTypes = new List<string>();
    
        /**
         * Maps credit card networks (e.g. "visa", "amex" etc) to the information
         * required to clear cards of that network.
         * 
         * Networks that do not appear here are not supported by the restaurant.
         * 
         * For the complete list of credit card networks, see
         * http://code.google.com/p/creditcard/
         */
        public IDictionary<string, CardInfo> cardInfos = new Dictionary<string, CardInfo>();

        /**
         * Maps available payment types to minimal charge allowed per payment, e.g.
         * "credit cards can only be used for paying $5 or more". Non-referenced
         * payment types have zero minimum by default.
         */
        public IDictionary<string, int> minPayments = new Dictionary<string, int>();

        public string state = STATE_OPERATIONAL;
    
        /**
         * Maps feature-IDs to their values. The values correspond to how strongly the feature
         * is relevant for the restaurant, which influences its position in search results.
         * 
         * For example, a restaurant with "hamburger" feature = 3.7 will appear before a
         * restaurant with the same feature = 2.3 when customers search for hamburgers.
         */
        public IDictionary<string, double> features = new Dictionary<string, double>();
    
        /**
         * Whether or not the restaurant's orders should be submitted / displayed
         * with a legacy "2-level hierarchy".
         */
        public bool? legacyHierarchy = false;
    
        /** The restaurant's Openrest rank (higher is better). */
        public double? rank;
    }
}
