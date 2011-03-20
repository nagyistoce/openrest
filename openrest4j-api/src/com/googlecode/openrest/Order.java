package com.googlecode.openrest;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order implements Serializable {
    /**
     * The restaurant required further confirmation before the order is submitted
     * (e.g. validating the user's phone number by SMS).
     */
    public static final String ORDER_STATUS_PENDING = "pending";
    /** The order has been submitted, and awaits processing by the restaurant. */
    public static final String ORDER_STATUS_NEW = "new";
    /** The order has been processed and accepted by the restaurant. */
    public static final String ORDER_STATUS_ACCEPTED = "accepted";
    /** The order has been canceled. */
    public static final String ORDER_STATUS_CANCELLED = "canceled";

    /** Constructs a previously submitted order from persisted data. */
    public Order(String id, String restaurantId, List<OrderItem> orderItems, String comment,
            Integer price, Delivery delivery, Contact contact, List<Payment> payments,
            java.util.Date created, java.util.Date modified, User user, String status,
            String shareToken) {

        this.id = id;
        this.restaurantId = restaurantId;
        this.orderItems = orderItems;
        this.comment = comment;
        this.price = price;
        this.delivery = delivery;
        this.contact = contact;
        this.payments = payments;
        this.created = ((created != null) ? created.getTime() : null);
        this.modified = ((modified != null) ? modified.getTime() : null);
        this.user = user;
        this.status = status;
        this.shareToken = shareToken;
    }

    /** Constructs a new order to be submitted. */
    public Order(List<OrderItem> orderItems, String comment, Integer price,
            Delivery delivery, Contact contact, List<Payment> payments) {
        this(null, null, orderItems, comment, price, delivery, contact, payments,
                null, null, null, null, null);
    }

    /** Default constructor for JSON deserialization. */
    public Order() {}
    
    public java.util.Date created() {
        return new java.util.Date(created.longValue());
    }

    public java.util.Date modified() {
        return new java.util.Date(modified.longValue());
    }

    /** The order's unique id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String id;

    /** The restaurant's unique id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String restaurantId;

    /** The ordered items. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<OrderItem> orderItems = Collections.emptyList();

    /** Comment to the restaurant (as opposed to the delivery person!). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String comment;

    /** Total price of the order. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer price = 0;

    /* Delivery method. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Delivery delivery;

    /* Contact details. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Contact contact;

    /* Payments. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<Payment> payments = Collections.emptyList();

    /** The order's creation timestamp. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Long created;

    /** The order's last modification timestamp. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Long modified;

    /** The ordering user. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public User user;

    /** The order's status. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String status;

    /** The order's share-token. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String shareToken;

    private static final long serialVersionUID = 1L;
}