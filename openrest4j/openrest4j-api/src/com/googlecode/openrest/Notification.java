package com.googlecode.openrest;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification implements Serializable {
    /** Triggered when a new order is unhandled for some duration. */
    public static final String NOTIFICATION_TYPE_DELAYED_ORDER = "delayed_order";
    /** Triggered when no one checks for existence of new orders for some duration. */
    public static final String NOTIFICATION_TYPE_OFFLINE = "offline";

    /** All known notification types. */
    public static final Set<String> ALL_NOTIFICATION_TYPES = new HashSet<String>(Arrays.asList(new String[] {
        NOTIFICATION_TYPE_DELAYED_ORDER, NOTIFICATION_TYPE_OFFLINE
    }));

    /** Constructs a previously submitted notification from persisted data. */
    public Notification(String restaurantId, String type, Contact contact, Integer durationMins) {
        this.restaurantId = restaurantId;
        this.type = type;
        this.contact = contact;
        this.durationMins = durationMins;
    }

    /** Constructs a new notification to be submitted. */
    public Notification(String type, Contact contact, Integer durationMins) {
        this(null, type, contact, durationMins);
    }
    
    /** Default constructor for JSON deserialization. */
    public Notification() {}

    public Notification delayedOrderNotification(Contact contact, Integer durationMins) {
        return new Notification(NOTIFICATION_TYPE_DELAYED_ORDER, contact, durationMins);
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String restaurantId;

    /** Notification type. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String type;

    /** Who should be notified. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Contact contact;

    /**
     * Event duration for triggering a notification, e.g. "after 15 minutes of
     * not handling an incoming order".
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer durationMins = 0;

    private static final long serialVersionUID = 1L;
}
