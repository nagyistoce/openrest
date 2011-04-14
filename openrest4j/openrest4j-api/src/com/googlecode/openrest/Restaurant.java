package com.googlecode.openrest;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Restaurant information.
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant implements Serializable {
    public Restaurant(String id, String name, String description, Contact contact,
            Integer deliveryCharge, Integer minOrderPrice, Address address,
            List<LatLng> deliveryArea, String welcomeMessage, String confirmationMessage,
            ColorScheme colorScheme, Availability openTimes, Availability deliveryTimes,
            Boolean inactive, List<String> deliveryTypes, Map<String, Integer> deliveryMins,
            String timezone, List<String> paymentTypes,
            Map<String, Integer> minPayments, String link, Map<String, String> properties) {
        
        this.id = id;
        this.name = name;
        this.description = description;
        this.contact = contact;
        this.deliveryCharge = deliveryCharge;
        this.minOrderPrice = minOrderPrice;
        this.address = address;
        this.deliveryArea = deliveryArea;
        this.welcomeMessage = welcomeMessage;
        this.confirmationMessage = confirmationMessage;
        this.colorScheme = colorScheme;
        this.openTimes = openTimes;
        this.deliveryTimes = deliveryTimes;
        this.inactive = inactive;
        this.deliveryTypes = deliveryTypes;
        this.deliveryMins = deliveryMins;
        this.timezone = timezone;
        this.paymentTypes = paymentTypes;
        this.minPayments = minPayments;
        this.link = link;
        this.properties = properties;
    }

    /** Default constructor for JSON deserialization. */
    public Restaurant() {}

    public TimeZone timezone() {
        return TimeZone.getTimeZone(timezone);
    }
    
    /** The restaurant's unique id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String id;

    /** The restaurant's name. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String name;

    /** The restaurant's description or tagline. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String description;

    /** The restaurant's contact. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Contact contact;

    /** The delivery charge (in "cents"). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer deliveryCharge = 0;

    /** The minimum allowed order price (in "cents"). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer minOrderPrice = 0;

    /** The address of this restaurant. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Address address;

    /** The delivery area (polygon vertices). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<LatLng> deliveryArea = Collections.emptyList();

    /** The restaurant's welcome message. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String welcomeMessage;

    /** The default order confirmation message. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String confirmationMessage;

    /** The color scheme. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public ColorScheme colorScheme;

    /** Restaurant availability. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Availability openTimes = new Availability();

    /** Deliveries availability. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Availability deliveryTimes = new Availability();

    /** Whether the restaurant is deactivated (i.e. suspended or disabled). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Boolean inactive = Boolean.FALSE;

    /** Available delivery methods. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<String> deliveryTypes = Delivery.ALL_DELIVERY_TYPES;
    
    /**
     * Maps delivery methods to delivery times (maximum number of minutes till
     * order arrives).
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, Integer> deliveryMins = Collections.emptyMap();

    /** The current status. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Status status;

    /** The current delivery status. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Status deliveryStatus;

    /** Human readable status string. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String openTimesStr;

    /** Human readable delivery status string. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String deliveryTimesStr;

    /**
     * The restaurant's timezone.
     * @see http://en.wikipedia.org/wiki/List_of_tz_database_time_zones
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String timezone;

    /** Available payment methods. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<String> paymentTypes = Payment.ALL_PAYMENT_TYPES;

    /**
     * Maps available payment types to minimal charge allowed per payment, e.g.
     * "credit cards can only be used for paying $5 or more". Non-referenced
     * payment types have zero minimum by default.
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, Integer> minPayments = Collections.emptyMap();

    /** Official restaurant web-site. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String link;
    
    /**
     * Map of user-defined extended properties. Developers should use unique
     * keys, e.g. "com.googlecode.openrestext".
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, String> properties = Collections.emptyMap();

    private static final long serialVersionUID = 1L;
}
