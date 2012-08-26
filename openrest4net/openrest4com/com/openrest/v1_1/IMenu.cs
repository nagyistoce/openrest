using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IMenu
    {
        IItems GetItems();
        ITags GetTags();
        ICategories GetCategories();
    }
}
