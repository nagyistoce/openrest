using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IUser
    {
        string GetId();
        string GetIpAddress();
        string GetFwdIpAddresses();
    }
}
