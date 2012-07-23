package com.openrest.v1_1;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.googlecode.openrest.v1_1.Payment;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPaymentRequest extends Request {
    private static final long serialVersionUID = 1L;
    
    /** Default constructor for JSON deserialization. */
    public AddPaymentRequest() {}
    
    public AddPaymentRequest(String accessToken, Payment payment) {
    	this.accessToken = accessToken;
    	this.payment = payment;
    }
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String accessToken;
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Payment payment;
}
