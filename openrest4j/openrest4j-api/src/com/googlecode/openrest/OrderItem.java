package com.googlecode.openrest;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItem implements Serializable {
    public OrderItem(String itemId, List<Variation> variations, List<List<OrderItem>> variationsChoices,
            String comment, Integer price, Integer count) {
        this.itemId = itemId;
        this.variations = variations;
        this.variationsChoices = variationsChoices;
        this.comment = comment;
        this.price = price;
        this.count = count;
    }

    /** Default constructor for JSON deserialization. */
    public OrderItem() {}

    /** Item id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String itemId;

    /**
     * The ordered-item's variations.
     * 
     * Submitting an OrderItem with empty variations means the defaults should be assumed
     * for variationsChoices.
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<Variation> variations = Collections.emptyList();

    /** The ordered-item's chosen variations. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<List<OrderItem>> variationsChoices = Collections.emptyList();

    /** Textual comment regarding the item. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String comment;

    /** Total price of the item and variations. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer price = 0;

    /** Number of times this order-item was ordered. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer count = 1;

    private static final long serialVersionUID = 1L;
}
