package com.googlecode.openrest.v1_1;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Category implements Serializable {
    /** Constructs a previously submitted category from persisted data. */
    public Category(String id, String restaurantId, Map<String, String> title,
    		Map<String, String> description, String parentCategoryId,
    		List<String> itemIds, Double priority) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.title = title;
        this.description = description;
        this.parentCategoryId = parentCategoryId;
        this.itemIds = itemIds;
        this.priority = priority;
    }

    /** Constructs a new category to be submitted. */
    public Category(Map<String, String> title, Map<String, String> description,
    		String parentCategoryId, List<String> itemIds, Double priority) {
        this(null, null, title, description, parentCategoryId, itemIds, priority);
    }

    /** Default constructor for JSON deserialization. */
    public Category() {}

    /** The category's unique id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String id;

    /** The restaurant's id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String restaurantId;

    /** The category's title in various locales. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, String> title;

    /** The category's description in various locales. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, String> description;

    /** The parent category's id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String parentCategoryId;

    /** The item-ids in this category. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<String> itemIds = Collections.emptyList();

    /** Order priority. Lower numbers appear first in the list. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Double priority = 0.0;
    
    private static final long serialVersionUID = 1L;
}
