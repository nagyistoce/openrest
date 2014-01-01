openrest = openrest || {};

openrest.ChargeHelper = openrest.ChargeHelper || (function() {

	var self = {};

    function indexOf(arr, val)
    {
        for (var i in arr) if (arr[i] === val) return i;
        return -1;
    }

    self.isApplicable = function(params)
    {
        var charge = params.charge;
        var clubIds = params.clubIds;
        var ref = params.ref;
        var timezone = params.timezone;
        var deliveryType = params.deliveryType || "";
        var dontCheckAvailability = params.dontCheckAvailability || false;
        var skipClub = params.skipClub || false;


        if ((charge.refs) && (indexOf(charge.refs, ref) === -1)) return false;

        if ((charge.deliveryTypes) && (indexOf(charge.deliveryTypes, deliveryType) === -1)) return false;
        if (charge.inactive) return false;

        if ((!dontCheckAvailability) && (charge.availability))
        {
            var now = new timezoneJS.Date();
            now.setTimezone(timezone);
            now.setTimestampToNow();

            var util = new availability.AvailabilityIterator({
            	cal : now,
            	availability : charge.availability
            });
            if (!util.hasNext())
            {
                console.log("AvailabilityIterator >> item availability hasNext returned false!");
                return false;
            }

            var status = util.next();
            if (status.status === OPENREST_STATUS_STATUS_UNAVAILABLE) return false;
        }

        if ((charge.type) && (charge.type == CHARGE_TYPE_CLUB_COUPON))
        {
            if (!skipClub)
            {
                if (typeof(clubIds) == "undefined") return false;
                if (typeof(charge.clubId) == "undefined") return false;
                if (indexOf(clubIds, charge.clubId) == -1) return false;
            }
            return true;
        }

        if ((charge.type) && (charge.type == CHARGE_TYPE_COUPON))
        {
            return true;
        }
    
        if ((charge.type) && (charge.type == CHARGE_TYPE_TAX))
        {
            return true;
        }

        // TODO: Others!!!
        return false;
    }

    self.calculateAmount = function(params)
    {
        var charge = params.charge;
        var orderItems = params.orderItems;
        var maxDiscount = params.maxDiscount || 0;
        var extraCost = params.extraCost;
        var type = charge.amountRuleType || AMOUNT_RULE_TYPE_VARIABLE;

        if (typeof(maxDiscount) == "undefined") maxDiscount = Number.MAX_VALUE;

        if (type == AMOUNT_RULE_TYPE_FIXED)
        {
            for (var i in orderItems)
            {
                var item = orderItems[i];
                if (self.isApplicableItem({charge:charge, itemId:item.itemId}))
                {
                    return Math.max(charge.amountRule, -1*maxDiscount);
                }
            }
        }
        else if (type == AMOUNT_RULE_TYPE_PERCENTAGE)
        {
            return Math.min(self.calculateChargeValuePercentage({charge:charge, orderItems:orderItems, extraCost:extraCost}), maxDiscount);
        }
        else if (type == AMOUNT_RULE_TYPE_FIXED_PER_ITEM)
        {
            try{
            var total = 0;
            for (var i in orderItems)
            {
                var item = orderItems[i];
                if (self.isApplicableItem({charge:charge, itemId:item.itemId}))
                {
                    var singlePrice = openrest.OrderItemHelper.getTotalPrice(item) / item.count;
                    var discount = Math.max(-1*singlePrice, charge.amountRule) * item.count;
                    total += discount;
                }
            }
            return total;
            } catch(e) {alert(e)};
        }
        else if (type == AMOUNT_RULE_TYPE_VARIABLE)
        {
            if ((charge.variableAmountRuleType) && (charge.variableAmountRuleType == AMOUNT_RULE_TYPE_FIXED))
            {
                return Math.max(charge.variableAmountRule, -1*maxDiscount);
            }
            else if ((charge.variableAmountRuleType) && (charge.variableAmountRuleType == AMOUNT_RULE_TYPE_PERCENTAGE))
            {
                return Math.max(self.calculateChargeValuePercentage({charge:charge, orderItems:orderItems, extraCost:extraCost, percentage:charge.variableAmountRule}), -1*maxDiscount);
            }
            else
            {
                return charge.amount || 0;
            }
        }

        return 0;
    }

    self.calculateChargeValuePercentage = function(params)
    {
        var charge = params.charge;
        var orderItems = params.orderItems;
        var extraCost = params.extraCost;
        var percentage = params.percentage || parseInt(charge.amountRule);

        var total = 0;
        if (typeof(orderItems) != "undefined")
        {
            for (var i in orderItems)
            {
                var item = orderItems[i];
                if (self.isApplicableItem({charge:charge, itemId:item.itemId}))
                {
                    total += openrest.OrderItemHelper.getTotalPrice(item) * percentage / 10000;
                }
            }
        }

        if (extraCost)
        {
            total += extraCost * percentage / 10000;
        }

        return parseInt(total);
    }

    self.isApplicableItem = function(params)
    {
        var charge = params.charge;
        var itemId = params.itemId;

        if ((charge.itemIds || null) === null) return true;

        var items = charge.itemIds;

        charge.mode = charge.mode || MODE_INCLUDE;

        if (indexOf(items, itemId) == -1)
        {
            return (charge.mode === MODE_EXCLUDE);
        }
        return (charge.mode === MODE_INCLUDE);


    }

    self.getTitle = function(params)
    {
        var charge = params.charge;
        var i18n = params.i18n;
        var defaultLocale = params.defaultLocale;

        if ((charge.type) && (charge.type == CHARGE_TYPE_CLUB_COUPON))
        {
            return charge.coupon.title[i18n.getLocale()] || charge.coupon.title[defaultLocale];
        }
        if ((charge.type) && (charge.type == CHARGE_TYPE_COUPON))
        {
            return charge.coupon.title[i18n.getLocale()] || charge.coupon.title[defaultLocale];
        }
        if ((charge.type) && (charge.type == CHARGE_TYPE_TAX))
        {
            return i18n.get("OrderConfTax");
        }

        return "";
    }

    self.getDescription = function(params)
    {
        var charge = params.charge;
        var i18n = params.i18n;
        var defaultLocale = params.defaultLocale;

        if ((charge.type) && ((charge.type == CHARGE_TYPE_CLUB_COUPON) || (charge.type == CHARGE_TYPE_COUPON)))
        {
            var ret = "";

            if (charge.coupon.description) ret = charge.coupon.description[i18n.getLocale()] || charge.coupon.description[defaultLocale];

            return ret;
        }

        return undefined;
    }

    return self;
}());

