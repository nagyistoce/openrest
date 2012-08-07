openrest = openrest || {};

openrest.RestaurantHelper = openrest.RestaurantHelper || (function() {
	var self = {};

    self.getOneLineAddress = function(restaurant)
    {
        var s = restaurant.address.street+" "+restaurant.address.number+" "+restaurant.address.city;
        if (restaurant.address.postalCode)
        {
            s += " "+restaurant.address.postalCode;
        }
        return s;
    }
    self.getStatus = function(restaurant)
    {
        if (restaurant.inactive)
        {
            return {'status': OPENREST_STATUS_STATUS_UNAVAILABLE, until:Number.MAX_VALUE}; 
        }

        var now = new timezoneJS.Date();
        now.setTimezone(restaurant.timezone);
        now.setTimestampToNow();

        var times = restaurant.openTimes || {weekly:[], exceptions:[]};
        var util = new TimeWindowsIterator(now, times);
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

        var times = restaurant.deliveryTimes || {weekly:[], exceptions:[]};
        var util = new TimeWindowsIterator(now, times);
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
        if (typeof(info.charge) == "undefined") return 0;
        return info.charge;
    }

    self.getMinMinPricedInfoContainingGecode = function(restaurant, geocode)
    {
        var best = undefined;

        for (var i = 0 ; i < restaurant.deliveryInfos.length ; i++)
        {
            var deliveryInfo = restaurant.deliveryInfos[i];

            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;
            if ((best) && (best.minOrderPrice < deliveryInfo.minOrderPrice)) continue;

            if (isInPolygon(deliveryInfo.area.polygon, geocode))
            {
                best = deliveryInfo;
            }
        }

        return best;
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

    function isInPolygon(pol, point)
    {
        var i, j;
        var c = false;
        var nvert = pol.length;
        var lat = point.lat;
        var lng = point.lng;
    
        for (i = 0 , j = nvert - 1 ; i < nvert ; j = i++)
        {
            var vertex1 = pol[i];
            var vertex2 = pol[j];

            if (vertex1.lng < lng && vertex2.lng >= lng || vertex2.lng < lng && vertex1.lng >= lng)  {
                if (vertex1.lat + (lng - vertex1.lng) / (vertex2.lng - vertex1.lng) * (vertex2.lat - vertex1.lat) < lat) 
                {
                    c = !c;
                }
            }
        }
    
        return c;
    }

    self.getDeliveryInfo = function(total, restaurant, geocode)
    {
        if (!geocode)
        {
            for (var i = 0 ; i < restaurant.deliveryInfos.length ; i++)
            {
                var deliveryInfo = restaurant.deliveryInfos[i];
                if (deliveryInfo.type == "takeout") 
                {
                    return deliveryInfo;
                }
            }
            return undefined;
        }

        var best = undefined;

        for (var i = 0 ; i < restaurant.deliveryInfos.length ; i++)
        {
            var deliveryInfo = restaurant.deliveryInfos[i];

            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;
            if (total < deliveryInfo.minOrderPrice) continue;
            if ((best) && (best.charge < deliveryInfo.charge)) continue;

            if (isInPolygon(deliveryInfo.area.polygon, geocode))
            {
                best = deliveryInfo;
            }
        }

        return best;
    }

	return self;
}());

