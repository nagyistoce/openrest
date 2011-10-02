package com.googlecode.openrest.v1_0;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public Role(String restaurantId, String role) {
    	this.restaurantId = restaurantId;
    	this.role = role;
    }
    
    /** Default constructor for JSON deserialization. */
    public Role() {}
    
    /** The restaurant-id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String restaurantId;
    
    /**
     * The user's role (admin, manager, employee) in the restaurant.
     * @see com.googlecode.openrest.Staff
     * */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String role;
}
