package com.googlecode.openrest;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Status implements Serializable {
    /** Available. */
    public static final String STATUS_AVAILABLE = "available";
    /** Unavailable. */
    public static final String STATUS_UNAVAILABLE = "unavailable";

    public Status(String status, java.util.Date until) {
        this.status = status;
        this.until = ((until != null) ? until.getTime() : null);
    }

    /** Default constructor for JSON deserialization. */
    public Status() {}

    public java.util.Date until() {
        return ((until != null) ? new java.util.Date(until) : null);
    }

    public boolean available() {
        return STATUS_AVAILABLE.equals(status);
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String status;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Long until;

    private static final long serialVersionUID = 1L;
}
