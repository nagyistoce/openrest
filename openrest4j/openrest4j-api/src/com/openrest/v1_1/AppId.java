package com.openrest.v1_1;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Application (e.g. iPhone app) information.
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppId implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
    
    /** Android platform. */
    public static final String PLATFORM_ANDROID = "android";
	/** iOS platform. */
    public static final String PLATFORM_IOS = "ios";
    
    /** All known platforms. */
    public static final Set<String> ALL_PLATFORMS = new HashSet<String>(Arrays.asList(
    		PLATFORM_ANDROID, PLATFORM_IOS
    ));
    
    public AppId(String platform, String id, String version) {
        this.platform = platform;
        this.id = id;
        this.version = version;
    }

    /** Default constructor for JSON deserialization. */
    public AppId() {}
    
    @Override
	public Object clone() {
    	return new AppId(platform, id, version);
	}

    /** Application platform (@see ALL_PLATFORMS) */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String platform;

    /**
     * Application id, in a platform specific format:
     * iOS platform -> "bundle id"
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String id;
    
    /** The application's version. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String version;
    
	@Override
	public String toString() {
		return "AppId [platform=" + platform + ", id=" + id + ", version=" + version + "]";
	}
}
