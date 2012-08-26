using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
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
