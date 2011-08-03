using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IAddress
    {
        string GetCity();
        string GetStreet();
        string GetNumber();
        string GetApt();
        string GetFloor();
        string GetEntrance();
        string GetComment();
        ILatLng GetLatLng();
    }
}
