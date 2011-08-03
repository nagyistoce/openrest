using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    public class CVariationsChoices : IVariationsChoices
    {
        public CVariationsChoices(IList<IList<OrderItem>> variationsChoices)
        {
            cVariationsChoices = new COrderItems[variationsChoices.Count];
            int i = 0;
            foreach (IList<OrderItem> choices in variationsChoices)
            {
                cVariationsChoices[i++] = new COrderItems(choices);
            }
        }

        public int GetCount()
        {
            return cVariationsChoices.Length;
        }

        public IOrderItems Get(int i)
        {
            return cVariationsChoices[i];
        }

        private COrderItems[] cVariationsChoices;
    }
}
