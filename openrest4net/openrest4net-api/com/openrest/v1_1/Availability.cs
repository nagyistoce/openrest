using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class Availability
    {
        public Availability(IList<WeeklyTimeWindow> weekly, IList<DateTimeWindow> exceptions)
        {
            this.weekly = weekly;
            this.exceptions = exceptions;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Availability() { }

        public IList<WeeklyTimeWindow> weekly = new List<WeeklyTimeWindow>();
        public IList<DateTimeWindow> exceptions = new List<DateTimeWindow>();
    }
}
