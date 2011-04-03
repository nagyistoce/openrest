using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace com.googlecode.openrest
{
    public class Payment
    {
        /** Cash payment. */
        public const string PAYMENT_TYPE_CASH = "cash";
        /** Credit card payment. */
        public const string PAYMENT_TYPE_CREDIT = "credit";
        /** Payment by 10bis card (@see www.10bis.co.il). */
        public const string PAYMENT_TYPE_10BIS = "10bis";

        /** All payment methods, in ascending alphabetic order! */
        public static readonly IList<string> ALL_PAYMENT_TYPES =
            new List<string> { PAYMENT_TYPE_10BIS, PAYMENT_TYPE_CASH, PAYMENT_TYPE_CREDIT };

        public Payment(string type, int amount, CreditCard card)
        {
            this.type = type;
            this.amount = amount;
            this.card = card;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Payment() { }

        /** Payment type. */
        public string type;

        /** Amount to pay. */
        public int? amount = 0;

        /** Credit card details (valid only if type is PAYMENT_TYPE_CREDIT) */
        public CreditCard card;
    }
}
