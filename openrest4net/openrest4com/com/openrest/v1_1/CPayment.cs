using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CPayment : IPayment
    {
        public CPayment(Payment payment)
        {
            this.payment = payment;
        }

        public string GetPaymentType()
        {
            return payment.type;
        }

        public int GetAmount()
        {
            return payment.amount.Value;
        }

        public ICreditCard GetCard()
        {
            return ((payment.card != null) ? new CCreditCard(payment.card) : null);
        }

        private readonly Payment payment;
    }
}
