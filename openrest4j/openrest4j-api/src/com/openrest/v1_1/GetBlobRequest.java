package com.openrest.v1_1;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBlobRequest extends Request {
    private static final long serialVersionUID = 1L;
    
    /** Default constructor for JSON deserialization. */
    public GetBlobRequest() {}
    
    public GetBlobRequest(String organizationId, String blobType) {
    	this.organizationId = organizationId;
    	this.blobType = blobType;
    }
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String organizationId;

    /** One of Organization.ALL_BLOB_TYPES */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String blobType;
}
