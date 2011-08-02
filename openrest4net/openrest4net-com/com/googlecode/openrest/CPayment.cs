using System;

namespace com.googlecode.openrest
{
    public class CPayment
    {
        public CPayment(Payment payment)
        {
            this.payment = payment;
        }

        public string GetPaymentType()
        {
            return payment.type;
        }

        public int? GetAmount()
        {
            return payment.amount;
        }

        public CCreditCard GetCard()
        {
            return ((payment.card != null) ? new CCreditCard(payment.card) : null);
        }

        private readonly Payment payment;
    }
}
