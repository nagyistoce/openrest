package com.openrest.v1_1;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SetBlobRequest extends Request {
    private static final long serialVersionUID = 1L;
    
    /** Default constructor for JSON deserialization. */
    public SetBlobRequest() {}
    
    public SetBlobRequest(String accessToken, String organizationId, String blobType, String blobId) {
    	this.accessToken = accessToken;
    	this.organizationId = organizationId;
    	this.blobType = blobType;
    	this.blobId = blobId;
    }
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String accessToken;
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String organizationId;

    /** One of Organization.ALL_BLOB_TYPES */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String blobType;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String blobId;
}
