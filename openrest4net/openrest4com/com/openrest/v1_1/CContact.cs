using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CContact : IContact
    {
        public CContact(Contact contact)
        {
            this.contact = contact;
        }

        public string GetFirstName()
        {
            return contact.firstName;
        }

        public string GetLastName()
        {
            return contact.lastName;
        }

        public string GetEmail()
        {
            return contact.email;
        }

        public string GetPhone()
        {
            return contact.phone;
        }

        private readonly Contact contact;
    }
}
