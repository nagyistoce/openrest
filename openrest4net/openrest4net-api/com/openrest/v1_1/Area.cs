using System;
using System.Collections.Generic;
using com.openrest.v1_1;

namespace com.openrest.v1_1
{
    /**
     * An area on a map.
     * @author DL
     */
    public class Area
    {
        public Area(IDictionary<string, string> title, IList<LatLng> polygon)
        {
            this.title = title;
            this.polygon = polygon;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Area() { }

        /** The area's human-readable title in various locales. */
        public IDictionary<string, string> title = new Dictionary<string, string>();
    
        /** The area (polygon vertices). */
        public IList<LatLng> polygon = new List<LatLng>();
    }
}