using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    public class COpenrestClient : IOpenrestClient
    {
        public COpenrestClient() {}

        public void Init(string restaurantUri, string accessToken)
        {
            client = new OpenrestClient(new Uri(restaurantUri), accessToken);
        }

        public IMenu GetMenu()
        {
            return new CMenu(client.Menu);
        }

        public IOrders GetNewOrders()
        {
            return new COrders(client.GetOrders(Order.ORDER_STATUS_NEW, null, null, null, "asc", null, true));
        }

        public IOrder SetOrderStatus(string orderId, string status)
        {
            return new COrder(client.SetOrderStatus(orderId, status));
        }

        private OpenrestClient client;
    }
}
