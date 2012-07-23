package com.openrest.v1_1;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.googlecode.openrest.v1_1.Order;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubmitOrderRequest extends Request {
    private static final long serialVersionUID = 1L;
    
    /** Default constructor for JSON deserialization. */
    public SubmitOrderRequest() {}
    
    public SubmitOrderRequest(String accessToken, Order order, String comment) {
    	this.accessToken = accessToken;
    	this.order = order;
    	this.comment = comment;
    }
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String accessToken;
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Order order;
    
    /** Technical comment (NOT user comment!) */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String comment;
}
