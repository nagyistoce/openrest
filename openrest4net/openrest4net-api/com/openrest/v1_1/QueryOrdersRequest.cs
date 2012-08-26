using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class QueryOrdersRequest : Request
    {
        private static readonly string TYPE = "query_orders";

        public string accessToken;
        public string distributorId;
        public IList<String> restaurantIds;
        public string userId;
        public string status;
        //public string ref;
        public long? since;
        public long? until;
        public bool? restaurantView = false;
        public string ordering;
	    public IList<string> fields;
        public int? limit;

        /** Empty constructor required for initialization from JSON-encoded string. */
        public QueryOrdersRequest()
            : base(TYPE)
        {
        }

        public QueryOrdersRequest(string accessToken, string distributorId, IList<String> restaurantIds, string userId,
            string status, long? since, long? until, bool? restaurantView, string ordering, IList<string> fields, int? limit)
            : base(TYPE)
        {
            this.accessToken = accessToken;
            this.distributorId = distributorId;
            this.restaurantIds = restaurantIds;
            this.userId = userId;
            this.status = status;
            this.since = since;
            this.until = until;
            this.restaurantView = restaurantView;
            this.ordering = ordering;
            this.fields = fields;
            this.limit = limit;
        }
    }
}
