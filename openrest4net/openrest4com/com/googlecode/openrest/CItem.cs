using System;
using System.Runtime.InteropServices;

namespace com.googlecode.openrest
{
    [ComVisible(true)]
    public class CItem : IItem
    {
        public CItem(Item item)
        {
            this.item = item;
        }

        public string GetId()
        {
            return item.id;
        }

        public string GetRestaurantId()
        {
            return item.restaurantId;
        }

        public string GetTitle()
        {
            return item.title;
        }

        public string GetDescription()
        {
            return item.description;
        }

        public int GetPrice()
        {
            return item.price.Value;
        }

        public IVariations GetVariations()
        {
            return new CVariations(item.variations);
        }

        public string GetAvailabilityStr()
        {
            return item.availabilityStr;
        }

        public bool GetInactive()
        {
            return item.inactive.Value;
        }

        public string GetPicture()
        {
            return item.picture;
        }

        public string GetExternalId(string key)
        {
            return (item.externalIds.ContainsKey(key) ? item.externalIds[key] : null);
        }

        private readonly Item item;
    }
}
