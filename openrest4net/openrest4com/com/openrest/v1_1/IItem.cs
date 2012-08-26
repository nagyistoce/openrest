using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IItem
    {
        string GetId();
        string GetRestaurantId();
        //string GetTitle();
        //string GetDescription();
        int GetPrice();
        IVariations GetVariations();
        //IAvailability GetAvailability();
        //string GetAvailabilityStr();
        bool GetInactive();
        //Status GetStatus();
        string GetPicture();
        string GetExternalId(string key);
    }
}
