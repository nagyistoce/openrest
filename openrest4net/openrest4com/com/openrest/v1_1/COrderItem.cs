using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class COrderItem : IOrderItem
    {
        public COrderItem(OrderItem orderItem)
        {
            this.orderItem = orderItem;
        }

        public string GetItemId()
        {
            return orderItem.itemId;
        }

        public IVariations GetVariations()
        {
            return new CVariations(orderItem.variations);
        }

        public IVariationsChoices GetVariationsChoices()
        {
            return new CVariationsChoices(orderItem.variationsChoices);
        }

        public string GetComment()
        {
            return orderItem.comment;
        }

        public int GetPrice()
        {
            return orderItem.price.Value;
        }

        public int GetCount()
        {
            return orderItem.count.Value;
        }

        private readonly OrderItem orderItem;
    }
}
