using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface ITag
    {
        string GetId();
        string GetRestaurantId();
        string GetTitle();
        string[] GetItemIds();
    }
}
