using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace com.googlecode.openrest
{
    public class COrders
    {
        public COrders() { }

        public COrders(IList<Order> orders)
        {
            cOrders = new COrder[orders.Count];
            int i = 0;
            foreach (Order order in orders)
            {
                cOrders[i] = new COrder(order);
            }
        }

        public int Count()
        {
            return cOrders.Length;
        }

        public COrder Get(int i)
        {
            return cOrders[i];
        }

        private readonly COrder[] cOrders;
    }
}
