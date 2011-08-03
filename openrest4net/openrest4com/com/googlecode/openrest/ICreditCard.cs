using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface ICreditCard
    {
        string GetNumber();
        INullableInt GetExpireMonth();
        INullableInt GetExpireYear();
        string GetHolderId();
        string GetHolderName();
        bool GetAnonymized();
    }
}
