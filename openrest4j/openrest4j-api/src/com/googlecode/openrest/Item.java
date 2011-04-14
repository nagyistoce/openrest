package com.googlecode.openrest;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

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
            Boolean inactive) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.variations = variations;
        this.availability = availability;
        this.inactive = inactive;
    }

    /** Constructs a new item to be submitted. */
    public Item(String title, String description, Integer price, List<Variation> variations,
            Availability availability, Boolean inactive) {
        this(null, null, title, description, price, variations, availability, inactive);
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

    /** The current status. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Status status;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.restaurantId == null) ? (other.restaurantId != null) : !this.restaurantId.equals(other.restaurantId)) {
            return false;
        }
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if (this.price != other.price && (this.price == null || !this.price.equals(other.price))) {
            return false;
        }
        if (this.variations != other.variations && (this.variations == null || !this.variations.equals(other.variations))) {
            return false;
        }
        if (this.availability != other.availability && (this.availability == null || !this.availability.equals(other.availability))) {
            return false;
        }
        if (this.inactive != other.inactive && (this.inactive == null || !this.inactive.equals(other.inactive))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 41 * hash + (this.restaurantId != null ? this.restaurantId.hashCode() : 0);
        hash = 41 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 41 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 41 * hash + (this.price != null ? this.price.hashCode() : 0);
        hash = 41 * hash + (this.variations != null ? this.variations.hashCode() : 0);
        hash = 41 * hash + (this.availability != null ? this.availability.hashCode() : 0);
        hash = 41 * hash + (this.inactive != null ? this.inactive.hashCode() : 0);
        return hash;
    }
    
    private static final long serialVersionUID = 1L;
}
