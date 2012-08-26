using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CCategories : ICategories
    {
        public CCategories(IList<Category> categories)
        {
            cCategories = new CCategory[categories.Count];
            int i = 0;
            foreach (Category category in categories)
            {
                cCategories[i++] = new CCategory(category);
            }
        }

        public int GetCount()
        {
            return cCategories.Length;
        }

        public ICategory Get(int i)
        {
            return cCategories[i];
        }

        private CCategory[] cCategories;
    }
}
