using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface ICoupon
    {
        string GetCouponType();
        string GetTitle();
        string GetDescription();
        int GetMaxNumAllowed();
        bool GetOthersAllowed();
    }
}
