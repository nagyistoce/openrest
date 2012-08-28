using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface ICategory
    {
        string GetId();
        string GetRestaurantId();
        IStringMap GetTitle();
        IStringMap GetDescription();
        string GetParentCategoryId();
        string[] GetItemIds();
        double GetPriority();
    }
}
