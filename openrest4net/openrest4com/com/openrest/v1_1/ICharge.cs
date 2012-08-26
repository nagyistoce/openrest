using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    [InterfaceType(ComInterfaceType.InterfaceIsDual)]
    public interface ICharge
    {
        string GetId();
        string GetRestaurantId();
        string GetChargeType();
        double GetPriority();
        string GetCode();
        string GetTagId();
        string GetTagMode();
        string GetAmountRuleType();
        int GetAmountRule();
        ICoupon GetCoupon();
        INullableInt GetAmount();
    }
}
