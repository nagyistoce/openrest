using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    public class CLatLng : ILatLng
    {
        public CLatLng(LatLng latLng)
        {
            this.latLng = latLng;
        }

        public double GetLat()
        {
            return latLng.lat.Value;
        }

        public double GetLng()
        {
            return latLng.lng.Value;
        }

        private readonly LatLng latLng;
    }
}
