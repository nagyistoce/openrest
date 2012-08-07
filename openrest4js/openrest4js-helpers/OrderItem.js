OrderItem.prototype.gTotalPrice = function()
{
    return openrest.OrderItemHelper.getTotalPrice(this);
};

var openrest = openrest || {};

openrest.OrderItemHelper = openrest.OrderItemHelper || (function() {
	var self = {};

    self.getTotalPrice = function(item)
    {
        var price = item.price;
        if (!price) price = 0;

        for (var i in item.variationsChoices) {
            for (var j in item.variationsChoices[i]) {
                price += self.getTotalPrice(item.variationsChoices[i][j]);
            }
        }

        var count = item.count;
        if (typeof(count) === "undefined") {
            count = 1;
        }
        price = price * count;
        return price;
    }

    self.getOrderItemFromItem = function(item, __itemById)
    {
        var orderItem = {};
        orderItem.itemId = item.id;
        orderItem.price = item.price;
        orderItem.variations = item.variations;
        orderItem.variationsChoices = [];

        for (var i in item.variations)
        {
            var choices = [];
            var prices = item.variations.prices || {};
            for (var j in item.variations[i].defaults)
            {
                var subItemId = item.variations[i].defaults[j];
                var subOrderItem = self.getOrderItemFromItem(__itemById(subItemId), __itemById);
                subOrderItem.price = prices[subItemId] || 0;

                choices.push(subOrderItem);
            }
            orderItem.variationsChoices.push(choices);
        }
        
        return orderItem;
    }

    function variationsEquals(x, y)
    {
        if (x.toOpenRestObj) x = x.toOpenRestObj();
        if (y.toOpenRestObj) y = y.toOpenRestObj();

        var p;
        for(p in y) {
            if (p === "displayType") continue;
            if(typeof(x[p])=='undefined') {return false;}
        }

        for(p in y) {
            if (y[p]) {
                switch(typeof(y[p])) {
                    case 'object':
                        if (!variationsEquals(x[p], y[p])) { return false; } break;
                    case 'function':
                        if (typeof(x[p])=='undefined' ||
                                (p != 'equals' && y[p].toString() != x[p].toString()))
                            {
                                return false;
                            }
                        break;
                    default:
                        if (y[p] != x[p]) { return false; }
                }
            } else {
                if (x[p])
                {
                    return false;
                }
            }
        }

        for(p in x) {
            if (p === "displayType") continue;
            if(typeof(y[p])=='undefined') {return false;}
        }

        return true;
    }

    self.validate = function(orderItem, __tagById, __itemStatus, __itemById)
    {
        if (__itemStatus(orderItem.itemId) === "unavailable") {
            return false;
        }

        var variations = orderItem.variations || [];

        if (__itemById)
        {
            var item = __itemById(orderItem.itemId);
            if (!variationsEquals(item.variations || [], variations))
            {
                return false;
            }
        }

        var variationsChoices = orderItem.variationsChoices || [];
        var allOk = true;

        for (var j = 0 ; j < variations.length ; j++)
        {       
            allOk &= validateVariation(variations[j], variationsChoices[j], __tagById, __itemStatus);
        }

        return allOk;
    }

    function validateVariation(variation, variationChoices, __tagById, __itemStatus, __itemById)
    {
        var count = 0;
        var valid = true;
        var items = variationChoices || [];
        var legalItems = __tagById(variation.tagId).itemIds;
        var min = (variation.minNumAllowed ? variation.minNumAllowed : 0);
        var max = (variation.maxNumAllowed ? variation.maxNumAllowed : items.length);

        for (var i = 0 ; i < items.length ; i++) {
            valid &= inArray(legalItems, items[i].itemId);
            valid &= self.validate(items[i], __tagById, __itemStatus, __itemById);
            count++;
        }

        if ((count > max) && (max > 0)) {
            valid = false;
        }
        if (count < min) {
            valid = false;
        }

        return valid;        
    }

    function inArray(array, val)
    {
        for (var i in array) { if (array[i] === val) return true;}
        return false;
    }

	return self;
}());

