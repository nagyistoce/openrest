using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CDelivery : IDelivery
    {
        public CDelivery(Delivery delivery)
        {
            this.delivery = delivery;
        }

        public string GetDeliveryType()
        {
            return delivery.type;
        }

        public IAddress GetAddress()
        {
            return new CAddress(delivery.address);
        }

        public int GetCharge()
        {
            return delivery.charge.Value;
        }

        private readonly Delivery delivery;
    }
}
