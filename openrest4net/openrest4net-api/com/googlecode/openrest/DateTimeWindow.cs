using System;

namespace com.googlecode.openrest
{
    class DateTimeWindow
    {
        public DateTimeWindow(Date start, Date end, bool available)
        {
            this.start = start;
            this.end = end;
            this.available = available;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        internal DateTimeWindow() { }

        public Date start;
        public Date end;
        public bool? available;
    }
}
