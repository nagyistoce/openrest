﻿using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IVariationsChoices
    {
        int GetCount();
        IOrderItems Get(int i);
    }
}