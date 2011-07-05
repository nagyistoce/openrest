package com.googlecode.openrest;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Status implements Serializable {
	/** Available. */
    public static final String STATUS_AVAILABLE = "available";
    /** Unavailable. */
    public static final String STATUS_UNAVAILABLE = "unavailable";
    
    /** All known statuses. */
    public static final Set<String> ALL_STATUSES = new HashSet<String>(Arrays.asList(new String[] {
    		STATUS_AVAILABLE, STATUS_UNAVAILABLE
    }));

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
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((until == null) ? 0 : until.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (until == null) {
			if (other.until != null)
				return false;
		} else if (!until.equals(other.until))
			return false;
		return true;
	}

    private static final long serialVersionUID = 1L;
}
