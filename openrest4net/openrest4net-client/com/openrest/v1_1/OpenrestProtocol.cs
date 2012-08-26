using System;

namespace com.openrest.v1_1
{
    class OpenrestProtocol
    {
        private readonly RestJsonClient restJsonClient;

        public OpenrestProtocol()
        {
            restJsonClient = new RestJsonClient();
        }

        public T Post<T>(Uri uri, Object obj)
        {
            try {
                Response<T> response = restJsonClient.Post<Response<T>>(uri, obj);
        	    VerifyResponse(response);
        	    return response.value;
            } catch (RestJsonHttpException e) {
                throw e;
            }
        }

        public RestJsonClient RestJsonClient
        {
            get
            {
                return restJsonClient;
            }
        }

        private static void VerifyResponse<T>(Response<T> response) {
            if (response.error != null) {
    	        throw new OpenrestException(response.error, response.errorMessage);
            }
        }
    }
}
