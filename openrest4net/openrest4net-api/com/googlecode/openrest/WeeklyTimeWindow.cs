﻿using System;

namespace com.googlecode.openrest
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