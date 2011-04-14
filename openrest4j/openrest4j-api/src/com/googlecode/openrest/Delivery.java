package com.googlecode.openrest;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Delivery implements Serializable {
    /** Delivery to an address of your choice. */
    public static final String DELIVERY_TYPE_DELIVERY = "delivery";
    /** Take-out from the restaurant. */
    public static final String DELIVERY_TYPE_TAKEOUT = "takeout";

    /** All delivery methods, in ascending alphabetic order! */
    public static final List<String> ALL_DELIVERY_TYPES = Arrays.asList(new String[] {
        DELIVERY_TYPE_DELIVERY, DELIVERY_TYPE_TAKEOUT
    });

    public Delivery(String type, Address address) {
        this.type = type;
        this.address = address;
    }

    /** Default constructor for JSON deserialization. */
    public Delivery() {}

    /** Delivery type. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String type;

    /** Address to deliver to (valid only if type is DELIVERY_TYPE_DELIVERY) */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Address address;

    private static final long serialVersionUID = 1L;
}
