using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
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
        INullableInt GetTakeoutPacks();
        ICharges GetCharges();
        long GetCreated();
        long GetModified();
        IUser GetUser();
        string GetStatus();
        string GetShareToken();
        string GetAffiliate();
        string GetRef();
    }
}
