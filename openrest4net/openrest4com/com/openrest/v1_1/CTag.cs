using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CTag : ITag
    {
        public CTag(Tag tag)
        {
            this.tag = tag;
        }

        public string GetId()
        {
            return tag.id;
        }

        public string GetRestaurantId()
        {
            return tag.restaurantId;
        }

        /*
        public string GetTitle()
        {
            return tag.title;
        }*/

        public string[] GetItemIds()
        {
            string[] arr = new string[tag.itemIds.Count];
            tag.itemIds.CopyTo(arr, 0);
            return arr;
        }

        private readonly Tag tag;
    }
}
