using System;
using System.Runtime.InteropServices;
using com.openrest.v1_1;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class COpenrestClient : IOpenrestClient
    {
        private OpenrestClient client;

        public COpenrestClient() { }

        public void Init(string apiUri)
        {
            client = new OpenrestClient(new Uri(apiUri));
        }

        public IRestaurantFullInfo GetRestaurantFullInfo(string restaurantId)
        {
            GetOrganizationFullRequest request = new GetOrganizationFullRequest(restaurantId);
            RestaurantFullInfo restaurantFullInfo = client.Request<RestaurantFullInfo>(request);
            return new CRestaurantFullInfo(restaurantFullInfo);
        }
    }
}
