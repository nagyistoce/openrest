using System;
using System.Collections.Generic;

namespace com.googlecode.openrest
{
    public class COpenrestClient
    {
        public COpenrestClient() {}

        public void Init(string restaurantUri, string accessToken)
        {
            client = new OpenrestClient(new Uri(restaurantUri), accessToken);
        }

        public COrders GetNewOrders()
        {
            return new COrders(client.GetOrders(Order.ORDER_STATUS_NEW, null, null, null, "asc", null, true));
        }

        private OpenrestClient client;
    }
}
