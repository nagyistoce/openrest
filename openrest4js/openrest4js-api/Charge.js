CHARGE_TYPE_DELIVERY = "delivery";
CHARGE_TYPE_COUPON = "coupon";
CHARGE_TYPE_TAX = "tax";
CHARGE_TYPE_CLUB_COUPON = "club_coupon";
ALL_CHARGE_TYPES = [
    CHARGE_TYPE_DELIVERY, CHARGE_TYPE_COUPON, CHARGE_TYPE_CLUB_COUPON
];
    
AMOUNT_RULE_TYPE_FIXED = "fixed";
AMOUNT_RULE_TYPE_PERCENTAGE = "percentage";
AMOUNT_RULE_TYPE_VARIABLE = "variable";
AMOUNT_RULE_TYPE_FIXED_PER_ITEM = "fixed_per_item";
ALL_AMOUNT_RULE_TYPES = [
    AMOUNT_RULE_TYPE_FIXED, AMOUNT_RULE_TYPE_PERCENTAGE, AMOUNT_RULE_TYPE_VARIABLE, AMOUNT_RULE_TYPE_FIXED_PER_ITEM
];

MODE_INCLUDE = "include";
MODE_EXCLUDE = "exclude";


function ChargeFromObj(data)
{
    var coupon = undefined;
    if (data.coupon)
    {
        coupon = CouponFromObj(data.coupon);
    }
    return new Charge(data.id, data.restaurantId, data.type, data.priority,
        data.code, data.clubId, data.itemIds, data.mode, data.amountRuleType, data.amountRule,
        data.amount, coupon);
}

function Charge(id, restaurantId, type, priority, code, clubId,
        itemIds, mode, amountRuleType, amountRule, amount, coupon)
{
    this.id = id;
    this.restaurantId = restaurantId;
    this.type = type;
    this.priority = priority;
    if (!this.priority) this.priority = 0;
    this.code = code;
    this.clubId = clubId;
    this.itemIds = itemIds;
    this.mode = mode;
    if (!this.mode) this.mode = MODE_INCLUDE;
    this.amountRuleType = amountRuleType;
    if (!this.amountRuleType) this.amountRuleType = AMOUNT_RULE_TYPE_VARIABLE;
    this.amountRule = amountRule;
    this.amount = amount;
    this.coupon = coupon;
}

Charge.prototype.toOpenRestObj = function()
{
    var ret = JSON.parse(JSON.stringify(this));

    if (!this.coupon) 
    {
        delete ret.coupon;
    }
    else
    {
        ret.coupon = this.coupon.toOpenRestObj();
    }
    if (!ret.itemIds) delete ret.itemIds;
    if (!ret.mode) delete ret.mode;
    if (typeof(ret.amount) == "undefined") delete ret.amount;
    if (typeof(ret.code) == "undefined") delete ret.code;
    if (typeof(ret.id) == "undefined") delete ret.id;
    if (typeof(ret.clubId) == "undefined") delete ret.clubId;

    return ret;
}
