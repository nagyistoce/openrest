using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class Organization : OpenrestObject
    {
        protected Organization(string type) : base(type) { }

        /** The organization's unique id. */
        public string id;

        /**
         * Map of externally-defined ids referring to this organization.
         * For example, the organization-id in some external billing system.
         * 
         * Developers should use unique keys, e.g. "com.company.product".
         */
        public IDictionary<string, string> externalIds = new Dictionary<string, string>();

        /** The organization's creation timestamp. */
        public long? created;
    
        /** The organization's last modification timestamp. */
        public long? modified;

        /** The organization's title in various locales. */
        public IDictionary<string, string> title = new Dictionary<string, string>();

        /** The organization's description or tagline in various locales. */
        public IDictionary<string, string> description = new Dictionary<string, string>();

        /** The color scheme. */
        public ColorScheme colorScheme;

        /** The organization's contact. */
        public Contact contact;

        /**
         * Map of externally-defined contact information for this organization.
         * For example, a redirect phone number in some portal.
         * 
         * Developers should use unique keys, e.g. "com.company.product".
         */
        public IDictionary<string, Contact> externalContacts = new Dictionary<string, Contact>();

        /** The address of this organization. */
        public Address address;

        /**
         * The restaurant's timezone.
         * @see http://en.wikipedia.org/wiki/List_of_tz_database_time_zones
         */
        public string timezone;

        /** The organization's default locale, e.g. "en_US". */
        public string locale;

        /** The organization's supported locales. */
        public IList<string> locales = new List<string>();
    
        /** The organization's main web-site URL. */
        public string link;

        /** The organization's online ordering domain. */
        public string domain;

        /**
         * The organization's online ordering alternative / legacy domains.
         * These should redirect to the main domain.
         */
        public IList<string> altDomains = new List<string>(); 


        /** The organization's picture URL (direct link), or null if unavailable. */
        public string picture;
    
        /** The organization's icon URL (direct link), or null if unavailable. */
        public string icon;
    
        /** The picture shown when a picture is missing. */
        public string noImagePicture;

        /** The organization's applications. */
        public IList<AppInfo> apps = new List<AppInfo>();

        /** SEO information. */
        public Seo seo;
    
        /**
         * Map of user-defined extended properties. Developers should use unique
         * keys, e.g. "com.googlecode.openrestext".
         */
        public IDictionary<string, string> properties = new Dictionary<string, string>();
    }
}
