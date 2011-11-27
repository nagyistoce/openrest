package com.googlecode.openrest.v1_1;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Base class for Restaurants and Distributers.
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** Default constructor for JSON deserialization. */
    public Organization() {}
    
    protected Organization(String id, Long created, Map<String, String> title, Map<String, String> description,
    		Contact contact, Address address, String timezone, String locale, List<String> locales,
    		String link, String domain, String picture, String icon, List<AppInfo> apps,
    		Map<String, String> properties) {
    	this.id = id;
    	this.created = created;
    	this.title = title;
    	this.description = description;
    	this.contact = contact;
    	this.address = address;
    	this.timezone = timezone;
    	this.locale = locale;
    	this.locales = locales;
    	this.link = link;
    	this.domain = domain;
    	this.picture = picture;
    	this.icon = icon;
    	this.apps = apps;
    	this.properties = properties;
    }
    
    /** The organization's unique id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String id;
    
    /** The organization's creation (NOT establishment!) timestamp. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Long created;
    
    public java.util.Date created() {
        return ((created != null) ? new java.util.Date(created.longValue()) : null);
    }
    
    /** The organization's title in various locales. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, String> title = Collections.emptyMap();

    /** The organization's description or tagline in various locales. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, String> description = Collections.emptyMap();

    /** The organization's contact. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Contact contact;

    /** The address of this organization. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public Address address;
    
    /**
     * The restaurant's timezone.
     * @see http://en.wikipedia.org/wiki/List_of_tz_database_time_zones
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String timezone;
    
    public TimeZone timezone() {
        return TimeZone.getTimeZone(timezone);
    }
    
    /** The organization's default locale, e.g. "en_US". */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String locale;
    
    /** The organization's supported locales. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<String> locales = Collections.emptyList();
    
    /** The organization's main web-site URL. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String link;
    
    /** The organization's online ordering domain. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String domain;
    
    /** The organization's picture URL (direct link), or null if unavailable. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String picture;
    
    /** The organization's icon URL (direct link), or null if unavailable. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String icon;
    
    /** The organization's applications. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<AppInfo> apps = Collections.emptyList();
    
    /**
     * Map of user-defined extended properties. Developers should use unique
     * keys, e.g. "com.googlecode.openrestext".
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, String> properties = Collections.emptyMap();
}
