package com.openrest.v1_1;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;

/**
 * Base class for all Openrest objects.
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,  
	    include = JsonTypeInfo.As.PROPERTY,  
	    property = "type")  
@JsonSubTypes({
	@Type(value = Restaurant.class, name = "restaurant"),
	@Type(value = Chain.class, name = "chain"),
	@Type(value = Distributor.class, name = "distributor"),
	@Type(value = Portal.class, name = "portal"),
	@Type(value = RestaurantFullInfo.class, name = "restaurant_full"),
	@Type(value = ChainFullInfo.class, name = "chain_full"),
	@Type(value = PortalFullInfo.class, name = "portal_full")
})
public abstract class OpenrestObject implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** Default constructor for JSON deserialization. */
    public OpenrestObject() {}
}
