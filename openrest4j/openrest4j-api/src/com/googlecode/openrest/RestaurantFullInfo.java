package com.googlecode.openrest;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.googlecode.openrest.Charge;
import com.googlecode.openrest.Menu;
import com.googlecode.openrest.Restaurant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantFullInfo implements Serializable {
    public RestaurantFullInfo(Restaurant restaurant, Menu menu, List<Charge> charges) {
    	this.restaurant = restaurant;
    	this.menu = menu;
    	this.charges = charges;
    }
    
    /** Default constructor for JSON deserialization. */
    public RestaurantFullInfo() {}
    
    /** The restaurant. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Restaurant restaurant;
    
    /** The menu. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Menu menu;
    
    /** The charges. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public List<Charge> charges;
    
    private static final long serialVersionUID = 1L;
}
