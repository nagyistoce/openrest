using System;

namespace com.openrest.v1_1
{
    public class DateTimeWindow
    {
        public DateTimeWindow(Date start, Date end, bool available)
        {
            this.start = start;
            this.end = end;
            this.available = available;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public DateTimeWindow() { }

        public Date start;
        public Date end;
        public bool? available;
    }
}
