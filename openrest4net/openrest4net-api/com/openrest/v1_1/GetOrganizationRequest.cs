using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class GetOrganizationRequest : Request
    {
        public const string TYPE = "get_organization";

        public string organizationId;
        public IList<string> fields;

        /** Empty constructor required for initialization from JSON-encoded string. */
        public GetOrganizationRequest()
            : base(TYPE)
        {
        }

        public GetOrganizationRequest(string organizationId, IList<string> fields)
            : base(TYPE)
        {
            this.organizationId = organizationId;
            this.fields = fields;
        }
    }
}
