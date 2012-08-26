using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class OrdersResponse
    {
        public IList<Order> results = new List<Order>();

        /** Empty constructor required for initialization from JSON-encoded string. */
        public OrdersResponse()
        {
        }

        public OrdersResponse(IList<Order> results)
        {
            this.results = results;
        }
    }
}
