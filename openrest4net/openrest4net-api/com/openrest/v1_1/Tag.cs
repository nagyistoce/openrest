using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    /**
     * A set of items that go together, e.g. "sides", "drinks", "toppings".
     * @author DL
     */
    public class Tag
    {
	    /** Inclusive: tag refers to given items. */
	    public const string TAG_MODE_INCLUDE = "include";
        /** Exclusive: tag refers to anything but the given items. */
        public const string TAG_MODE_EXCLUDE = "exclude";
    
        /** All known tag modes */
        public static readonly IList<string> ALL_TAG_MODES = new List<String> { TAG_MODE_INCLUDE, TAG_MODE_EXCLUDE };

        public Tag(string id, string restaurantId, IDictionary<string, string> title, IList<string> itemIds,
            IDictionary<string, string> properties)
        {
            this.id = id;
            this.restaurantId = restaurantId;
            this.title = title;
            this.itemIds = itemIds;
            this.properties = properties;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Tag() { }

        /** The tag's unique id. */
        public string id;

        /** The restaurant's id. */
        public string restaurantId;

        /** The tag's name in various locales, e.g. "drink", "sides". */
        public IDictionary<string, string> title = new Dictionary<string, string>();

        /** Item ids. */
        public IList<string> itemIds = new List<string>();

        /**
         * Map of user-defined extended properties. Developers should use unique
         * keys, e.g. "com.googlecode.openrestext".
         */
        public IDictionary<string, string> properties = new Dictionary<string, string>();
    }
}
