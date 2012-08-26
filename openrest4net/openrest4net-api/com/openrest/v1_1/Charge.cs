using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    /**
     * An extra charge or a discount associated with an order.
     * Examples: delivery charge, state tax, discount coupon.
     * 
     * @author DL
     */
    public class Charge
    {
	    /** Delivery charge. */
        public const string CHARGE_TYPE_DELIVERY = "delivery";
        /** Discount coupon. */
        public const string CHARGE_TYPE_COUPON = "coupon";
        /** Member discount. */
        public const string CHARGE_TYPE_CLUB_COUPON = "club_coupon";
	    /** Tax. */
        public const string CHARGE_TYPE_TAX = "tax";
    
        /** All known charges. */
        public static readonly IList<string> ALL_CHARGE_TYPES = new List<string> {
            CHARGE_TYPE_DELIVERY, CHARGE_TYPE_COUPON, CHARGE_TYPE_CLUB_COUPON, CHARGE_TYPE_TAX
        };
    
	    /** Amount rule is a fixed number. */
        public const string AMOUNT_RULE_TYPE_FIXED = "fixed";
	    /** Amount rule is percentage of some baseline amount. */
        public const string AMOUNT_RULE_TYPE_PERCENTAGE = "percentage";
	    /** Amount rule is variable, depending on external factors. */
        public const string AMOUNT_RULE_TYPE_VARIABLE = "variable";

        /** All known amount rule types. */
        public static readonly IList<string> ALL_AMOUNT_RULE_TYPES = new List<string> {
            AMOUNT_RULE_TYPE_FIXED, AMOUNT_RULE_TYPE_PERCENTAGE, AMOUNT_RULE_TYPE_VARIABLE
        };

        /** Constructs a previously submitted charge from persisted data. */
        public Charge(string id, string restaurantId, string type, double? priority, string code,
            string tagId, string tagMode,
            string amountRuleType, int? amountRule, Coupon coupon, int? amount) {

    	    this.id = id;
    	    this.restaurantId = restaurantId;
            this.type = type;
            this.priority = priority;
            this.code = code;
            this.tagId = tagId;
            this.tagMode = tagMode;
            this.amountRuleType = amountRuleType;
            this.amountRule = amountRule;
            this.coupon = coupon;
            this.amount = amount;
        }
    
        /** Constructs a new charge to be submitted. */
        public Charge(string type, double? priority, string code,
    		    string tagId, string tagMode,
    		    string amountRuleType, int? amountRule, Coupon coupon) :
    	    this(null, null, type, priority, code, tagId, tagMode, amountRuleType, amountRule, coupon, null)
        {}

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Charge() {}

        /** Charge id. */
        public string id;
    
        /** Restaurant id. */
        public string restaurantId;
    
        /** Charge type. */
        public string type;

        /** Charge priority. Lower numbers appear first in the list. */
        public double? priority = 0.0;
    
        /** Optional activation code, e.g. GoDaddy-style, member id. */
        public string code;

        /** Optional internal club-id. */
        public string clubId;
    
        /** Items for which the charge applies, null if applies for every item. */
        public string tagId;
   
        /** Tag mode: inclusive or exclusive. */
        public string tagMode = Tag.TAG_MODE_INCLUDE;
    
        /** Charge amount rule type. */
        public string amountRuleType = AMOUNT_RULE_TYPE_VARIABLE;
    
        /**
         * Charge amount rule (cents for fixed amounts, basis points for percentage
         * amounts, undefined for variable amounts).
         * 
         * Basis points are 1/100th of a percentage, e.g. -500 bp referes to 5% discount. 
         * 
         * Positive numbers are extra charges, negatives are discounts.
         */
        public int? amountRule;
    
        /** Coupon information (valid for CHARGE_TYPE_COUPON and CHARGE_TYPE_CLUB_COUPON). */
        public Coupon coupon;
    
        /** Bottom-line charge amount (in cents). */
        public int? amount;
    }
}
