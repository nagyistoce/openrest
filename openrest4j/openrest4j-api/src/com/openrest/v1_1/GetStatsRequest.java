package com.openrest.v1_1;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.googlecode.openrest.v1_1.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetStatsRequest extends Request {
    private static final long serialVersionUID = 1L;
    
    /** Default constructor for JSON deserialization. */
    public GetStatsRequest() {}
    
    public GetStatsRequest(String accessToken, String distributorId, String chainId, Set<String> restaurantIds,
    		Date since, Date until, String granularity, String ref) {
    	this.accessToken = accessToken;
    	this.distributorId = distributorId;
    	this.chainId = chainId;
    	this.restaurantIds = restaurantIds;
    	this.since = since;
    	this.until = until;
    	this.granularity = granularity;
    	this.ref = ref;
    }
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String accessToken;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String distributorId;
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String chainId;
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Set<String> restaurantIds;
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Date since;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Date until;
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String granularity;
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String ref;
}
