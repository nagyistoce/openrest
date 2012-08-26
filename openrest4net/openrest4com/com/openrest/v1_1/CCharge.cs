using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class CCharge : ICharge
    {
        public CCharge(Charge charge)
        {
            this.charge = charge;
        }

        public string GetId()
        {
            return charge.id;
        }

        public string GetRestaurantId()
        {
            return charge.restaurantId;
        }

        public string GetChargeType()
        {
            return charge.type;
        }

        public double GetPriority()
        {
            return charge.priority.Value;
        }

        public string GetCode()
        {
            return charge.code;
        }

        public string GetTagId()
        {
            return charge.tagId;
        }

        public string GetTagMode()
        {
            return charge.tagMode;
        }

        public string GetAmountRuleType()
        {
            return charge.amountRuleType;
        }

        public int GetAmountRule()
        {
            return charge.amountRule.Value;
        }

        public ICoupon GetCoupon()
        {
            return ((charge.coupon != null) ? new CCoupon(charge.coupon) : null);
        }

        public INullableInt GetAmount()
        {
            return new CNullableInt(charge.amount);
        }

        private readonly Charge charge;
    }
}
