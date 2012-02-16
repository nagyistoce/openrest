package com.googlecode.openrest.v1_1;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Picture implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public Picture(String id) {
    	this.id = id;
    }
    
    /** Default constructor for JSON deserialization. */
    public Picture() {}
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String id;
}
