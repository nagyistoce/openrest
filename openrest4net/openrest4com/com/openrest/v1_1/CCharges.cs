using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using com.openrest.v1_1;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CCharges : ICharges
    {
        public CCharges(IList<Charge> charges)
        {
            cCharges = new CCharge[charges.Count];
            int i = 0;
            foreach (Charge charge in charges)
            {
                cCharges[i++] = new CCharge(charge);
            }
        }

        public int GetCount()
        {
            return cCharges.Length;
        }

        public ICharge Get(int i)
        {
            return cCharges[i];
        }

        private CCharge[] cCharges;
    }
}
