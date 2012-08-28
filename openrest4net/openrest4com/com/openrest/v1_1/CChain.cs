using System;
using System.Runtime.InteropServices;
using com.openrest.v1_1;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CChain : COrganization, IChain
    {
        private readonly Chain chain;

        public CChain(Chain chain) : base(chain)
        {
            this.chain = chain;
        }

        public string GetDistributorId()
        {
            return chain.distributorId;
        }
    }
}
