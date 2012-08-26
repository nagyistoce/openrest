using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CVariation : IVariation
    {
        public CVariation(Variation variation)
        {
            this.variation = variation;
        }

        /*
        public string GetTitle()
        {
            return variation.title;
        }*/

        public string TagId()
        {
            return variation.tagId;
        }

        public int GetMinNumAllowed()
        {
            return variation.minNumAllowed.Value;
        }

        public int GetMaxNumAllowed()
        {
            return variation.maxNumAllowed.Value;
        }

        public int GetPrice(string itemId)
        {
            return (variation.prices.ContainsKey(itemId) ? variation.prices[itemId] : 0);
        }

        public string[] GetDefaults()
        {
            string[] arr = new string[variation.defaults.Count];
            variation.defaults.CopyTo(arr, 0);
            return arr;
        }

        public string GetDisplayType()
        {
            return variation.displayType;
        }

        private readonly Variation variation;
    }
}
