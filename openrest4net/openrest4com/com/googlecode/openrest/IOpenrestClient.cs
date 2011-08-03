using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IOpenrestClient
    {
        void Init(string restaurantUri, string accessToken);
        IOrders GetNewOrders();
        IOrder SetOrderStatus(string orderId, string status);
    }
}
