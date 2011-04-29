package com.googlecode.openrest;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Information regarding a delivery destination: type, area, requirements, etc.
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryInfo implements Serializable {
	public DeliveryInfo(String type, Area area, Integer minOrderPrice, Integer charge, Integer delayMins) {
    	this.type = type;
    	this.area = area;
    	this.minOrderPrice = minOrderPrice;
    	this.charge = charge;
    	this.delayMins = delayMins;
    }

    /** Default constructor for JSON deserialization. */
    public DeliveryInfo() {}
    
    /** Delivery type, one of Delivery.ALL_DELIVERY_TYPES. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String type;
    
    /** Optional delivery area (for type = Delivery.DELIVERY_TYPE_DELIVERY). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Area area;
    
    /** The minimum allowed order price (in "cents"). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer minOrderPrice = 0;
    
    /** The delivery charge (in "cents"). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer charge = 0;
    
    /** Delivery time (maximum number of minutes till order arrives). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer delayMins = 0;
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((charge == null) ? 0 : charge.hashCode());
		result = prime * result
				+ ((delayMins == null) ? 0 : delayMins.hashCode());
		result = prime * result
				+ ((minOrderPrice == null) ? 0 : minOrderPrice.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeliveryInfo other = (DeliveryInfo) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (charge == null) {
			if (other.charge != null)
				return false;
		} else if (!charge.equals(other.charge))
			return false;
		if (delayMins == null) {
			if (other.delayMins != null)
				return false;
		} else if (!delayMins.equals(other.delayMins))
			return false;
		if (minOrderPrice == null) {
			if (other.minOrderPrice != null)
				return false;
		} else if (!minOrderPrice.equals(other.minOrderPrice))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
    
	private static final long serialVersionUID = 1L;
}
