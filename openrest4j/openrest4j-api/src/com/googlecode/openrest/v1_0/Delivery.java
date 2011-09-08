package com.googlecode.openrest.v1_0;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Delivery implements Serializable {
    /** Delivery to an address of your choice. */
    public static final String DELIVERY_TYPE_DELIVERY = "delivery";
    /** Take-out from the restaurant. */
    public static final String DELIVERY_TYPE_TAKEOUT = "takeout";

    /** All known delivery methods. */
    public static final Set<String> ALL_DELIVERY_TYPES = new HashSet<String>(Arrays.asList(new String[] {
        DELIVERY_TYPE_DELIVERY, DELIVERY_TYPE_TAKEOUT
    }));

    public Delivery(String type, Address address, Integer charge) {
        this.type = type;
        this.address = address;
        this.charge = charge;
    }

    /** Default constructor for JSON deserialization. */
    public Delivery() {}

    /** Delivery type. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String type;

    /** Address to deliver to (valid only if type is DELIVERY_TYPE_DELIVERY) */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Address address;
    
    /** The delivery charge (in "cents"). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer charge = 0;

    private static final long serialVersionUID = 1L;
}
