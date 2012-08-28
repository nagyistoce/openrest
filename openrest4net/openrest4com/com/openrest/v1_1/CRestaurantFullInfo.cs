using System;
using System.Runtime.InteropServices;
using com.openrest.v1_1;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CRestaurantFullInfo : COpenrestObject, IRestaurantFullInfo
    {
        private readonly RestaurantFullInfo restaurantFullInfo;

        public CRestaurantFullInfo(RestaurantFullInfo restaurantFullInfo) : base(restaurantFullInfo)
        {
            this.restaurantFullInfo = restaurantFullInfo;
        }

        public IRestaurant GetRestaurant()
        {
            return new CRestaurant(restaurantFullInfo.restaurant);
        }

        public IMenu GetMenu()
        {
            return new CMenu(restaurantFullInfo.menu);
        }

        public ICharges GetCharges()
        {
            return new CCharges(restaurantFullInfo.charges);
        }

        public IDistributor GetDistributor()
        {
            return new CDistributor(restaurantFullInfo.distributor);
        }

        public IChain GetChain()
        {
            return new CChain(restaurantFullInfo.chain);
        }

        public string[] GetFbAdmins()
        {
            return (string[]) restaurantFullInfo.fbAdmins;
        }
    }
}
