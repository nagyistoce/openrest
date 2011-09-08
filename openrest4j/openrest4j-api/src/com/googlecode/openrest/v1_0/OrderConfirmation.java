package com.googlecode.openrest.v1_0;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderConfirmation implements Serializable {
    public OrderConfirmation(Order order, String message) {
        this.order = order;
        this.message = message;
    }
    
    /** Default constructor for JSON deserialization. */
    public OrderConfirmation() {}

    /** The confirmed order. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Order order;

    /** The restaurant's confirmation message. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String message;
    
    private static final long serialVersionUID = 1L;
}
