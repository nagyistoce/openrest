using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IOpenrestClient
    {
        void Init(string apiUri);

        IRestaurantFullInfo GetRestaurantFullInfo(string restaurantId);
        //IOrders QueryOrders(IQueryOrdersRequest request);
        //IOrder SetOrderStatus(ISetOrderStatusRequest request);
    }
}
