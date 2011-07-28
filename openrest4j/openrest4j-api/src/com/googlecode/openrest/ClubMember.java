package com.googlecode.openrest;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Identifies a club member to the restaurant.
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClubMember implements Serializable {
    /** Default constructor for JSON deserialization. */
    public ClubMember(String restaurantId, String memberId, String phone) {
    	this.restaurantId = restaurantId;
    	this.memberId = memberId;
    	this.phone = phone;
    }
    
    /** Default constructor for JSON deserialization. */
    public ClubMember() {}
    
    /** The restaurant's id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String restaurantId;
    
    /** The club member's id (e.g. number on membership card). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String memberId;
    
    /** The club member's phone number. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String phone;
    
    private static final long serialVersionUID = 1L;
}
