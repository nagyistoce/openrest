package com.openrest.v1_1;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    
    public static final String STATS_GRANULARITY_DAY = "day";
    public static final String STATS_GRANULARITY_WEEK = "week";
    public static final String STATS_GRANULARITY_MONTH = "month";
    public static final String STATS_GRANULARITY_YEAR = "year";
    
    /** All known stats granularities. */
    public static final Set<String> ALL_STATS_GRANULARITIES = new HashSet<String>(Arrays.asList(new String[] {
    		STATS_GRANULARITY_DAY, STATS_GRANULARITY_WEEK, STATS_GRANULARITY_MONTH, STATS_GRANULARITY_YEAR
    }));

    public Stats(Date date, Integer count, Integer total) {
        this.date = date;
        this.count = count;
        this.total = total;
    }

    /** Default constructor for JSON deserialization. */
    public Stats() {}
    
    @Override
	public Object clone() {
    	return new Stats(((date != null) ? (Date)date.clone() : null), count, total);
	}

    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    public Date date;

    @JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer count = 0;

    @JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer total = 0;
}
