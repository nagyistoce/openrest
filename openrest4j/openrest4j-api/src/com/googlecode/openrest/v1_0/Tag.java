package com.googlecode.openrest.v1_0;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * A set of items that go together, e.g. "sides", "drinks", "toppings".
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag implements Serializable {
	/** Inclusive: tag refers to given items. */
	public static final String TAG_MODE_INCLUDE = "include";
    /** Exclusive: tag refers to anything but the given items. */
    public static final String TAG_MODE_EXCLUDE = "exclude";
    
    /** All known tag modes */
    public static final Set<String> ALL_TAG_MODES = new HashSet<String>(Arrays.asList(new String[] {
    		TAG_MODE_INCLUDE, TAG_MODE_EXCLUDE
    }));
	
    /** Constructs a previously submitted tag from persisted data. */
    public Tag(String id, String restaurantId, String title, List<String> itemIds) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.title = title;
        this.itemIds = itemIds;
    }

    /** Constructs a new tag to be submitted. */
    public Tag(String title, List<String> itemIds) {
        this(null, null, title, itemIds);
    }

    /** Default constructor for JSON deserialization. */
    public Tag() {}

    /** The tag's unique id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String id;

    /** The restaurant's id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String restaurantId;

    /** The tag's name, e.g. "drink", "sides". */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String title;

    /** Item ids. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<String> itemIds = Collections.emptyList();

    private static final long serialVersionUID = 1L;
}
