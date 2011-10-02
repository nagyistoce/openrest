package com.googlecode.openrest.v1_1;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Date implements Serializable {
	public Date(Integer year, Integer month, Integer day, Integer hour, Integer minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public Date(Calendar cal) {
        this(cal.get(Calendar.YEAR), 1 + cal.get(Calendar.MONTH) - Calendar.JANUARY,
                cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE));
    }

    /** Default constructor for JSON deserialization. */
    public Date() {}

    public Calendar calendar(TimeZone tz) {
        final Calendar cal = Calendar.getInstance(tz);
        cal.clear();
        cal.set(year.intValue(), Calendar.JANUARY + month.intValue() - 1,
                day.intValue(), hour.intValue(), minute.intValue());
        return cal;
    }
    
    @Override
	public String toString() {
    	return String.format("%04d-%02d-%02d %02d:%02d",
    			year, month, day, hour, minute);
	}

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer year;

    /** 1-based. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer month;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer day;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer hour;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer minute;
    
    private static final long serialVersionUID = 1L;
}
