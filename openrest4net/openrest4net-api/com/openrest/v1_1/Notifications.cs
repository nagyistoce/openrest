using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class Notifications
    {
        public Notifications(IList<Notification> notifications)
        {
            this.notifications = notifications;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Notifications() { }

        public IList<Notification> notifications = new List<Notification>();
    }
}
