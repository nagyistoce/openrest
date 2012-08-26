using System;

namespace com.openrest.v1_1
{
    public class CreditCard
    {
        public CreditCard(string number, int expireMonth, int expireYear, string holderId,
            string holderName, bool anonymized)
        {
            this.number = number;
            this.expireMonth = expireMonth;
            this.expireYear = expireYear;
            this.holderId = holderId;
            this.holderName = holderName;
            this.anonymized = anonymized;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public CreditCard() { }

        /** The card type, e.g. "visa", "mastercard", "maestro". */
        public string type;

        /** The card number (digits only). */
        public string number
            ;
        /** Card expiration month (1-based). Non-expiring cards can ignore this. */
        public int? expireMonth;
        /** Card expiration year. Non-expiring cards can ignore this. */
        public int? expireYear;

        /** Optional card holder-id (e.g. government issued unique identity card number). */
        public string holderId;

        /** Card holder name (required). */
        public string holderName;

        /** Card issue month (1-based), e.g for Maestro. */
        public int? validFromMonth;
        /** Card issue year, e.g for Maestro. */
        public int? validFromYear;
        /** Card issue number, e.g for Maestro. */
        public string issueNumber;
    
        /** Card billing address. */
        public string billingAddress;
        /** Card billing postal code. */
        public string billingPostalCode;

        /**
         * Card security code.
         * @see http://en.wikipedia.org/wiki/Card_security_code
         */
        public string csc;

        public bool? anonymized = false;
    }
}
