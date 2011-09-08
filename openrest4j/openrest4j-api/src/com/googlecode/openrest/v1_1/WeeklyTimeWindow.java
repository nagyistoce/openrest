package com.googlecode.openrest.v1_1;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeeklyTimeWindow implements Serializable {
    /** Default constructor for JSON deserialization. */
    public WeeklyTimeWindow() {}

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer minuteOfWeek;
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer durationMins;
    
    private static final long serialVersionUID = 1L;
}
