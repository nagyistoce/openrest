using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface IOrganization : IOpenrestObject
    {
        string GetId();
        IStringMap GetExternalIds();
        INullableLong GetCreated();
        INullableLong GetModified();
        IStringMap GetTitle();
        IStringMap GetDescription();
        //IColorScheme GetColorScheme();
        IContact GetContact();
        // GetExternalContacts();
        IAddress GetAddress();
        string GetTimezone();
        string GetLocale();
        string[] GetLocales();
        //string GetLink();
        //string GetDomain();
        //string[] GetAltDomains();
        //string GetPicture();
        //string GetIcon();
        //string GetNoImagePicture();
        // GetApps();
        // GetSeo();
        IStringMap GetProperties();
    }
}
