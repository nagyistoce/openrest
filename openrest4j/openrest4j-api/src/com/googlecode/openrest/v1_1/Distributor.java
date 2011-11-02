package com.googlecode.openrest.v1_1;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Distributor information.
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Distributor extends Organization {
    private static final long serialVersionUID = 1L;

    /** Default constructor for JSON deserialization. */
    public Distributor() {}
    
    public Distributor(String id, Long created, Map<String, String> title, Map<String, String> description,
    		Contact contact, Address address, String timezone, String locale, List<String> locales,
    		String link, String picture, String icon, Map<String, String> properties,
    		String facebookAppId, String domain, String noImagePicture) {
    	super(id, created, title, description, contact, address, timezone, locale, locales,
    			link, picture, icon, properties);
    	
    	this.facebookAppId = facebookAppId;
    	this.domain = domain;
    	this.noImagePicture = noImagePicture;
    }
    
    /** The distributor's Facebook application id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String facebookAppId;
    
    /** The user facing domain. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String domain;
    
    /** The picture shown when a restaurant / item picture is missing. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String noImagePicture;
}
