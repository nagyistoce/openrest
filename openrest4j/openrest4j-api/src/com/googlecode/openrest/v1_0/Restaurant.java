package com.googlecode.openrest.v1_0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Restaurant information.
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/** Restaurant system is used for demonstration only. Orders will not be handled. */
    public static final String STATE_DEMO = "demo";
    /** Restaurant system is under construction. Orders will not be handled. */
    public static final String STATE_UNDER_CONSTRUCTION = "under_construction";
    /** Restaurant system is operational. Orders are accepted. */
    public static final String STATE_OPERATIONAL = "operational";
    
    /** All known statuses. */
    public static final Set<String> ALL_STATES = new HashSet<String>(Arrays.asList(new String[] {
    		STATE_DEMO, STATE_UNDER_CONSTRUCTION, STATE_OPERATIONAL
    }));
	
    public Restaurant(String id, Long created, String distributorId, String name, String description,
    		Contact contact, Address address, String welcomeMessage, String confirmationMessage,
            ColorScheme colorScheme, Availability openTimes, Availability deliveryTimes,
            Boolean inactive, List<DeliveryInfo> deliveryInfos, Status status, Status deliveryStatus,
            String timezone, String currency, String locale, List<String> locales,
            List<String> paymentTypes, Map<String, Integer> minPayments,
            String link, String picture, String icon, Map<String, String> properties,
            String state, Double rank) {
        
        this.id = id;
        this.created = created;
        this.distributorId = distributorId;
        this.name = name;
        this.description = description;
        this.contact = contact;
        this.address = address;
        this.welcomeMessage = welcomeMessage;
        this.confirmationMessage = confirmationMessage;
        this.colorScheme = colorScheme;
        this.openTimes = openTimes;
        this.deliveryTimes = deliveryTimes;
        this.inactive = inactive;
        this.deliveryInfos = deliveryInfos;
        this.status = status;
        this.deliveryStatus = deliveryStatus;
        this.timezone = timezone;
        this.currency = currency;
        this.locale = locale;
        this.locales = locales;
        this.paymentTypes = paymentTypes;
        this.minPayments = minPayments;
        this.link = link;
        this.picture = picture;
        this.icon = icon;
        this.properties = properties;
        this.state = state;
        this.rank = rank;
    }

    /** Default constructor for JSON deserialization. */
    public Restaurant() {}

    public TimeZone getTimezone() {
        return TimeZone.getTimeZone(timezone);
    }
    
    /** The restaurant's unique id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String id;
    
    /** The restaurant's creation (NOT establishment!) timestamp. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Long created;
    
    public java.util.Date created() {
        return ((created != null) ? new java.util.Date(created.longValue()) : null);
    }
    
    /** The distributor in charge of this restaurant. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String distributorId;

    /** The restaurant's name. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String name;

    /** The restaurant's description or tagline. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String description;

    /** The restaurant's contact. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Contact contact;

    /** The address of this restaurant. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Address address;

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

    /** Information regarding the different delivery destinations. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<DeliveryInfo> deliveryInfos = Collections.emptyList();

    /** The current status. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Status status;

    /** The current delivery status. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Status deliveryStatus;

    /**
     * The restaurant's timezone.
     * @see http://en.wikipedia.org/wiki/List_of_tz_database_time_zones
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String timezone;
    
    /** The restaurant's currency (ISO 4217). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String currency;
    
    /** The restaurant's default locale, e.g. "en_US". */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String locale;
    
    /** The restaurant's supported locales. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<String> locales = Collections.emptyList();

    /** Available payment methods. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<String> paymentTypes = new ArrayList<String>(Payment.ALL_PAYMENT_TYPES);

    /**
     * Maps available payment types to minimal charge allowed per payment, e.g.
     * "credit cards can only be used for paying $5 or more". Non-referenced
     * payment types have zero minimum by default.
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, Integer> minPayments = Collections.emptyMap();

    /** Official restaurant web-site URL. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String link;
    
    /** Restaurant picture URL (direct link). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String picture;
    
    /** Restaurant icon URL (direct link). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String icon;
    
    /**
     * Map of user-defined extended properties. Developers should use unique
     * keys, e.g. "com.googlecode.openrestext".
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, String> properties = Collections.emptyMap();
    
    /** @see ALL_STATES */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public String state = STATE_OPERATIONAL;
    
    /** The restaurant's Openrest rank. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Double rank;
}
