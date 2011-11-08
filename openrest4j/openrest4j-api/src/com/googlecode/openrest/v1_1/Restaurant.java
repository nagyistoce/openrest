package com.googlecode.openrest.v1_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Restaurant information.
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant extends Organization implements Comparable<Restaurant> {
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
    
    /** The restaurant's welcome message. */
    public static final String MESSAGE_TYPE_WELCOME = "welcome";
    /** The restaurant's order confirmation message. */
    public static final String MESSAGE_TYPE_ORDER_CONFIRMATION = "order_confirmation";
	
    public Restaurant(String id, Long created, String distributorId, Map<String, String> title,
    		Map<String, String> description, Contact contact, Address address,
    		Map<String, Map<String, String>> messages, ColorScheme colorScheme,
    		Availability openTimes, Availability deliveryTimes,
            Boolean inactive, List<DeliveryInfo> deliveryInfos, Status status, Status deliveryStatus,
            String timezone, String currency, String locale, List<String> locales,
            List<String> paymentTypes, Map<String, Integer> minPayments,
            String link, String picture, String icon, Map<String, String> properties,
            String state, Double rank) {
    	super(id, created, title, description, contact, address, timezone, locale, locales,
    			link, picture, icon, properties);
        
    	this.distributorId = distributorId;
        this.messages = messages;
        this.colorScheme = colorScheme;
        this.openTimes = openTimes;
        this.deliveryTimes = deliveryTimes;
        this.inactive = inactive;
        this.deliveryInfos = deliveryInfos;
        this.status = status;
        this.deliveryStatus = deliveryStatus;
        this.currency = currency;
        this.paymentTypes = paymentTypes;
        this.minPayments = minPayments;
        this.state = state;
        this.rank = rank;
    }

    /** Default constructor for JSON deserialization. */
    public Restaurant() {}

    /** The distributor in charge of this restaurant. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String distributorId;
    
    /** Maps message types (e.g. MESSAGE_TYPE_WELCOME) to their text in various locales. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, Map<String, String>> messages = Collections.emptyMap();

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

    /** The restaurant's currency (ISO 4217). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String currency;
    
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

    /** @see ALL_STATES */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public String state = STATE_OPERATIONAL;
    
    /** The restaurant's Openrest rank (higher is better). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Double rank;

	@Override
	public int compareTo(Restaurant other) {
		if (rank != null) {
			return ((other.rank != null) ? -rank.compareTo(other.rank) : -1);
		} else {
			return ((other.rank == null) ? (0) : 1);
		}
	}
}
