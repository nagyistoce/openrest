using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    public class Seo
    {
        public Seo(string title, IDictionary<string, string> meta, string html)
        {
            this.title = title;
            this.meta = meta;
            this.html = html;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public Seo() { }

        /** The HTML title. */
        public string title;
    
        /** HTML meta tags. */
        public IDictionary<string, string> meta = new Dictionary<string, string>();
    
        /** Marketing HTML block to embed in the web-site. */
        public string html;
    }
}
