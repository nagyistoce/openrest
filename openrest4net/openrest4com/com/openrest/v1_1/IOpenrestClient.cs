using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IOpenrestClient
    {
        void Init(string restaurantUri, string accessToken);

        /*
        IMenu GetMenu();
        IOrders GetNewOrders();
        IOrder SetOrderStatus(string orderId, string status);
        */
    }
}
