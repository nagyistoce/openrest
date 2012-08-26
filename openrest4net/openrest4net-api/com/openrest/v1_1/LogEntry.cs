using System;

namespace com.openrest.v1_1
{
    /**
     * An entry in a change-log.
     * @author DL
     */
    public class LogEntry
    {
        public LogEntry(long timestamp, User user, string comment)
        {
            this.timestamp = timestamp;
            this.user = user;
            this.comment = comment;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public LogEntry() { }

        /** The log entry's timestamp. */
        public long? timestamp;

        /** The user making the changes. */
        public User user;

        /** Optional comment documenting the change. */
        public string comment;
    }
}
