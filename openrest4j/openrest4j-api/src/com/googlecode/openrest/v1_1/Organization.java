package com.googlecode.openrest.v1_1;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    
    protected Organization(String id, Map<String, String> title, Map<String, String> description,
    		Contact contact, Address address, String locale, List<String> locales, String link,
    		String picture, String icon, Map<String, String> properties) {
    	this.id = id;
    	this.title = title;
    	this.description = description;
    	this.contact = contact;
    	this.address = address;
    	this.locale = locale;
    	this.locales = locales;
    	this.link = link;
    	this.picture = picture;
    	this.icon = icon;
    	this.properties = properties;
    }
    
    /** The organization's unique id. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String id;
    
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
    
    /** The organization's default locale, e.g. "en_US". */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String locale;
    
    /** The organization's supported locales. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<String> locales = Collections.emptyList();
    
    /** The organization's web-site URL. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String link;
    
    /** The organization's picture URL (direct link), or null if unavailable. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String picture;
    
    /** The organization's icon URL (direct link), or null if unavailable. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String icon;
    
    /**
     * Map of user-defined extended properties. Developers should use unique
     * keys, e.g. "com.googlecode.openrestext".
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, String> properties = Collections.emptyMap();
}
