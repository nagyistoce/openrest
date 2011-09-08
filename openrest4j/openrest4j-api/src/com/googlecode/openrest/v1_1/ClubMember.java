package com.googlecode.openrest.v1_1;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Identifies a club member to the restaurant.
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClubMember implements Serializable {
    /** Default constructor for JSON deserialization. */
    public ClubMember(String restaurantId, String memberId, String phone, List<String> clubIds) {
    	this.restaurantId = restaurantId;
    	this.memberId = memberId;
    	this.phone = phone;
    	this.clubIds = clubIds;
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
    
    /** The internal club ids of which the client is a member of. */
    public List<String> clubIds = Collections.emptyList();
    
    private static final long serialVersionUID = 1L;
}
