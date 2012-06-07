function OrderFromObj(data)
{
    return new Order(data.orderId, data.restaurantId, data.orderItems, data.comment, data.price, 
            data.delivery, data.contact, data.payments, data.created, data.modified, data.user, 
            data.status, data.shareToken, data.takeoutPacks, data.charges, data.clubMember, data.log, 
            data.locale);
}

function Order(orderId, restaurantId, orderItems, comment,
        price, delivery, contact, payments, created,
        modified, user, status, shareToken, takeoutPacks,
        charges, clubMember, log, locale)
{
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.orderItems = orderItems || [];
        this.comment = comment;
        this.price = price;
        this.delivery = delivery;
        this.contact = contact;
        this.payments = payments || [];
        this.created = created;
        this.modified = modified;
        this.user = user;
        this.status = status;
        this.shareToken = shareToken;
        this.takeoutPacks = takeoutPacks;
        this.charges = charges;
        this.clubMember = clubMember;
        this.log = log;
        this.locale = locale;
};

Order.prototype.toOpenRestObj = function()
{
    var ret = {};

        if (this.orderId) ret['orderId'] = this.orderId;
        if (this.restaurantId) ret['restaurantId'] = this.restaurantId;
        if (this.orderItems) ret['orderItems'] = this.orderItems;
        if (this.comment) ret['comment'] = this.comment;
        if (this.price) ret['price'] = this.price;
        if (this.delivery) ret['delivery'] = this.delivery;
        if (this.contact) ret['contact'] = this.contact;
        if (this.payments) ret['payments'] = this.payments;
        if (this.created) ret['created'] = this.created;
        if (this.modified) ret['modified'] = this.modified;
        if (this.user) ret['user'] = this.user;
        if (this.status) ret['status'] = this.status;
        if (this.shareToken) ret['shareToken'] = this.shareToken;
        if (this.takeoutPacks) ret['takeoutPacks'] = this.takeoutPacks;
        if (this.charges) ret['charges'] = this.charges;
        if (this.clubMember) ret['clubMember'] = this.clubMember;
        if (this.log) ret['log'] = this.log;
        if (this.locale) ret['locale'] = this.locale;
        if (this.ref) ret['ref'] = this.ref;

    return ret;
}
