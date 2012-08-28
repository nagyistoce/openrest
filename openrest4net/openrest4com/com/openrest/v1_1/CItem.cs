using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CItem : IItem
    {
        private readonly Item item;

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

        public IStringMap GetTitle()
        {
            return new CStringMap(item.title);
        }

        public IStringMap GetDescription()
        {
            return new CStringMap(item.description);
        }

        public int GetPrice()
        {
            return item.price.Value;
        }

        public IVariations GetVariations()
        {
            return new CVariations(item.variations);
        }

        public bool GetInactive()
        {
            return item.inactive.Value;
        }

        public string GetPicture()
        {
            return item.picture;
        }

        public IStringMap GetExternalIds()
        {
            return new CStringMap(item.externalIds);
        }
    }
}
