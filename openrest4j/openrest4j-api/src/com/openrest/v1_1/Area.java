package com.openrest.v1_1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * An area on a map.
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Area implements Serializable, Cloneable {
	public Area(Map<String, String> title, List<LatLng> polygon) {
    	this.title = title;
    	this.polygon = polygon;
    }

    /** Default constructor for JSON deserialization. */
    public Area() {}
    
    @Override
	public Object clone() {
    	final List<LatLng> clonedPolygon;
    	if (polygon != null) {
    		clonedPolygon = new ArrayList<LatLng>(polygon.size());
    		for (LatLng latLng : polygon) {
    			clonedPolygon.add((LatLng) latLng.clone());
    		}
    	} else {
    		clonedPolygon = null;
    	}
    	
    	return new Area(
    			((title != null) ? new HashMap<String, String>(title) : null),
    			clonedPolygon);
	}
    
    /** The area's human-readable title in various locales. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, String> title = Collections.emptyMap();
    
    /** The area (polygon vertices). */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public List<LatLng> polygon = Collections.emptyList();
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((polygon == null) ? 0 : polygon.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Area other = (Area) obj;
		if (polygon == null) {
			if (other.polygon != null)
				return false;
		} else if (!polygon.equals(other.polygon))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;
}
