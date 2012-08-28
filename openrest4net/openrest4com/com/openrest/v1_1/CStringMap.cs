using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CStringMap : IStringMap
    {
        private readonly IDictionary<string, string> map;

        public CStringMap(IDictionary<string, string> map)
        {
            this.map = map;
        }

        public string[] GetKeys()
        {
            return (string[]) map.Keys;
        }

        public string Get(string key)
        {
            return (map.ContainsKey(key) ? map[key] : null);
        }
    }
}
