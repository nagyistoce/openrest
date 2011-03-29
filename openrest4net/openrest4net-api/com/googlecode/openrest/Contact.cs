using System;
using System.Collections.Generic;

namespace com.googlecode.openrest
{
    public class Contact
    {
        public Contact(string firstName, string lastName, string email, string phone)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Contact() { }

        public string firstName;
        public string lastName;
        public string email;
        public string phone;
    }
}
