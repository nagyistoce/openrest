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
        public Item(string id, string restaurantId, string title, string description,
            int price, IList<Variation> variations, Availability availability, bool inactive, string picture)
        {
            this.id = id;
            this.restaurantId = restaurantId;
            this.title = title;
            this.description = description;
            this.variations = variations;
            this.availability = availability;
            this.inactive = inactive;
            this.picture = picture;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Item() { }

        /** The item's unique id. */
        public string id;

        /** The restaurant's id. */
        public string restaurantId;

        /** The item's name. */
        public string title;

        /** The item's one line description. */
        public string description;

        /** The item's price, in "cents". */
        public int? price = 0;

        /** List of possible variations. */
        public IList<Variation> variations = new List<Variation>();

        /** Time windows in which this item is regularly available. */
        public Availability availability = new Availability();

        /** Human readable availability string. */
        public string availabilityStr;

        /** Whether the item is deactivated (i.e. suspended or disabled). */
        public bool? inactive = false;
        
        /**
         * Map of externally-defined item ids referring to this item.
         * For example, the item-id in the restaurant's PoS system.
         * 
         * Developers should use unique keys, e.g. "com.company.product".
         */
        public IDictionary<string, string> externalIds = new Dictionary<string, string>();

        /** The current status. */
        public Status status;

        /** Item picture URL (direct link). */
        public string picture;
    }
}
