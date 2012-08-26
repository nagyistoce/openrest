using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class Stats
    {
        public const string STATS_GRANULARITY_DAY = "day";
        public const string STATS_GRANULARITY_WEEK = "week";
        public const string STATS_GRANULARITY_MONTH = "month";
        public const string STATS_GRANULARITY_YEAR = "year";

        public Stats(Date date, int count, int total)
        {
            this.date = date;
            this.count = count;
            this.total = total;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Stats() { }

        public Date date;
        public int? count = 0;
        public int? total = 0;
    }
}
