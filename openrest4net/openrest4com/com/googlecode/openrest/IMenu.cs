using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
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
