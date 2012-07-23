package com.openrest.v1_1;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.googlecode.openrest.v1_1.Item;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopItem implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
    
    public TopItem(Map<String, String> title, Integer price) {
    	this.title = title;
    	this.price = price;
    }
    
    public TopItem(Item item) {
    	this(item.title, item.price);
    }
    
    /** Default constructor for JSON deserialization. */
    public TopItem() {}
    
    @Override
	public Object clone() {
    	return new TopItem(
    			((title != null) ? new HashMap<String, String>(title) : null),
    			price);
	}
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, String> title = Collections.emptyMap();
    
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer price = 0;
}
