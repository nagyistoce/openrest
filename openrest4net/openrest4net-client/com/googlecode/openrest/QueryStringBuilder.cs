using System;
using System.Text;

namespace com.googlecode.openrest
{
    class QueryStringBuilder
    {
        public QueryStringBuilder() { }

        public void Append(String name, String value)
        {
            builder.Append(first ? '?' : '&')
                .Append(name)
                .Append('=')
                .Append(value);
            first = false;
        }

        public override string ToString()
        {
            return builder.ToString();
        }

        private bool first = true;
        private readonly StringBuilder builder = new StringBuilder();
    }
}
