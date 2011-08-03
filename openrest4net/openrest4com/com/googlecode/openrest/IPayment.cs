using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IPayment
    {
        string GetPaymentType();
        int GetAmount();
        ICreditCard GetCard();
    }
}
