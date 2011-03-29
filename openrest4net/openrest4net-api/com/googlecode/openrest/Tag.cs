using System;
using System.Collections.Generic;

namespace com.googlecode.openrest
{
    /**
     * A set of items that go together, e.g. "sides", "drinks", "toppings".
     * @author DL
     */
    public class Tag
    {
        public Tag(string id, string restaurantId, string title, IList<string> itemIds)
        {
            this.id = id;
            this.restaurantId = restaurantId;
            this.title = title;
            this.itemIds = itemIds;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Tag() { }

        /** The tag's unique id. */
        public string id;

        /** The restaurant's id. */
        public string restaurantId;

        /** The tag's name, e.g. "drink", "sides". */
        public string title;

        /** Item ids. */
        public IList<string> itemIds = new List<string>();
    }
}
