using System;

namespace com.googlecode.openrest
{
    public class CCreditCard
    {
        public CCreditCard() { }

        public CCreditCard(CreditCard card)
        {
            this.card = card;
        }

        public string GetNumber()
        {
            return card.number;
        }

        public int? GetExpireMonth()
        {
            return card.expireMonth;
        }

        public int? GetExpireYear()
        {
            return card.expireYear;
        }

        public string GetHolderId()
        {
            return card.holderId;
        }

        public string GetHolderName()
        {
            return card.holderName;
        }

        public bool? GetAnonymized()
        {
            return card.anonymized;
        }

        private readonly CreditCard card;
    }
}
