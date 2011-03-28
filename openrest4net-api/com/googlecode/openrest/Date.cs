using System;

namespace com.googlecode.openrest
{
    class Date
    {
        public Date(int year, int month, int day, int hour, int minute)
        {
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
            this.minute = minute;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        internal Date() { }

        public int? year;
        /** 1-based. */
        public int? month;
        public int? day;
        public int? hour;
        public int? minute;
    }
}
