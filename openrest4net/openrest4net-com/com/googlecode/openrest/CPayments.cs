using System;
using System.Collections.Generic;

namespace com.googlecode.openrest
{
    public class CPayments
    {
        public CPayments() { }

        public CPayments(IList<Payment> payments)
        {
            cPayments = new CPayment[payments.Count];
            int i = 0;
            foreach (Payment payment in payments)
            {
                cPayments[i] = new CPayment(payment);
            }
        }

        public int Count()
        {
            return cPayments.Length;
        }

        public CPayment Get(int i)
        {
            return cPayments[i];
        }

        private CPayment[] cPayments;
    }
}
