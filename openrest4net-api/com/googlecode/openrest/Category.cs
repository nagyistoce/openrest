using System;
using System.Collections.Generic;

namespace com.googlecode.openrest
{
    class Category
    {
        public Category(string id, string restaurantId, string title, string description,
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
        internal Category() { }

        /** The category's unique id. */
        public string id;

        /** The restaurant's id. */
        public string restaurantId;

        /** The category's title. */
        public string title;

        /** The category's description. */
        public string description;

        /** The parent category's id. */
        public string parentCategoryId;

        /** The item-ids in this category. */
        public IList<string> itemIds = new List<string>();

        /** Order priority. Higher means first in the list. */
        public double? priority = 0.0;
    }
}
