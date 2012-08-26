package com.openrest.v1_1;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,  
	    include = JsonTypeInfo.As.PROPERTY,  
	    property = "type")  
@JsonSubTypes({
	@Type(value = GetOrganizationRequest.class, name = "get_organization"),
	@Type(value = SetOrganizationRequest.class, name = "set_organization"),
	@Type(value = AddOrganizationRequest.class, name = "add_organization"),
	@Type(value = GetChainsRequest.class, name = "get_chains"),
	@Type(value = GetDistributorsRequest.class, name = "get_distributors"),
	@Type(value = GetOrganizationFullRequest.class, name = "get_organization_full"),
	@Type(value = GetOrganizationsFullRequest.class, name = "get_organizations_full"),
	@Type(value = SearchRequest.class, name = "search"),
	@Type(value = QueryOrdersRequest.class, name = "query_orders"),
	@Type(value = GetOrderRequest.class, name = "get_order"),
	@Type(value = DisplayOrderRequest.class, name = "display_order"),
	@Type(value = SubmitOrderRequest.class, name = "submit_order"),
	@Type(value = SetOrderStatusRequest.class, name = "set_order_status"),
	@Type(value = AddPaymentRequest.class, name = "add_payment"),
	@Type(value = CopyMenuRequest.class, name = "copy_menu"),
	@Type(value = RegisterForEventRequest.class, name = "register_event"),
	@Type(value = SetAppMappingRequest.class, name = "set_app_mapping"),
	@Type(value = GetAppMappedObjectRequest.class, name = "get_app_mapped_object"),
	@Type(value = SetDomainMappingRequest.class, name = "set_domain_mapping"),
	@Type(value = GetDomainMappedObjectRequest.class, name = "get_domain_mapped_object"),
	@Type(value = GetBlobRequest.class, name = "get_blob"),
	@Type(value = SetBlobRequest.class, name = "set_blob"),
	@Type(value = GetRolesRequest.class, name = "get_roles"),
	@Type(value = SetAvailabilityExceptionsRequest.class, name = "set_availability_exceptions"),
	@Type(value = QueryEmailRequest.class, name = "query_email"),
	@Type(value = DeferredRequest.class, name = "deferred_request"),
	@Type(value = SetItemRequest.class, name = "set_item"),
	@Type(value = GetStatsRequest.class, name = "get_stats"),
	@Type(value = GetStaffRequest.class, name = "get_staff"),
	@Type(value = SetStaffRequest.class, name = "set_staff"),
	@Type(value = GetNotificationsRequest.class, name = "get_notifications"),
	@Type(value = SetNotificationsRequest.class, name = "set_notifications")
})
public abstract class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** Default constructor for JSON deserialization. */
    public Request() {}
}
