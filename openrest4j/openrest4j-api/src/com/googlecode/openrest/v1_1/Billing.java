package com.googlecode.openrest.v1_1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Billing implements Serializable {
	private static final long serialVersionUID = 1L;
    
    public Billing(String restaurantId, List<Cost> costs, Map<String, String> method, Integer year, Integer month) {
    	this.restaurantId = restaurantId;
    	this.costs = costs;
    	this.method = method;
    	this.year = year;
    	this.month = month;
    }
    
    /** Default constructor for JSON deserialization. */
    public Billing() {}
    
    /** The restaurant unique id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String restaurantId;
    
    /** The costs. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<Cost> costs = new ArrayList<Cost>();
    
    /** Payment method (free text, multi-locale). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, String> method = Collections.emptyMap();
    
    /** The restaurant unique id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer year;

    /** The restaurant unique id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer month;
}
