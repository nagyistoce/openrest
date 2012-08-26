using System;

namespace com.openrest.v1_1
{
    public class WeeklyTimeWindow
    {
        public WeeklyTimeWindow(int minuteOfWeek, int durationMins)
        {
            this.minuteOfWeek = minuteOfWeek;
            this.durationMins = durationMins;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public WeeklyTimeWindow() { }

        public int? minuteOfWeek;
        public int? durationMins;
    }
}
