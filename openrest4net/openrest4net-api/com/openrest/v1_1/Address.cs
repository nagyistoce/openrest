using System;
using Newtonsoft.Json;
using System.Text;

namespace com.openrest.v1_1
{
    public class Address
    {
        public Address(string country, string city, string street, string number, string apt,
            string floor, string entrance, string comment, LatLng latLng, string countryCode, string postalCode)
        {
            this.country = country;
            this.city = city;
            this.street = street;
            this.number = number;
            this.apt = apt;
            this.floor = floor;
            this.entrance = entrance;
            this.comment = comment;
            this.latLng = latLng;
            this.countryCode = countryCode;
            this.postalCode = postalCode;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Address() { }

        public string GetStreetAddress(bool useCountry)
        {
            StringBuilder builder = new StringBuilder();

            builder.Append(street).Append(' ').Append(number);
            builder.Append(", ").Append(city);

            if ((postalCode != null) && (postalCode.Length != 0))
            {
                builder.Append(" ").Append(postalCode);
            }

            if ((useCountry) && (country != null) && (country.Length != 0))
            {
                builder.Append(", ").Append(country);
            }

            return builder.ToString();
        }

        public string country;
        public string city;
        public string street;
        public string number;
        public string apt;
        public string floor;
        public string entrance;
        public string comment;
        public LatLng latLng;

        /**
         * Two letter country code.
         * @see http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
         */
        public string countryCode;
        public string postalCode;
    }
}
