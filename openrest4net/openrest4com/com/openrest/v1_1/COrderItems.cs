using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class COrderItems : IOrderItems
    {
        public COrderItems(IList<OrderItem> orderItems)
        {
            cOrderItems = new COrderItem[orderItems.Count];
            int i = 0;
            foreach (OrderItem orderItem in orderItems)
            {
                cOrderItems[i++] = new COrderItem(orderItem);
            }
        }

        public int GetCount()
        {
            return cOrderItems.Length;
        }

        public IOrderItem Get(int i)
        {
            return cOrderItems[i];
        }

        private COrderItem[] cOrderItems;
    }
}
