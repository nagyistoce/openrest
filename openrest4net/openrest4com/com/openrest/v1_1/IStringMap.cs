using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IStringMap
    {
        string[] GetKeys();
        string Get(string key);
    }
}
