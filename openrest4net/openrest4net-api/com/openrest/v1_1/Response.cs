using System;
using System.Collections.Generic;
using Newtonsoft.Json;

namespace com.openrest.v1_1
{
    public class Response<T>
    {
	    /** API used in an unexpected way. Nothing the user can do. */
        public string RESPONSE_ERROR_INVALID_DATA = "invalid_data";
        /** Empty admin list is not allowed. */
        public const string RESPONSE_ERROR_EMPTY_ADMIN_LIST = "empty_admin_list";
        /** Empty managers list is not allowed. */
        public const string RESPONSE_ERROR_EMPTY_MANAGERS_LIST = "empty_manager_list";
        /** Credit card expired. */
        public const string RESPONSE_ERROR_CC_EXPIRED = "cc_expired";
        /** Credit card number is invalid. */
        public const string RESPONSE_ERROR_INVALID_CC_NUMBER = "invalid_cc_number";
        /** User entered illegal date (e.g. 35/5/2010). */
        public const string RESPONSE_ERROR_INVALID_DATE = "invalid_date";
        /**
         * Order cannot be submitted, probably due to a very recent menu update.
         * Probably best thing to do is to forget the order (cookie?) and refresh the page.
         */
        public const string RESPONSE_ERROR_CANNOT_SUBMIT_ORDER = "cannot_submit_order";
        /** Address is outside of delivery area. User can try to enter a different address */
        public const string RESPONSE_ERROR_ADDRESS_NOT_IN_RANGE = "address_not_in_range";
        /**
         * Resource not found, probably due to a very recent menu update.
         * User should probably refresh the page.
         */
        public const string RESPONSE_ERROR_NOT_FOUND = "not_found";
        /** Uploaded file is too big. */
        public const string RESPONSE_ERROR_FILE_SIZE_EXCEEDS_LIMIT = "file_size_exceeds_limit";
        /** Admin tried to add a restaurant with id identical to existing restaurant. */
        public const string RESPONSE_ERROR_RESTAURANT_ID_EXISTS = "restaurant_id_exists";
        /** Something really bad happened (e.g. GAE failed). Please try again later. */
        public const string RESPONSE_ERROR_INTERNAL = "internal";
        /** Address could not be resolved. User can try entering a different address. */
        public const string RESPONSE_ERROR_UNKNOWN_ADDRESS = "unknown_address";
        /** User has no permission to perform the action. Non-clients should probably refresh the page. */
        public const string RESPONSE_ERROR_NO_PERMISSION = "no_permission";
        /**
         * User tried to delete a resource (e.g. tag) that is still referenced by other resources.
         * Delete all references to that resource, and try again.
         */
        public const string RESPONSE_ERROR_CANNOT_DELETE_REFERENCED = "cannot_delete_referenced";
        /**
         * User tried to pay using a method that isn't allowed by the restaurant (e.g. credit-card in cash-only restaurant).
         * Should probably reload the page and try again.
         */
        public const string RESPONSE_ERROR_PAYMENT_METHOD_UNAVAILABLE = "payment_method_unavailable";

        public Response(T value)
        {
            this.timestamp = Utils.ToUnixTime(DateTime.UtcNow);
            this.value = value;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Response() { }

        [JsonIgnore]
        public DateTime Timestamp
        {
            get
            {
                return Utils.FromUnixTime(timestamp.Value);
            }
            set
            {
                timestamp = Utils.ToUnixTime(value);
            }
        }

        public long? timestamp;

        /** Optional error code, one of the above. */
        public string error;

        /** Detailed error information for debugging. */
        public string errorMessage;

        /** The returned value, if no error occurred. */
        public T value;
    }
}
