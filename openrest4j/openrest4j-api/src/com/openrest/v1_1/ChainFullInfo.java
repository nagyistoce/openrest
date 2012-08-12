package com.openrest.v1_1;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChainFullInfo extends OpenrestObject {
    private static final long serialVersionUID = 1L;
    
    public ChainFullInfo(Chain chain, Distributor distributor) {
    	this.chain = chain;
    	this.distributor = distributor;
    }
    
    /** Default constructor for JSON deserialization. */
    public ChainFullInfo() {}
    
    /** The chain. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Chain chain;
    
    /** The distributor. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Distributor distributor;
}
