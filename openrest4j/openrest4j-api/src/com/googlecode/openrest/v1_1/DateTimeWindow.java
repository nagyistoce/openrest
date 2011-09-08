package com.googlecode.openrest.v1_1;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DateTimeWindow implements Serializable {
    public DateTimeWindow(Date start, Date end, Boolean available) {
        this.start = start;
        this.end = end;
        this.available = available;
    }
    
    public DateTimeWindow(Calendar start, Calendar end, Boolean available) {
        this(new Date(start), new Date(end), available);
    }

    /** Default constructor for JSON deserialization. */
    public DateTimeWindow() {}

    public Calendar start(TimeZone tz) {
        return start.calendar(tz);
    }

    public Calendar end(TimeZone tz) {
        return end.calendar(tz);
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Date start;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Date end;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Boolean available;
    
    private static final long serialVersionUID = 1L;
}
