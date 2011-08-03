using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IOrderItem
    {
        string GetItemId();
        IVariations GetVariations();
        IVariationsChoices GetVariationsChoices();
        string GetComment();
        int GetPrice();
        int GetCount();
    }
}
