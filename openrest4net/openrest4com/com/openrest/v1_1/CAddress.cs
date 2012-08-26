using System;
using System.Runtime.InteropServices;
using com.openrest.v1_1;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CAddress : IAddress
    {
        public CAddress(Address address)
        {
            this.address = address;
        }

        public string GetCity()
        {
            return address.city;
        }

        public string GetStreet()
        {
            return address.street;
        }

        public string GetNumber()
        {
            return address.number;
        }

        public string GetApt()
        {
            return address.apt;
        }

        public string GetFloor()
        {
            return address.floor;
        }

        public string GetEntrance()
        {
            return address.entrance;
        }

        public string GetComment()
        {
            return address.comment;
        }

        public ILatLng GetLatLng()
        {
            return new CLatLng(address.latLng);
        }

        private readonly Address address;
    }
}
