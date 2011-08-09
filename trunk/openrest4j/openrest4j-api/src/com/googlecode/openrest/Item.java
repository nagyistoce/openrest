package com.googlecode.openrest;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * An item that can be ordered, e.g. a main dish ("hamburger"), a side ("fries")
 * or a dish variation ("well done").
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Serializable {
	/** Constructs a previously submitted item from persisted data. */
    public Item(String id, String restaurantId, String title, String description,
            Integer price, List<Variation> variations, Availability availability,
            Boolean inactive, String picture, Map<String, String> externalIds) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.variations = variations;
        this.availability = availability;
        this.inactive = inactive;
        this.picture = picture;
        this.externalIds = externalIds;
    }

    /** Constructs a new item to be submitted. */
    public Item(String title, String description, Integer price, List<Variation> variations,
            Availability availability, Boolean inactive, Map<String, String> externalIds) {
        this(null, null, title, description, price, variations, availability, inactive, null, externalIds);
    }

	/** Default constructor for JSON deserialization. */
    public Item() {}

    /** The item's unique id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String id;

    /** The restaurant's id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String restaurantId;

    /** The item's name. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String title;

    /** The item's one line description. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String description;

    /** The item's price, in "cents". */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer price = 0;

    /** List of possible variations. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<Variation> variations = Collections.emptyList();

    /** Time windows in which this item is regularly available. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Availability availability = new Availability();

    /** Human readable availability string. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String availabilityStr;

    /** Whether the item is deactivated (i.e. suspended or disabled). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Boolean inactive = Boolean.FALSE;
    
    /** Item picture URL (direct link). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String picture;

    /** The current status. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Status status;
    
    /**
     * Map of externally-defined item ids referring to this item.
     * For example, the item-id in the restaurant's PoS system.
     * 
     * Developers should use unique keys, e.g. "com.company.product".
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, String> externalIds = Collections.emptyMap();
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((availability == null) ? 0 : availability.hashCode());
		result = prime * result
				+ ((availabilityStr == null) ? 0 : availabilityStr.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((externalIds == null) ? 0 : externalIds.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((inactive == null) ? 0 : inactive.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((restaurantId == null) ? 0 : restaurantId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result
				+ ((variations == null) ? 0 : variations.hashCode());
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
		Item other = (Item) obj;
		if (availability == null) {
			if (other.availability != null)
				return false;
		} else if (!availability.equals(other.availability))
			return false;
		if (availabilityStr == null) {
			if (other.availabilityStr != null)
				return false;
		} else if (!availabilityStr.equals(other.availabilityStr))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (externalIds == null) {
			if (other.externalIds != null)
				return false;
		} else if (!externalIds.equals(other.externalIds))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inactive == null) {
			if (other.inactive != null)
				return false;
		} else if (!inactive.equals(other.inactive))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (restaurantId == null) {
			if (other.restaurantId != null)
				return false;
		} else if (!restaurantId.equals(other.restaurantId))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (variations == null) {
			if (other.variations != null)
				return false;
		} else if (!variations.equals(other.variations))
			return false;
		return true;
	}
    
    private static final long serialVersionUID = 1L;
}
