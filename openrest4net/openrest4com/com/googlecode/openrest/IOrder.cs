using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IOrder
    {
        string GetId();
        string GetRestaurantId();
        IOrderItems GetOrderItems();
        string GetComment();
        int GetPrice();
        IDelivery GetDelivery();
        IContact GetContact();
        IPayments GetPayments();
        long GetCreated();
        long GetModified();
        IUser GetUser();
        string GetStatus();
        string GetShareToken();
    }
}
