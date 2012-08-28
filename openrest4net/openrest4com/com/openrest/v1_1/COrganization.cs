using System;
using System.Runtime.InteropServices;
using com.openrest.v1_1;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class COrganization : COpenrestObject, IOrganization
    {
        private readonly Organization organization;

        public COrganization(Organization organization) :  base(organization)
        {
            this.organization = organization;
        }

        public string GetId()
        {
            return organization.id;
        }

        public IStringMap GetExternalIds()
        {
            return new CStringMap(organization.externalIds);
        }

        public INullableLong GetCreated()
        {
            return new CNullableLong(organization.created);
        }

        public INullableLong GetModified()
        {
            return new CNullableLong(organization.modified);
        }

        public IStringMap GetTitle()
        {
            return new CStringMap(organization.title);
        }

        public IStringMap GetDescription()
        {
            return new CStringMap(organization.description);
        }

        public IContact GetContact()
        {
            return new CContact(organization.contact);
        }

        public IAddress GetAddress()
        {
            return new CAddress(organization.address);
        }

        public string GetTimezone()
        {
            return organization.timezone;
        }

        public string GetLocale()
        {
            return organization.locale;
        }

        public string[] GetLocales()
        {
            return (string[]) organization.locales;
        }

        public IStringMap GetProperties()
        {
            return new CStringMap(organization.properties);
        }
    }
}
