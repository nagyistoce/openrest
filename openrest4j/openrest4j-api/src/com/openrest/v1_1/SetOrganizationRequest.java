package com.openrest.v1_1;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SetOrganizationRequest extends Request {
    private static final long serialVersionUID = 1L;
    
    /** Default constructor for JSON deserialization. */
    public SetOrganizationRequest() {}
    
    public SetOrganizationRequest(String accessToken, Organization organization) {
    	this.accessToken = accessToken;
    	this.organization = organization;
    }
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String accessToken;
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Organization organization;
}
