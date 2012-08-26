using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CVariations : IVariations
    {
        public CVariations(IList<Variation> variations)
        {
            cVariations = new CVariation[variations.Count];
            int i = 0;
            foreach (Variation variation in variations)
            {
                cVariations[i++] = new CVariation(variation);
            }
        }

        public int GetCount()
        {
            return cVariations.Length;
        }

        public IVariation Get(int i)
        {
            return cVariations[i];
        }

        private CVariation[] cVariations;
    }
}
