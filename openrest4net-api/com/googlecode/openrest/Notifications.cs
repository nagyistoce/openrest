using System;
using System.Collections.Generic;

namespace com.googlecode.openrest
{
    class Notifications
    {
        public Notifications(IList<Notification> notifications)
        {
            this.notifications = notifications;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        internal Notifications() { }

        public IList<Notification> notifications = new List<Notification>();
    }
}
