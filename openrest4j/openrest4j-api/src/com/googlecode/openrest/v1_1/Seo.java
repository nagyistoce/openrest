package com.googlecode.openrest.v1_1;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Seo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public Seo(String title, Map<String, String> meta, String html) {
    	this.title = title;
    	this.meta = meta;
    	this.html = html;
    }
    
    /** Default constructor for JSON deserialization. */
    public Seo() {}
    
    /** The HTML title. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String title;
    
    /** HTML meta tags. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, String> meta = Collections.emptyMap();
    
    /** Marketing HTML block to embed in the web-site. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String html;
}
