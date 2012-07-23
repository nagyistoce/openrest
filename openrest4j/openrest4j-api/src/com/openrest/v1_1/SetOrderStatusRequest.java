package com.openrest.v1_1;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SetOrderStatusRequest extends Request {
    private static final long serialVersionUID = 1L;
    
    /** Default constructor for JSON deserialization. */
    public SetOrderStatusRequest() {}
    
    public SetOrderStatusRequest(String accessToken, String confirmationToken, String orderId, String status, String comment) {
    	this.accessToken = accessToken;
    	this.confirmationToken = confirmationToken;
    	this.orderId = orderId;
    	this.status = status;
    	this.comment = comment;
    }
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String accessToken;
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String confirmationToken;
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String orderId;
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String status;
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String comment;
}
