package com.googlecode.openrest.v1_1;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.googlecode.openrest.v1_1.Payment;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SavedPayment extends Payment {
	private static final long serialVersionUID = 1L;
	
    public SavedPayment(String id, String userId, Payment payment, String password) {
    	super(payment.type, Integer.valueOf(0), payment.card);
    	this.id = id;
    	this.userId = userId;
    	this.password = password;
    }

    /** Default constructor for JSON deserialization. */
    public SavedPayment() {}

    /** The saved payment unique id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String id;
    
    /** The user's Facebook id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String userId;

    /**
     * Optional user-defined password for protecting against unauthorized usage.
     * 
     * For anonymized saved payments, this would either be missing (null) to indicate
     * no password, or empty ("") to indicate a password exists and was anonymized.
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String password;
}
