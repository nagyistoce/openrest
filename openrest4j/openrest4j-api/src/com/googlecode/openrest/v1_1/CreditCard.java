package com.googlecode.openrest.v1_1;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditCard implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    
	public CreditCard(String number, Integer expireMonth, Integer expireYear,
            String holderId, String holderName, Boolean anonymized) {
        this.number = number;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.holderId = holderId;
        this.holderName = holderName;
        this.anonymized = anonymized;
    }

    /** Default constructor for JSON deserialization. */
    public CreditCard() {}
    
    @Override
	public Object clone() {
    	return new CreditCard(number, expireMonth, expireYear, holderId, holderName, anonymized);
	}

    /** The card number (digits only). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String number;

    /** Card expiration month (1-based). Non-expiring cards can ignore this. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer expireMonth;

    /** Card expiration year. Non-expiring cards can ignore this. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer expireYear;

    /** Optional card holder-id (e.g. government issued unique identity card number). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String holderId;

    /** Card holder name (required). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String holderName;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Boolean anonymized = Boolean.FALSE;
}
