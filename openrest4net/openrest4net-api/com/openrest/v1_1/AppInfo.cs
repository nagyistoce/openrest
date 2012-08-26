using System;
using Newtonsoft.Json;

namespace com.openrest.v1_1
{
    public class AppInfo
    {
        /** Android platform. */
        public const string PLATFORM_ANDROID = "android";
	    /** iOS platform. */
        public const string PLATFORM_IOS = "ios";

        /** Client application. */
        public const string APP_TYPE_CLIENT = "client";
	    /** Employee application. */
        public const string APP_TYPE_EMPLOYEE = "employee";

        public AppInfo(string type, string platform, string id, string version, string link)
        {
            this.type = type;
            this.platform = platform;
            this.id = id;
            this.version = version;
            this.link = link;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public AppInfo() { }

        public string type;
        public string platform;
        public string id;
        public string version;
        public string link;
    }
}
