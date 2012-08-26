using System;
using System.Collections.Generic;
using com.openrest.v1_1;

namespace com.openrest.v1_1
{
    /**
     * An item that can be ordered, e.g. a main dish ("hamburger"), a side ("fries")
    * or a dish variation ("well done").
    * @author DL
    */
    public class Item
    {
        public Item(string id, IDictionary<string, string> externalIds, string restaurantId,
            IDictionary<string, string> title, IDictionary<string, string> description,
            int price, IList<Variation> variations, Availability availability, bool inactive, IList<string> labels,
            IDictionary<string, string> properties)
        {
            this.id = id;
            this.externalIds = externalIds;
            this.restaurantId = restaurantId;
            this.title = title;
            this.description = description;
            this.price = price;
            this.variations = variations;
            this.availability = availability;
            this.inactive = inactive;
            this.labels = labels;
            this.properties = properties;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Item() { }

        /** The item's unique id. */
        public string id;

        /** The restaurant's id. */
        public string restaurantId;

        /** The item's title in various locales. */
        public IDictionary<string, string> title = new Dictionary<string, string>();

        /** The item's one line description in various locales. */
        public IDictionary<string, string> description = new Dictionary<string, string>();

        /** The item's price, in "cents". */
        public int? price = 0;

        /** List of possible variations. */
        public IList<Variation> variations = new List<Variation>();

        /** Time windows in which this item is regularly available. */
        public Availability availability = new Availability();

        /** Whether the item is deactivated (i.e. suspended or disabled). */
        public bool? inactive = false;

        /** Item picture URL (direct link). */
        public string picture;
        
        /**
         * Map of externally-defined item ids referring to this item.
         * For example, the item-id in the restaurant's PoS system.
         * 
         * Developers should use unique keys, e.g. "com.company.product".
         */
        public IDictionary<string, string> externalIds = new Dictionary<string, string>();

        /** The item's labels, e.g. "new", "spicy". */
        public IList<string> labels = new List<string>();

        /**
         * Map of user-defined extended properties. Developers should use unique
         * keys, e.g. "com.googlecode.openrestext".
         */
        public IDictionary<string, string> properties = new Dictionary<string, string>();

        /** The item's Openrest rank (higher is better). */
        public double? rank = 0.0;
    }
}
