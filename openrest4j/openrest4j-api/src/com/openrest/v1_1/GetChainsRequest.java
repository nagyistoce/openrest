package com.openrest.v1_1;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetChainsRequest extends Request {
	public static final String TYPE = "get_chains";
    private static final long serialVersionUID = 1L;
    
    /** Default constructor for JSON deserialization. */
    public GetChainsRequest() {}
    
    public GetChainsRequest(Set<String> fields) {
    	this.fields = fields;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Set<String> fields;
}
