using System.Collections.Generic;
using System;

namespace com.openrest.v1_1
{
    public class Utils
    {
        private Utils() { }

        public static long ToUnixTime(DateTime dateTime)
        {
            return (long) ((dateTime - EPOCH).TotalMilliseconds);
        }

        public static DateTime FromUnixTime(long unixTime)
        {
            return EPOCH.AddMilliseconds(unixTime);
        }

        private static readonly DateTime EPOCH = new DateTime(1970, 1, 1);
    }
}
