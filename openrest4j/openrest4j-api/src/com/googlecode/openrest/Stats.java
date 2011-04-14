package com.googlecode.openrest;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats implements Serializable {
    public static final String STATS_GRANULARITY_DAY = "day";
    public static final String STATS_GRANULARITY_WEEK = "week";
    public static final String STATS_GRANULARITY_MONTH = "month";
    public static final String STATS_GRANULARITY_YEAR = "year";

    public Stats(Date date, Integer count, Integer total) {
        this.date = date;
        this.count = count;
        this.total = total;
    }

    /** Default constructor for JSON deserialization. */
    public Stats() {}

    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    public Date date;

    @JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer count = 0;

    @JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer total = 0;

    private static final long serialVersionUID = 1L;
}
