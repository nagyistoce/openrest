using System;
using System.Collections.Generic;

namespace com.googlecode.openrest
{
    /**
     * A raw image.
     * @author DL
     */
    public class Image
    {
        public Image(string contentType, byte[] content)
        {
            this.contentType = contentType;
            this.content = content;
        }

        public string ContentType
        {
            get
            {
                return contentType;
            }
        }

        public byte[] Content
        {
            get
            {
                return content;
            }
        }

        private readonly string contentType;
        private readonly byte[] content;
    }
}
