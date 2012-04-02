package com.googlecode.openrest.v1_1;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    
    public Distributor(String id, Long created, Long modified,
    		Map<String, String> title, Map<String, String> description,
    		String locale, Set<String> locales, ColorScheme colorScheme,
    		Contact contact, Address address, String timezone, 
    		String link, String domain, Set<String> altDomains,
    		List<AppInfo> apps, Seo seo, Map<String, String> properties, String facebookAppId,
    		String picture, String icon, String noImagePicture) {
    	super(id, created, modified, title, description, locale, locales, colorScheme,
    			contact, address, timezone, link, domain, altDomains, apps, seo, properties,
    			picture, icon, noImagePicture);
    	
    	this.facebookAppId = facebookAppId;
    }
    
    /** The distributor's Facebook application id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String facebookAppId;
}
