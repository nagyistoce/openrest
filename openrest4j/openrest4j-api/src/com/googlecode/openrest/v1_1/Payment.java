package com.googlecode.openrest.v1_1;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment implements Serializable {
    /** Cash payment. */
    public static final String PAYMENT_TYPE_CASH = "cash";
    /** Credit card payment. */
    public static final String PAYMENT_TYPE_CREDIT = "credit";
    /** Debit card payment. */
    public static final String PAYMENT_TYPE_DEBIT = "debit";
    /** Payment by 10bis card (@see www.10bis.co.il). */
    public static final String PAYMENT_TYPE_10BIS = "10bis";

    /** All known payment methods. */
    public static final Set<String> ALL_PAYMENT_TYPES = new HashSet<String>(Arrays.asList(new String[] {
    		PAYMENT_TYPE_CASH, PAYMENT_TYPE_CREDIT, PAYMENT_TYPE_DEBIT, PAYMENT_TYPE_10BIS
    }));

    public Payment(String type, Integer amount, CreditCard card) {
        this.type = type;
        this.amount = amount;
        this.card = card;
    }

    /** Default constructor for JSON deserialization. */
    public Payment() {}

    /** Payment type. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String type;

    /** Amount to pay. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer amount = 0;

    /** Credit card details (not valid for PAYMENT_TYPE_CASH or PAYMENT_CARD_DEBIT) */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public CreditCard card;

    private static final long serialVersionUID = 1L;
}
