using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class Staff
    {
        public const string STAFF_ADMIN = "admin";
        public const string STAFF_MANAGER = "manager";
        public const string STAFF_EMPLOYEE = "employee";

        public Staff(IDictionary<string, IList<string>> staff)
        {
            this.staff = staff;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Staff() { }

        /** Maps roles (see above) to list of user-ids. */
        public IDictionary<string, IList<string>> staff = new Dictionary<string, IList<string>>();
    }
}
