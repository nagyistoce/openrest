package com.openrest.v1_1;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.googlecode.openrest.v1_1.Address;
import com.googlecode.openrest.v1_1.AppInfo;
import com.googlecode.openrest.v1_1.Availability;
import com.googlecode.openrest.v1_1.CardInfo;
import com.googlecode.openrest.v1_1.ColorScheme;
import com.googlecode.openrest.v1_1.Contact;
import com.googlecode.openrest.v1_1.DeliveryInfo;
import com.googlecode.openrest.v1_1.Restaurant;
import com.googlecode.openrest.v1_1.Seo;
import com.googlecode.openrest.v1_1.Status;

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
            String state, Map<String, Double> features, Boolean legacyHierarchy, Double rank, List<TopItem> topItems, Set<String> deliveryTypes) {
    	super(id, externalIds, created, modified, distributorId, chainId, title, description, contact, externalContacts,
    			address, messages, colorScheme, openTimes, deliveryTimes, inactive, deliveryInfos, status, deliveryStatus, timezone,
    			currency, locale, locales, paymentTypes, cardInfos, minPayments, link, domain, altDomains, picture, icon, noImagePicture,
    			apps, seo, properties, state, features, legacyHierarchy, rank);
    	
    	this.topItems = topItems;
    	this.deliveryTypes = deliveryTypes;
    }
    
    /** Default constructor for JSON deserialization. */
    public SearchResult() {}
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<TopItem> topItems = Collections.emptyList();
    
    /** Supported delivery types (optimization to avoid getting the entire deliveryInfos field). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Set<String> deliveryTypes = Collections.emptySet();
}
