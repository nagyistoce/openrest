package com.googlecode.openrest.v1_1;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address implements Serializable {
    public Address(String country, String city, String street, String number,
    		String apt, String floor, String entrance, String comment, LatLng latLng) {
    	this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
        this.apt = apt;
        this.floor = floor;
        this.entrance = entrance;
        this.comment = comment;
        this.latLng = latLng;
    }

    /** Default constructor for JSON deserialization. */
    public Address() {}

    public String streetAddress() {
        return street + ' ' + number + ", " + city + ", " + country;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String country;
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String city;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String street;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String number;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String apt;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String floor;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String entrance;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String comment;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public LatLng latLng;
    
    private static final long serialVersionUID = 1L;
}
