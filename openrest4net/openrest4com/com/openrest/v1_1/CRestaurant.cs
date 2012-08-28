using System;
using System.Runtime.InteropServices;
using com.openrest.v1_1;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CRestaurant : COrganization, IRestaurant
    {
        private readonly Restaurant restaurant;

        public CRestaurant(Restaurant restaurant)
            : base(restaurant)
        {
            this.restaurant = restaurant;
        }

        public string GetDistributorId()
        {
            return restaurant.distributorId;
        }

        public string GetChainId()
        {
            return restaurant.chainId;
        }

        public string GetCurrency()
        {
            return restaurant.currency;
        }

        public string[] GetPaymentTypes()
        {
            return (string[]) restaurant.paymentTypes;
        }

        public string GetState()
        {
            return restaurant.state;
        }
    }
}
