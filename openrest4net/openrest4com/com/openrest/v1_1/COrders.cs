using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class COrders : IOrders
    {
        public COrders() { }

        public COrders(IList<Order> orders)
        {
            cOrders = new COrder[orders.Count];
            int i = 0;
            foreach (Order order in orders)
            {
                cOrders[i++] = new COrder(order);
            }
        }

        public int GetCount()
        {
            return cOrders.Length;
        }

        public IOrder Get(int i)
        {
            return cOrders[i];
        }

        private readonly COrder[] cOrders;
    }
}
