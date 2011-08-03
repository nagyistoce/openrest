using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    public class CNullableInt : INullableInt
    {
        public CNullableInt(int? i)
        {
            this.i = i;
        }

        public bool HasValue()
        {
            return i.HasValue;
        }

        public int GetValue()
        {
            return i.Value;
        }

        private readonly int? i;
    }
}
