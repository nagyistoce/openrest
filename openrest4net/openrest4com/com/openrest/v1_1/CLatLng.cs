using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CLatLng : ILatLng
    {
        private readonly LatLng latLng;

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
    }
}
