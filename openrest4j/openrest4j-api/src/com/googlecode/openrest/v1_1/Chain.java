package com.googlecode.openrest.v1_1;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Restaurant chain information.
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Chain extends Organization {
    private static final long serialVersionUID = 1L;

    /** Default constructor for JSON deserialization. */
    public Chain() {}
    
    public Chain(String id, String distributorId, Long created, Long modified,
    		Map<String, String> title, Map<String, String> description,
    		String locale, Set<String> locales, ColorScheme colorScheme,
    		Contact contact, Address address, String timezone,
    		String link, String domain, Set<String> altDomains,
    		List<AppInfo> apps, Map<String, String> properties,
    		String picture, String icon, String noImagePicture) {
    	super(id, created, modified, title, description, locale, locales, colorScheme,
    			contact, address, timezone, link, domain, altDomains, apps, properties,
    			picture, icon, noImagePicture);
    	
    	this.distributorId = distributorId;
    }
    
    /** The distributor in charge of this chain. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String distributorId;
}
