package com.googlecode.openrest.v1_1;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public Role(String organizationId, String role) {
    	this.organizationId = organizationId;
    	this.role = role;
    }
    
    /** Default constructor for JSON deserialization. */
    public Role() {}
    
    /** The organization id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String organizationId;
    
    /**
     * The user's role (admin, manager, employee, etc) in the organization.
     * @see com.googlecode.openrest.Staff
     * */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String role;
}
