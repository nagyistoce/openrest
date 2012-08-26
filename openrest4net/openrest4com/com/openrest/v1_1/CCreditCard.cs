using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CCreditCard : ICreditCard
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

        public INullableInt GetExpireMonth()
        {
            return new CNullableInt(card.expireMonth);
        }

        public INullableInt GetExpireYear()
        {
            return new CNullableInt(card.expireYear);
        }

        public string GetHolderId()
        {
            return card.holderId;
        }

        public string GetHolderName()
        {
            return card.holderName;
        }

        public bool GetAnonymized()
        {
            return card.anonymized.Value;
        }

        private readonly CreditCard card;
    }
}
