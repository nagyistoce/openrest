package com.googlecode.openrest;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.googlecode.openrest.Address;
import com.googlecode.openrest.Contact;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientInfo implements Serializable {
    public ClientInfo(List<Contact> contacts, List<Address> addresses,
    		List<ClubMember> memberships, Map<String, String> properties) {
    	this.contacts = contacts;
    	this.addresses = addresses;
    	this.memberships = memberships;
    	this.properties = properties;
    }
    
    /** Default constructor for JSON deserialization. */
    public ClientInfo() {}
    
    /** Saved contact details. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<Contact> contacts = Collections.emptyList();
    
    /** Saved addresses. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<Address> addresses = Collections.emptyList();
    
    /** Saved club memberships. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<ClubMember> memberships = Collections.emptyList();

    /**
     * Map of user-defined extended properties. Developers should use unique
     * keys, e.g. "com.googlecode.openrestext".
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, String> properties = Collections.emptyMap();
    
    private static final long serialVersionUID = 1L;
}
