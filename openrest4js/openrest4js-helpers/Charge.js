openrest = openrest || {};

openrest.ChargeHelper = openrest.ChargeHelper || (function() {

	var self = {};

    self.isApplicable = function(params)
    {
        var charge = params.charge;
        var orderItems = params.orderItems;
        var clubMember = params.clubMember;

        if ((charge.type) && (charge.type == CHARGE_TYPE_CLUB_COUPON))
        {
            if (typeof(clubMember) == "undefined") return false;
            if (typeof(clubMember.clubIds) == "undefined") return false;
            if (typeof(charge.clubId) == "undefined") return false;
            if (dojo.indexOf(clubMember.clubIds, charge.clubId) == -1) return false;
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
        var tagMap = params.tagMap;

        if (typeof(maxDiscount) == "undefined") maxDiscount = Number.MAX_VALUE;

        if ((charge.amountRuleType) && (charge.amountRuleType == AMOUNT_RULE_TYPE_FIXED))
        {
            return Math.min(charge.amountRule, maxDiscount);
        }
        else if ((charge.amountRuleType) && (charge.amountRuleType == AMOUNT_RULE_TYPE_PERCENTAGE))
        {
            return Math.min(self.calculateChargeValuePercentage({charge:charge, orderItems:orderItems, extraCost:extraCost, tagMap:tagMap}), maxDiscount);
        }

        // TODO: Variables!!

        return 0;
    }

    self.calculateChargeValuePercentage = function(params)
    {
        var charge = params.charge;
        var orderItems = params.orderItems;
        var extraCost = params.extraCost;
        var tagMap = params.tagMap;

        var percentage = parseInt(charge.amountRule);
        var total = 0;
        if (typeof(orderItems) != "undefined")
        {
            for (var i in orderItems)
            {
                var item = orderItems[i];
                if (self.isApplicableItem({charge:charge, itemId:item.itemId, tagMap:tagMap}))
                {
                    total += item.gTotalPrice() * percentage / 10000;
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
        var tagMap = params.tagMap;

        if (typeof(charge.tagId) == "undefined") return true;

        var items = tagMap[charge.tagId].itemIds;

        charge.tagMode = charge.tagMode || TAG_MODE_INCLUDE;

        if (items.indexOf(itemId) == -1)
        {
            return (charge.tagMode === TAG_MODE_EXCLUDE);
        }
        return (charge.tagMode === TAG_MODE_INCLUDE);


    }

    self.getTitle = function(params)
    {
        var charge = params.charge;
        var i18n = params.i18n;

        if ((charge.type) && (charge.type == CHARGE_TYPE_CLUB_COUPON))
        {
            return charge.coupon.title[i18n.getLocale()];
        }
        if ((charge.type) && (charge.type == CHARGE_TYPE_COUPON))
        {
            return charge.coupon.title[i18n.getLocale()];
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

        if ((charge.type) && ((charge.type == CHARGE_TYPE_CLUB_COUPON) || (charge.type == CHARGE_TYPE_COUPON)))
        {
            var ret = "";

            if (charge.coupon.description) ret = charge.coupon.description[i18n.getLocale()];

            return ret;
        }

        return undefined;
    }

    return self;
}());

