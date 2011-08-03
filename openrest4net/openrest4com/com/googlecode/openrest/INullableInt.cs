using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface INullableInt
    {
        bool HasValue();
        int GetValue();
    }
}
