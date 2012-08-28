using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IRestaurantFullInfo : IOpenrestObject
    {
        IRestaurant GetRestaurant();
        IMenu GetMenu();
        ICharges GetCharges();
        IDistributor GetDistributor();
        IChain GetChain();
        string[] GetFbAdmins();
    }
}
