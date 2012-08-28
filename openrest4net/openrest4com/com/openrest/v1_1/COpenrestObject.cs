using System;
using System.Runtime.InteropServices;
using com.openrest.v1_1;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class COpenrestObject : IOpenrestObject
    {
        private readonly OpenrestObject openrestObject;

        public COpenrestObject(OpenrestObject openrestObject)
        {
            this.openrestObject = openrestObject;
        }
    }
}
