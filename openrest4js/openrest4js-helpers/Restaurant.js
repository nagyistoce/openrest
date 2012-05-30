openrest = openrest || {};

openrest.RestaurantHelper = openrest.RestaurantHelper || (function() {
	var self = {};

    self.getStatus = function(restaurant)
    {
        if (restaurant.inactive)
        {
            return {'status': OPENREST_STATUS_STATUS_UNAVAILABLE, until:Number.MAX_VALUE}; 
        }

        var now = new timezoneJS.Date();
        now.setTimezone(restaurant.timezone);
        now.setTimestampToNow();

        var util = new TimeWindowsIterator(now, restaurant.openTimes);
        if (!util.hasNext())
        {
            return {'status':OPENREST_STATUS_STATUS_AVAILABLE, until:Number.MAX_VALUE};
        }

        var availability = util.next();
        if (typeof(availability.until) == "undefined") availability.until = Number.MAX_VALUE;
        return availability;
    }

    self.getDeliveryStatus = function(restaurant)
    {
        if (restaurant.inactive)
        {
            return {'status': OPENREST_STATUS_STATUS_UNAVAILABLE, until:Number.MAX_VALUE}; 
        }

        var now = new timezoneJS.Date();
        now.setTimezone(restaurant.timezone);
        now.setTimestampToNow();

        var util = new TimeWindowsIterator(now, restaurant.deliveryTimes);
        if (!util.hasNext())
        {
            return {'status':OPENREST_STATUS_STATUS_AVAILABLE, until:Number.MAX_VALUE};
        }

        var availability = util.next();
        if (typeof(availability.until) == "undefined") availability.until = Number.MAX_VALUE;
        return availability;
    }

    self.isTakeoutInactive = function(restaurant)
    {
        for (var i in restaurant.deliveryInfos)
        {
            if (restaurant.deliveryInfos[i].type == "takeout") 
                return restaurant.deliveryInfos[i].inactive;
        }
        return true;
    }

    self.getDeliveryInfoWithMinCharge = function(data)
    {
        var value = undefined;

        for (var i = 0 ; i < data.deliveryInfos.length ; i++)
        {
            var deliveryInfo = data.deliveryInfos[i];
            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;

            if ((typeof(value) == "undefined") || (deliveryInfo.charge < value.charge)) {
                value = deliveryInfo;
            }
        }   

        if (typeof(value) == "undefined") return undefined;
        return value;
    }

    self.getMinDeliveryChargePossible = function(data) 
    {
        var info = self.getDeliveryInfoWithMinCharge(data);
        if (typeof(info) == "undefined") return 0;
        return info.charge;
    }

    self.getMaxDeliveryChargePossible = function(data)
    {
        var value = undefined;

        for (var i = 0 ; i < data.deliveryInfos.length ; i++)
        {
            var deliveryInfo = data.deliveryInfos[i];
            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;

            if ((typeof(value) == "undefined") || (deliveryInfo.charge > value)) {
                value = deliveryInfo.charge;
            }
        }   

        if (typeof(value) == "undefined") return 0;
        return value;
    }

    self.getMinMinOrderPossible = function(data) 
    {
        var value = undefined;

        for (var i = 0 ; i < data.deliveryInfos.length ; i++)
        {
            var deliveryInfo = data.deliveryInfos[i];
            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;

            if ((typeof(value) == "undefined") || (deliveryInfo.minOrderPrice < value)) {
                value = deliveryInfo.minOrderPrice;
            }
        }   

        if (typeof(value) == "undefined") return 0;
        return value;
    }

    self.getMaxMinOrderPossible = function(data) 
    {
        var value = undefined;

        for (var i = 0 ; i < data.deliveryInfos.length ; i++)
        {
            var deliveryInfo = data.deliveryInfos[i];
            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;

            if ((typeof(value) == "undefined") || (deliveryInfo.minOrderPrice > value)) {
                value = deliveryInfo.minOrderPrice;
            }
        }   

        if (typeof(value) == "undefined") return 0;
        return value;
    }

    self.getMinDelayMinsPossible = function(data) 
    {
        var value = undefined;

        for (var i = 0 ; i < data.deliveryInfos.length ; i++)
        {
            var deliveryInfo = data.deliveryInfos[i];
            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;

            if ((typeof(value) == "undefined") || (deliveryInfo.delayMins < value)) {
                value = deliveryInfo.delayMins;
            }
        }   

        if (typeof(value) == "undefined") return 0;
        return value;
    }

    self.isMultipleDeliveryArea = function(data)
    {
        var areas = {};

        for (var i = 0 ; i < data.deliveryInfos.length ; i++)
        {
            var deliveryInfo = data.deliveryInfos[i];

            if ((deliveryInfo.type == "delivery") && (!deliveryInfo.inactive))
            {
                var key = deliveryInfo.getHash();
                areas[key] = 1;
            }
        }

        var value = 0;
        for (k in areas)
        {
            value++;
            if (value > 1) return true;
        }

        return false;
    }

	return self;
}());

