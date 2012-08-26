using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class Category
    {
        public Category(string id, string restaurantId, IDictionary<string, string> title, IDictionary<string, string> description,
            string parentCategoryId, IList<string> itemIds, double priority)
        {
            this.id = id;
            this.restaurantId = restaurantId;
            this.title = title;
            this.description = description;
            this.parentCategoryId = parentCategoryId;
            this.itemIds = itemIds;
            this.priority = priority;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Category() { }

        /** The category's unique id. */
        public string id;

        /** The restaurant's id. */
        public string restaurantId;

        /** The category's title in various locales. */
        public IDictionary<string, string> title = new Dictionary<string, string>();

        /** The category's description in various locales. */
        public IDictionary<string, string> description = new Dictionary<string, string>();

        /** The parent category's id. */
        public string parentCategoryId;

        /** The item-ids in this category. */
        public IList<string> itemIds = new List<string>();

        /** Order priority. Higher means first in the list. */
        public double? priority = 0.0;
    }
}
