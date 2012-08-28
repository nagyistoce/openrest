using System;
using System.Runtime.InteropServices;
using com.openrest.v1_1;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CDistributor : COrganization, IDistributor
    {
        private readonly Distributor distributor;

        public CDistributor(Distributor distributor)
            : base(distributor)
        {
            this.distributor = distributor;
        }
    }
}
