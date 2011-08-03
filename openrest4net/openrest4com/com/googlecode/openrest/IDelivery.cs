using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IDelivery
    {
        string GetDeliveryType();
        IAddress GetAddress();
        int GetCharge();
    }
}
