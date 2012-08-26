using System;

namespace com.openrest.v1_1
{
    /**
     * Information regarding a remote user of the system.
     * @author DL
     */
    public class User
    {
        public User(string id, string ipAddress, string fwdIpAddresses)
        {
            this.id = id;
            this.ipAddress = ipAddress;
            this.fwdIpAddresses = fwdIpAddresses;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public User() { }

        /** The user's Facebook id. */
        public string id;

        /** Whether the user's Facebook id is inferred (as opposed to verified). */
        public bool? idIsInferred = false;

        /**
         * The immediate client's IP address. If the real client is separated from the
         * server by a proxy server, this will return the IP address of the proxy.
         */
        public string ipAddress;

        /**
         * Corresponds to the "X-Forwarded-For" HTTP header.
         * This generally contains the user agent IP address (but can be spoofed).
         */
        public string fwdIpAddresses;
    }
}
