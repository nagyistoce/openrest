package com.googlecode.openrest;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact implements Serializable {
    public Contact(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
    
    /** Default constructor for JSON deserialization. */
    public Contact() {}

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String firstName;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String lastName;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String email;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String phone;
    
    private static final long serialVersionUID = 1L;
}
