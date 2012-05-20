package com.googlecode.openrest.v1_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
public class Distributor extends Organization implements Cloneable {
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
    
    @Override
	public Object clone() {
    	final List<AppInfo> clonedApps;
    	if (apps != null) {
    		clonedApps = new ArrayList<AppInfo>(apps.size());
    		for (AppInfo app : apps) {
    			clonedApps.add((AppInfo) app.clone());
    		}
    	} else {
    		clonedApps = null;
    	}
    	
    	return new Distributor(id, created, modified,
    			((title != null) ? new HashMap<String, String>(title) : null),
    			((description != null) ? new HashMap<String, String>(description) : null),
    			locale,
    			((locales != null) ? new HashSet<String>(locales) : null),
    			((colorScheme != null) ? (ColorScheme)colorScheme.clone() : null),
    			((contact != null) ? (Contact) contact.clone() : null),
    			((address != null) ? (Address) address.clone() : null),
    			timezone, link, domain,
    			((altDomains != null) ? new HashSet<String>(altDomains) : null),
    			clonedApps,
    			((seo != null) ? (Seo) seo.clone() : null),
    			((properties != null) ? new HashMap<String, String>(properties) : null),
    			facebookAppId, picture, icon, noImagePicture);
	}
}
