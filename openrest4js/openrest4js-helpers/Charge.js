Charge.prototype.isApplicable = function(orderItems, clubMember)
{
    if ((this.type) && (this.type == CHARGE_TYPE_CLUB_COUPON))
    {
        if (typeof(clubMember) == "undefined") return false;
        if (typeof(clubMember.clubIds) == "undefined") return false;
        if (typeof(this.clubId) == "undefined") return false;
        if (dojo.indexOf(clubMember.clubIds, this.clubId) == -1) return false;
        return true;
    }

    if ((this.type) && (this.type == CHARGE_TYPE_COUPON))
    {
        return true;
    }
    
    if ((this.type) && (this.type == CHARGE_TYPE_TAX))
    {
        return true;
    }

    // TODO: Others!!!
    return false;
}

Charge.prototype.calculateAmount = function(orderItems, maxDiscount, extraCost)
{
    if (typeof(maxDiscount) == "undefined") maxDiscount = Number.MAX_VALUE;

    if ((this.amountRuleType) && (this.amountRuleType == AMOUNT_RULE_TYPE_FIXED))
    {
        return Math.min(this.amountRule, maxDiscount);
    }
    else if ((this.amountRuleType) && (this.amountRuleType == AMOUNT_RULE_TYPE_PERCENTAGE))
    {
        return Math.min(this.calculateChargeValuePercentage(orderItems, extraCost), maxDiscount);
    }

    // TODO: Variables!!

    return 0;
}

Charge.prototype.calculateChargeValuePercentage = function(orderItems, extraCost)
{
    var percentage = parseInt(this.amountRule);
    var total = 0;
    if (typeof(orderItems) != "undefined")
    {
        for (var i in orderItems)
        {
            var item = orderItems[i];
            if (this.isApplicableItem(item.itemId))
            {
                total += item.getTotalPrice() * percentage / 10000;
            }
        }
    }

    if (extraCost)
    {
        total += extraCost * percentage / 10000;
    }

    return parseInt(total);
}

Charge.prototype.isApplicableItem = function(itemId)
{
    if (typeof(this.tagId) == "undefined") return true;
        
    var items = this.restaurantClient.getTagById(this.tagId).itemIds;

    if (dojo.indexOf(items, itemId) == -1)
    {
        return (this.tagMode == TAG_MODE_EXCLUDE);
    }
    return (this.tagMode == TAG_MODE_INCLUDE);
    

}

Charge.prototype.getTitle = function()
{
    if ((this.type) && (this.type == CHARGE_TYPE_CLUB_COUPON))
    {
        return this.coupon.title[spiceI18n.locale];
    }
    if ((this.type) && (this.type == CHARGE_TYPE_COUPON))
    {
        return this.coupon.title[spiceI18n.locale];
    }
    if ((this.type) && (this.type == CHARGE_TYPE_TAX))
    {
        return SPICE_STR("${OrderConfTax}");
    }

    return "";
}

Charge.prototype.getDescription = function()
{
    if ((this.type) && ((this.type == CHARGE_TYPE_CLUB_COUPON) || (this.type == CHARGE_TYPE_COUPON)))
    {
        var ret = "";
        
        if (this.coupon.description) ret = this.coupon.description[spiceI18n.locale];

        return ret;
    }

    return undefined;
}
