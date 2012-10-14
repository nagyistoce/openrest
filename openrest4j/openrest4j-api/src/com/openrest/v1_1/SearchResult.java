package com.openrest.v1_1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult extends Restaurant {
    private static final long serialVersionUID = 1L;
    
    public SearchResult(String id, Map<String, String> externalIds, Long created, Long modified,
    		String distributorId, String chainId, Map<String, String> title,
    		Map<String, String> description, Contact contact, Map<String, Contact> externalContacts, Address address,
    		Map<String, Map<String, String>> messages, ColorScheme colorScheme,
    		Availability openTimes, Availability deliveryTimes,
            Boolean inactive, List<DeliveryInfo> deliveryInfos, Status status, Status deliveryStatus,
            String timezone, String currency, String locale, Set<String> locales,
            Set<String> paymentTypes, Map<String, CardInfo> cardInfos, Map<String, Integer> minPayments,
            String link, String domain, Set<String> altDomains,
            String picture, String icon, String noImagePicture,
            List<AppInfo> apps, Seo seo, Map<String, String> properties,
            String state, Map<String, Double> features, Boolean legacyHierarchy, Double rank, List<TopItem> topItems,
            Set<String> deliveryTypes, List<Charge> charges) {
    	super(id, externalIds, created, modified, distributorId, chainId, title, description, contact, externalContacts,
    			address, messages, colorScheme, openTimes, deliveryTimes, inactive, deliveryInfos, status, deliveryStatus, timezone,
    			currency, locale, locales, paymentTypes, cardInfos, minPayments, link, domain, altDomains, picture, icon, noImagePicture,
    			apps, seo, properties, state, features, legacyHierarchy, rank);
    	
    	this.topItems = topItems;
    	this.deliveryTypes = deliveryTypes;
    	this.charges = charges;
    }
    
    /** Default constructor for JSON deserialization. */
    public SearchResult() {}
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<TopItem> topItems = new ArrayList<TopItem>();
    
    /** Supported delivery types (optimization to avoid getting the entire deliveryInfos field). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Set<String> deliveryTypes = new HashSet<String>();
    
    /** Available charges (for discounts search). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<Charge> charges = new ArrayList<Charge>();
}
