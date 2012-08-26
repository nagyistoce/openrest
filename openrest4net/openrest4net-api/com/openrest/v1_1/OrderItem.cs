using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class OrderItem
    {
        public OrderItem(string itemId, IList<Variation> variations, IList<IList<OrderItem>> variationsChoices,
            string comment, int price, int count)
        {
            this.itemId = itemId;
            this.variations = variations;
            this.variationsChoices = variationsChoices;
            this.comment = comment;
            this.price = price;
            this.count = count;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public OrderItem() { }

        /** Item id. */
        public string itemId;

        /**
         * The ordered-item's variations.
         * 
         * Submitting an OrderItem with empty variations means the defaults should be assumed
         * for variationsChoices.
         */
        public IList<Variation> variations = new List<Variation>();

        /** The ordered-item's chosen variations. */
        public IList<IList<OrderItem>> variationsChoices = new List<IList<OrderItem>>();

        /** Textual comment regarding the item. */
        public string comment;

        /** Total price of the item and variations. */
        public int? price = 0;

        /** Number of times this order-item was ordered. */
        public int? count = 1;
    }
}
