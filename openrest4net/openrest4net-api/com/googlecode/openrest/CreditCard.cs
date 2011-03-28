using System;

namespace com.googlecode.openrest
{
    class CreditCard
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
        internal CreditCard() { }

        public string number;
        /** 1-based */
        public int? expireMonth;
        public int? expireYear;
        public string holderId;
        public string holderName;
        public bool? anonymized = false;
    }
}
