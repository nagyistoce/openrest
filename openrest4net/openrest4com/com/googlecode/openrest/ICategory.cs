using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface ICategory
    {
        string GetId();
        string GetRestaurantId();
        string GetTitle();
        string GetDescription();
        string GetParentCategoryId();
        string[] GetItemIds();
        double GetPriority();
    }
}
