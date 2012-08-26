using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    /**
     * The Openrest client.
     * @author DL
     */
    public class OpenrestClient
    {
        private readonly Uri apiUri;
        private readonly OpenrestProtocol protocol;

        public OpenrestClient(Uri apiUri)
        {
            this.apiUri = apiUri;
            protocol = new OpenrestProtocol();

            // @see http://stackoverflow.com/questions/566437/http-post-returns-the-error-417-expectation-failed-c
            System.Net.ServicePointManager.Expect100Continue = false;
        }

        public T Request<T>(Request request)
        {
            return protocol.Post<T>(apiUri, request);
        }
    }
}
