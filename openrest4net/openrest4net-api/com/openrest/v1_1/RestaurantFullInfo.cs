using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class RestaurantFullInfo : OpenrestObject
    {
        public const string TYPE = "restaurant_full";

        /** The restaurant. */
        public Restaurant restaurant;
    
        /** The menu. */
        public Menu menu;
    
        /** The charges. */
        public IList<Charge> charges;
    
        /** The distributor. */
        public Distributor distributor;
    
        /** The chain. */
        public Chain chain;
    
        /** The restaurant's Facebook admins. */
        public IList<String> fbAdmins = new List<string>();

        /** Empty constructor required for initialization from JSON-encoded string. */
        public RestaurantFullInfo() : base(TYPE) { }

        public RestaurantFullInfo(Restaurant restaurant, Menu menu, IList<Charge> charges, Distributor distributor,
            Chain chain, IList<string> fbAdmins) : base(TYPE)
        {
            this.restaurant = restaurant;
            this.menu = menu;
            this.charges = charges;
            this.distributor = distributor;
            this.chain = chain;
            this.fbAdmins = fbAdmins;
        }
    }
}
