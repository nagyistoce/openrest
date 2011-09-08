package com.googlecode.openrest.v1_1;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditCard implements Serializable, Cloneable {
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

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String number;

    /** 1-based */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer expireMonth;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer expireYear;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String holderId;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String holderName;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Boolean anonymized = Boolean.FALSE;

    private static final long serialVersionUID = 1L;
}
