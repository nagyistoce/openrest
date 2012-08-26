using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
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
