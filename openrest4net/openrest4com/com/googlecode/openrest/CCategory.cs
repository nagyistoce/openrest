using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    public class CCategory : ICategory
    {
        public CCategory(Category category)
        {
            this.category = category;
        }

        public string GetId()
        {
            return category.id;
        }

        public string GetRestaurantId()
        {
            return category.restaurantId;
        }

        public string GetTitle()
        {
            return category.title;
        }

        public string GetDescription()
        {
            return category.description;
        }

        public string GetParentCategoryId()
        {
            return category.parentCategoryId;
        }

        public string[] GetItemIds()
        {
            string[] arr = new string[category.itemIds.Count];
            category.itemIds.CopyTo(arr, 0);
            return arr;
        }

        public double GetPriority()
        {
            return category.priority.Value;
        }

        private readonly Category category;
   }
}
