using System;
using Newtonsoft.Json;

namespace com.openrest.v1_1
{
    class OpenrestProtocol
    {
        private readonly RestJsonClient restJsonClient;

        public OpenrestProtocol()
        {
            JsonSerializerSettings settings = new JsonSerializerSettings();
            settings.Converters.Add(new OpenrestObjectJsonConvertor());
            settings.Converters.Add(new RequestJsonConvertor());

            restJsonClient = new RestJsonClient(settings);
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
