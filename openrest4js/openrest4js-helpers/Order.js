openrest = openrest || {};

openrest.OrderHelper = openrest.OrderHelper || (function() {
	var self = {};

    self.calculateTotalOrderWithoutCoupons = function(params)
    {
        var order = params.order;
        if (!order) return 0;

        var orderItems = params.order.orderItems || [];

        var total = 0;
        for (var i = 0 ; i < orderItems.length; i++) 
        {
            var orderItem = order.orderItems[i];
            total += orderItem.gTotalPrice();
        }

        return total;
    }

    self.getAllApplicableCharges = function(params)
    {
        var order = params.order;
        var charges = params.charges;
        var clubMember = params.clubMember;
        var tagMap = params.tagMap;

        var res = [];

        for (var i in charges)
        {
            var charge = charges[i];
            if (openrest.ChargeHelper.isApplicable({charge:charge, orderItems:order.orderItems, 
                clubMember:clubMember, tagMap:tagMap}))
            {
                res.push(charges[i]);
            }
        }

        return res;
    }

    self.calculateTotalOrder = function(params)
    {
        var order = params.order;
        var charges = params.charges;
        var withoutTax = params.withoutTax;
        var tagMap = params.tagMap;

        var total = self.calculateTotalOrderWithoutCoupons(params);
        Ti.API.timestamp("OrderHelper.calculatTotalOrder >> W/o Coupons = "+total);
        var price = 0;

        var charges = self.getAllApplicableCharges(params);
        for (var i in charges)
        {
            var charge = charges[i];
            if ((charge.type == CHARGE_TYPE_COUPON) || (charge.type == CHARGE_TYPE_CLUB_COUPON))
            {
                price += openrest.ChargeHelper.calculateAmount({charge:charge, 
                    orderItems:order.orderItems, maxDiscount:total, extraCost:0,
                      tagMap:tagMap});
            }
            if ((!withoutTax) && (charge.type == CHARGE_TYPE_TAX))
            {
                price += openrest.ChargeHelper.calculateAmount({charge:charge, 
                    orderItems:order.orderItems, maxDiscount:total, extraCost:0,
                      tagMap:tagMap});
            }
        }

        Ti.API.timestamp("OrderHelper.calculatTotalOrder >> after charges = "+(total+price));
        return total + price;
    }

	return self;
}());

