package com.googlecode.openrest;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * An extra charge or a discount associated with an order.
 * Examples: delivery charge, state tax, discount coupon.
 * 
 * TODO: time availability ("happy hour"), geography filter (delivery charge).
 * 
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Charge implements Serializable {
    /** Delivery charge. */
    public static final String CHARGE_TYPE_DELIVERY = "delivery";
    /** Discount coupon. */
    public static final String CHARGE_TYPE_COUPON = "coupon";
    /** Member discount. */
    public static final String CHARGE_TYPE_CLUB_COUPON = "club_coupon";
    
    /** All charges, in ascending alphabetic order! */
    public static final List<String> ALL_CHARGE_TYPES = Arrays.asList(new String[] {
    		CHARGE_TYPE_CLUB_COUPON, CHARGE_TYPE_COUPON, CHARGE_TYPE_DELIVERY
    });
    
	/** Amount rule is a fixed number. */
    public static final String AMOUNT_RULE_TYPE_FIXED = "fixed";
	/** Amount rule is percentage of some baseline amount. */
    public static final String AMOUNT_RULE_TYPE_PERCENTAGE = "percentage";
	/** Amount rule is variable, depending on external factors. */
    public static final String AMOUNT_RULE_TYPE_VARIABLE = "variable";

    /** All amount types, in ascending alphabetic order! */

    public static final List<String> ALL_AMOUNT_RULE_TYPES = Arrays.asList(new String[] {
    		AMOUNT_RULE_TYPE_FIXED, AMOUNT_RULE_TYPE_PERCENTAGE, AMOUNT_RULE_TYPE_VARIABLE
    });

    public Charge(String id, String type, Double priority, String code,
    		String tagId, String tagMode,
    		String amountRuleType, Integer amountRule, Integer amount) {
    	this.id = id;
        this.type = type;
        this.priority = priority;
        this.code = code;
        this.tagId = tagId;
        this.tagMode = tagMode;
        this.amountRuleType = amountRuleType;
        this.amountRule = amountRule;
        this.amount = amount;
    }

    /** Default constructor for JSON deserialization. */
    public Charge() {}

    /** Charge id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String id;
    
    /** Charge type. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String type;

    /** Charge priority. Higher means first in the list. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Double priority = 0.0;
    
    /** Optional activation code, e.g. GoDaddy-style, member id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String code;
    
    /** Items for which the charge applies, null if applies for every item. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String tagId;
   
    /** Tag mode: inclusive or exclusive. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public String tagMode = Tag.TAG_MODE_INCLUDE;
    
    /** Charge amount rule type. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public String amountRuleType = AMOUNT_RULE_TYPE_VARIABLE;
    
    /**
     * Charge amount rule (cents for fixed amounts, percentage for percentage
     * amounts, undefined for variable amounts).
     * 
     * Positive numbers are extra charges, negatives are discounts.
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer amountRule;
    
    /** Coupon information (valid for CHARGE_TYPE_COUPON and CHARGE_TYPE_CLUB_COUPON). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Coupon coupon;
    
    /** Bottom-line charge amount (in cents). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer amount;
    
    private static final long serialVersionUID = 1L;
}
