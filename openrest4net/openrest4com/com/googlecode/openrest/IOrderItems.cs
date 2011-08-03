using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IOrderItems
    {
        int GetCount();
        IOrderItem Get(int i);
    }
}
