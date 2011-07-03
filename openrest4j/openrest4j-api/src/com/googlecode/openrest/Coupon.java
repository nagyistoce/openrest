package com.googlecode.openrest;
 
import java.io.Serializable;
 
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
 
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coupon implements Serializable {
    /** Discount coupon. */
    public static final String COUPON_TYPE_DISCOUNT = "discount";
    /** M+N coupon. */
    public static final String COUPON_TYPE_M_PLUS_N = "m_plus_n";
   
    public Coupon(String type, String title) {
    	this.type = type;
    	this.title = title;
    }
    
    /** Default constructor for JSON deserialization. */
    public Coupon() {}
   
    /** The coupon's type. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String type;
   
    /** The coupon's user-friendly name. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String title;
    
    /** Maximum number of times this coupon can be used in a single order. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Integer maxNumAllowed = Integer.MAX_VALUE;
   
    /** Whether or not other coupons can be used with this one. */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    public Boolean othersAllowed = Boolean.TRUE;
 
    private static final long serialVersionUID = 1L;
}