using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IContact
    {
        string GetFirstName();
        string GetLastName();
        string GetEmail();
        string GetPhone();
    }
}
