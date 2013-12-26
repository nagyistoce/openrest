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
            total += openrest.OrderItemHelper.getTotalPrice(orderItem);
        }

        return total;
    }

    self.getAllApplicableCharges = function(params)
    {
        var order = params.order;
        var charges = params.charges;
        var clubIds = params.clubIds;
        var ref = params.ref;
        var timezone = params.timezone;

        var res = [];

        for (var i in charges)
        {
            var charge = charges[i];
            if (openrest.ChargeHelper.isApplicable({charge:charge, orderItems:order.orderItems, 
                clubIds:clubIds, ref:ref, timezone:timezone, deliveryType:order.delivery.type}))
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
        var ref = params.ref;
        var timezone = params.timezone;

        var total = self.calculateTotalOrderWithoutCoupons(params);
        var price = 0;

        var charges = self.getAllApplicableCharges(params);
        for (var i in charges)
        {
            var charge = charges[i];
            if ((charge.type == CHARGE_TYPE_COUPON) || (charge.type == CHARGE_TYPE_CLUB_COUPON))
            {
                price += openrest.ChargeHelper.calculateAmount({charge:charge, 
                    orderItems:order.orderItems, maxDiscount:total, extraCost:0});
            }
            if ((!withoutTax) && (charge.type == CHARGE_TYPE_TAX))
            {
                price += openrest.ChargeHelper.calculateAmount({charge:charge, 
                    orderItems:order.orderItems, maxDiscount:total, extraCost:0});
            }
        }

        return total + price;
    }

    function transformOrderItemToRestaurant(params)
    {
        var orderItem = params.orderItem;
        var itemMapping = params.itemMapping;
        var destItemsMapping = params.destItemsMapping;

        orderItem.itemId = itemMapping[orderItem.itemId];

        var item = destItemsMapping[orderItem.itemId];

        for (var i in orderItem.variations)
        {
            orderItem.variations[i] = JSON.parse(JSON.stringify(item.variations[i]));
        }

        for (var i in orderItem.variationsChoices)
        {
            for (var j in orderItem.variationsChoices[i])
            {
                orderItem.variationsChoices[i][j] = transformOrderItemToRestaurant({
                    orderItem:orderItem.variationsChoices[i][j],
                    itemMapping:itemMapping,
                    destItemsMapping:destItemsMapping
                });
            }
        }

        return orderItem;
    }

    function transformChargesToRestaurant(params)
    {
        var order = params.order;
        var destRestaurantFull = params.destRestaurantFull;

        var oldCharges = JSON.parse(JSON.stringify(order.charges));
        order.charges = getCharges({order:order, full:destRestaurantFull});

        for (var i = 0, l = order.charges.length ; i < l ; i++)
        {
            var destCharge = order.charges[i];
            var type = destCharge.amountRuleType || AMOUNT_RULE_TYPE_VARIABLE;
            if (type === AMOUNT_RULE_TYPE_VARIABLE)
            {
                for (var j = 0, l2 = oldCharges.length ; j < l2 ; j++)
                {
                    var srcCharge = oldCharges[j];
                    var srcType = srcCharge.amountRuleType || AMOUNT_RULE_TYPE_VARIABLE;
                    if ((srcType === type) && (srcCharge.clubId === destCharge.clubId) &&
                            (srcCharge.coupon.title[spiceI18n.locale] === destCharge.coupon.title[spiceI18n.locale]))
                    {
                        destCharge.amount = srcCharge.amount;
                    }
                }
            }
        }

        return order;
    }

    function getCharges(params)
    {
        var order = params.order;
        var full = params.full;

        var ret = [];
        var total = self.calculateTotalOrderWithoutCoupons({order:order});
        var charges = self.getAllApplicableCharges({order:order, charges:full.charges, clubIds:(order.clubMember || {}).clubIds,
                ref:order.ref, timezone:full.restaurant.timezone});

        for (var i in charges)
        {
            var charge = charges[i];
            charge.amount = openrest.ChargeHelper.calculateAmount({charge:charge, orderItems:order.orderItems,
                        maxDiscount:total, extraCost:0});

            ret.push(charge);
        }

        return ret;
    }

    self.transformOrderToRestaurant = function(params)
    {
        var order = params.order;
        var srcRestaurantFull = params.srcRestaurantFull;
        var destRestaurantFull = params.destRestaurantFull;
        var chainId = srcRestaurantFull.chain.id;

        order.restaurantId = destRestaurantFull.restaurant.id;

        var destItemsMapping = {};
        for (var i in destRestaurantFull.menu.items)
        {
            var item = destRestaurantFull.menu.items[i];
            destItemsMapping[item.id] = item;
        }

        var destItemMappingToExternalId = {};
        for (var i in destRestaurantFull.menu.items)
        {
            var item = destRestaurantFull.menu.items[i];
            var id = (item.externalIds || {})[chainId] || null;

            if (id !== null)
            {
                destItemMappingToExternalId[id] = item.id;
            }
            else if (!item.inactive)
            {
                console.log("No mapping for "+item.title["he_IL"]+" "+id+" "+"("+destRestaurantFull.restaurant.title["he_IL"]+")");
            }
        }

        var itemMapping = {};
        for (var i in srcRestaurantFull.menu.items)
        {
            var item = srcRestaurantFull.menu.items[i];
            var id = (item.externalIds || {})[chainId] || null;

            if ((id !== null) && (destItemMappingToExternalId[id]) && (destItemsMapping[destItemMappingToExternalId[id]]))
            {
                itemMapping[item.id] = destItemMappingToExternalId[id];
                if (item.title["he_IL"] !== destItemsMapping[destItemMappingToExternalId[id]].title["he_IL"]) {
                    console.log("'"+id+"' "+item.title["he_IL"] + "("+srcRestaurantFull.restaurant.title["he_IL"]+") - " + destItemsMapping[destItemMappingToExternalId[id]].title["he_IL"]+"("+destRestaurantFull.restaurant.title["he_IL"]+")");
                }
            }
            else if ((id === null) && (!item.inactive))
            {
                console.log("No mapping for "+item.title["he_IL"]+" "+id+" ("+srcRestaurantFull.restaurant.title["he_IL"]+")");
            }
            else if (!item.inactive)
            {
                console.log("No connection for "+item.title["he_IL"]+" "+id+" ("+srcRestaurantFull.restaurant.title["he_IL"]+")", destItemMappingToExternalId[id]);
            }
        }

        for (var i in order.orderItems)
        {
            order.orderItems[i] = transformOrderItemToRestaurant({
                orderItem:order.orderItems[i],
                itemMapping:itemMapping,
                destItemsMapping:destItemsMapping
            });
        }

        order = transformChargesToRestaurant({order:order, destRestaurantFull:destRestaurantFull});
    }

	return self;
}());

