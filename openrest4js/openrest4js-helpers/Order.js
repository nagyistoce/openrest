openrest = openrest || {};

openrest.OrderHelper = openrest.OrderHelper || (function() {
	var self = {};

    self.calculateTotalOrderWithoutCoupons = function(params)
    {
        var order = params.order;

        var total = 0;
        for (var i = 0 ; i < order.orderItems.length; i++) 
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

        var res = [];

        for (var i in charges)
        {
            if (ChargeFromObj(charges[i]).isApplicable(order.orderItems, clubMember))
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

        var total = self.calculateTotalOrderWithoutCoupons(params);
        Ti.API.timestamp("OrderHelper.calculatTotalOrder >> W/o Coupons = "+total);
        var price = 0;

        var charges = self.getAllApplicableCharges(params);
        for (var i in charges)
        {
            var charge = ChargeFromObj(charges[i]);
            if ((charge.type == CHARGE_TYPE_COUPON) || (charge.type == CHARGE_TYPE_CLUB_COUPON))
            {
                price += charge.calculateAmount(orderItems, total);
            }
            if ((!withoutTax) && (charge.type == CHARGE_TYPE_TAX))
            {
                price += charge.calculateAmount(orderItems, total);
            }
        }

        Ti.API.timestamp("OrderHelper.calculatTotalOrder >> after charges = "+(total+price));
        return total + price;
    }

	return self;
}());

