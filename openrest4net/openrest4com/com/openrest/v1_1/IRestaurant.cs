using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IRestaurant : IOrganization
    {
        string GetDistributorId();
        string GetChainId();
        string GetCurrency();
        string[] GetPaymentTypes();
        string GetState();
    }
}
