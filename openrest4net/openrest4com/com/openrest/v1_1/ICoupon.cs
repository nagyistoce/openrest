using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface ICoupon
    {
        string GetCouponType();
        //string GetTitle();
        //string GetDescription();
        int GetMaxNumAllowed();
        bool GetOthersAllowed();
    }
}
