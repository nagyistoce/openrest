using System;
using Newtonsoft.Json;
using System.Collections.Generic;

namespace com.googlecode.openrest
{
    /**
     * An area on a map.
     * @author DL
     */
    public class Area
    {
        public Area(string title, IList<LatLng> polygon)
        {
            this.title = title;
            this.polygon = polygon;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Area() { }

        /** The area's human-readable title. */
        public string title;
    
        /** The area (polygon vertices). */
        public IList<LatLng> polygon = new List<LatLng>();
    }
}