using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class Coupon
    {
        /** Discount coupon. */
        public const string COUPON_TYPE_DISCOUNT = "discount";
        /** M+N coupon. */
        public const string COUPON_TYPE_M_PLUS_N = "m_plus_n";

        /** All known coupouns. */
        public static readonly IList<string> ALL_COUPON_TYPES =
            new List<string> { COUPON_TYPE_DISCOUNT, COUPON_TYPE_M_PLUS_N };

        public Coupon(string type, IDictionary<string, string> title, IDictionary<string, string> description, int? maxNumAllowed, bool? othersAllowed)
        {
            this.type = type;
            this.title = title;
            this.description = description;
            this.maxNumAllowed = maxNumAllowed;
            this.othersAllowed = othersAllowed;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Coupon() { }

        /** The coupon's type. */
        public string type;

        /** The coupon's user-friendly short name. */
        public IDictionary<string, string> title = new Dictionary<string, string>();

        /** The coupon's user-friendly description in various locales. */
        public IDictionary<string, string> description = new Dictionary<string, string>();

        /** Maximum number of times this coupon can be used in a single order. */
        public int? maxNumAllowed = Int32.MaxValue;

        /** Whether or not other coupons can be used with this one. */
        public bool? othersAllowed = true;
    }
}
