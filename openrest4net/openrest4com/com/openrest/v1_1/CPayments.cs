using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CPayments : IPayments
    {
        public CPayments(IList<Payment> payments)
        {
            cPayments = new CPayment[payments.Count];
            int i = 0;
            foreach (Payment payment in payments)
            {
                cPayments[i++] = new CPayment(payment);
            }
        }

        public int GetCount()
        {
            return cPayments.Length;
        }

        public IPayment Get(int i)
        {
            return cPayments[i];
        }

        private CPayment[] cPayments;
    }
}
