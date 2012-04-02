package com.googlecode.openrest.v1_1;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditCard implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    
    /** (number & expireMonth & expireYear) */
    public static final String FORM_STANDARD = "standard"; 
    /** FORM_STANDARD & holderId */
    public static final String FORM_STANDARD_WITH_HOLDER_ID = "standard_id";
    /** FORM_STANDARD & (issueNumber | (validFromMonth & validFromYear)) */
    public static final String FORM_MAESTRO = "maestro";
    
    public static final Set<String> ALL_FORMS = new HashSet<String>(Arrays.asList(
    		FORM_STANDARD, FORM_STANDARD_WITH_HOLDER_ID, FORM_MAESTRO));
    
	public CreditCard(String type, String number, Integer expireMonth, Integer expireYear,
            String holderId, String holderName,
            Integer validFromMonth, Integer validFromYear, String issueNumber,
            Boolean anonymized) {
		this.type = type;
        this.number = number;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.holderId = holderId;
        this.holderName = holderName;
        this.validFromMonth = validFromMonth;
        this.validFromYear = validFromYear;
        this.issueNumber = issueNumber;
        this.anonymized = anonymized;
    }

    /** Default constructor for JSON deserialization. */
    public CreditCard() {}
    
    @Override
	public Object clone() {
    	return new CreditCard(type, number, expireMonth, expireYear, holderId, holderName,
    			validFromMonth, validFromYear, issueNumber, anonymized);
	}

    /** The card type, e.g. "visa", "mastercard", "maestro". */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String type;
    
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
    
    /** Card issue month (1-based), e.g for Maestro. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer validFromMonth;

    /** Card issue year, e.g for Maestro. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Integer validFromYear;
    
    /** Card issue number, e.g for Maestro. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String issueNumber;
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Boolean anonymized = Boolean.FALSE;
}
