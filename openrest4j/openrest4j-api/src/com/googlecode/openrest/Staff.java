package com.googlecode.openrest;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Staff implements Serializable {
    public static final String STAFF_ADMIN = "admin";
    public static final String STAFF_MANAGER = "manager";
    public static final String STAFF_EMPLOYEE = "employee";

    public Staff(Map<String, List<String>> staff) {
        this.staff = staff;
    }

    /** Default constructor for JSON deserialization. */
    public Staff() {}

    public static Staff createAdmins(List<String> adminStaff) {
        final Map<String, List<String>> staff = new HashMap<String, List<String>>(1);
        staff.put(STAFF_ADMIN, adminStaff);
        return new Staff(staff);
    }

    public static Staff createManagers(List<String> managerStaff) {
        final Map<String, List<String>> staff = new HashMap<String, List<String>>(1);
        staff.put(STAFF_MANAGER, managerStaff);
        return new Staff(staff);
    }

    public static Staff createEmployees(List<String> employeeStaff) {
        final Map<String, List<String>> staff = new HashMap<String, List<String>>(1);
        staff.put(STAFF_EMPLOYEE, employeeStaff);
        return new Staff(staff);
    }

    public static Staff createRestaurantStaff(List<String> managerStaff, List<String> employeeStaff) {
        final Map<String, List<String>> staff = new HashMap<String, List<String>>(2);
        staff.put(STAFF_MANAGER, managerStaff);
        staff.put(STAFF_EMPLOYEE, employeeStaff);
        return new Staff(staff);
    }

    /** Maps roles (see above) to list of user-ids. */
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
    public Map<String, List<String>> staff = Collections.emptyMap();

    private static final long serialVersionUID = 1L;
}
