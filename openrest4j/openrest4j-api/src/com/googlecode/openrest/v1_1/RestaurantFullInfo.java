package com.googlecode.openrest.v1_1;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.googlecode.openrest.v1_1.Charge;
import com.googlecode.openrest.v1_1.Menu;
import com.googlecode.openrest.v1_1.Restaurant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantFullInfo implements Serializable {
    public RestaurantFullInfo(Restaurant restaurant, Menu menu, List<Charge> charges, Distributor distributor) {
    	this.restaurant = restaurant;
    	this.menu = menu;
    	this.charges = charges;
    	this.distributor = distributor;
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
    
    /** The distributor. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Distributor distributor;
    
    private static final long serialVersionUID = 1L;
}
