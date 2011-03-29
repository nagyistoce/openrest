using System;

namespace com.googlecode.openrest
{
    public class LatLng
    {
        public LatLng(double lat, double lng)
        {
            this.lat = lat;
            this.lng = lng;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public LatLng() { }

        public double? lat;
        public double? lng;
    }
}
