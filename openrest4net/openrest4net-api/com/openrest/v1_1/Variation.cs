using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    /**
     * A possible modification to an item, e.g. "choice of sides" for a happy-meal
     * or "degree of cooking" for a hamburger.
     * @author DL
     */
    public class Variation
    {
        public const string VARIATION_DISPLAY_TYPE_DIFF = "diff";
        public const string VARIATION_DISPLAY_TYPE_CHOICE = "choice";
        /**
         * Variations that are not displayed to the customer, just to the restaurant.
         * Similar to a hidden field in an HTML form.
         */
        public const string VARIATION_DISPLAY_TYPE_HIDDEN = "hidden";

        public Variation(IDictionary<string, string> title, string tagId, int minNumAllowed, int maxNumAllowed,
            IDictionary<string, int> prices, IList<string> defaults, string displayType)
        {
            this.title = title;
            this.tagId = tagId;
            this.minNumAllowed = minNumAllowed;
            this.maxNumAllowed = maxNumAllowed;
            this.prices = prices;
            this.defaults = defaults;
            this.displayType = displayType;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Variation() { }

        /** The variations's name in various locales, e.g. "sides", "degree of cooking". */
        public IDictionary<string, string> title = new Dictionary<string, string>();

        /** The set's name, e.g. "drink", "sides". */
        public string tagId;

        /** Minimum number of items to select. */
        public int? minNumAllowed = 0;

        /** Maximum number of items to select. */
        public int? maxNumAllowed = Int32.MaxValue;

        /** Items' base prices. Non-referenced items are free by default. */
        public IDictionary<string, int> prices = new Dictionary<string, int>();

        /** Default selected item ids. */
        public IList<string> defaults = new List<string>();

        /** Display type for human-readable printing. */
        public string displayType = VARIATION_DISPLAY_TYPE_CHOICE;
    }
}
