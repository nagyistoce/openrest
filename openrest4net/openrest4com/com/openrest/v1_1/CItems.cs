using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CItems : IItems
    {
        public CItems(IList<Item> items)
        {
            cItems = new CItem[items.Count];
            int i = 0;
            foreach (Item item in items)
            {
                cItems[i++] = new CItem(item);
            }
        }

        public int GetCount()
        {
            return cItems.Length;
        }

        public IItem Get(int i)
        {
            return cItems[i];
        }

        private CItem[] cItems;
    }
}
