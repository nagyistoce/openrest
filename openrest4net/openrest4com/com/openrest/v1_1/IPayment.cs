using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
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
