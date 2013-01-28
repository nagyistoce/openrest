COUPON_TYPE_DISCOUNT = "discount";
COUPON_TYPE_M_PLUS_N = "m_plus_n";
ALL_COUPON_TYPES = [
    COUPON_TYPE_DISCOUNT, COUPON_TYPE_M_PLUS_N
];

function CouponFromObj(data)
{
    return new Coupon(data.type, data.title, data.description, data.maxNumAllowed, data.othersAllowed);
}

function Coupon(type, title, description, maxNumAllowed, othersAllowed)
{
    this.type = type;
    this.title = title;
    this.description = description;
    this.maxNumAllowed = maxNumAllowed;
    if (typeof(this.maxNumAllowed) == "undefined") this.maxNumAllowed = 2147483647;
    this.othersAllowed = othersAllowed;
    if (typeof(this.othersAllowed) == "undefined") this.othersAllowed = true;
}

Coupon.prototype.toOpenRestObj = function()
{
    var ret = JSON.parse(JSON.stringify(this));

    if (!('description' in ret)) delete ret.description;
    if (typeof(ret.maxNumAllowed) == "undefined") ret.maxNumAllowed = 2147483647;
    if (typeof(ret.othersAllowed) == "undefined") ret.othersAllowed = true;

    return ret;
}
