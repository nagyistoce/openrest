openrest = openrest || {};

openrest.RestaurantHelper = openrest.RestaurantHelper || (function() {
	var self = {};

    function isAvailable(restaurant, times, time)
    {
        if (!times) return true;

        if (!time)
        {
            time = new timezoneJS.Date();
            time.setTimezone(restaurant.timezone);
            time.setTimestampToNow();
        }

        var util = new availability.AvailabilityIterator({
        	cal : time,
        	availability : times
        });
        if (!util.hasNext())
        {
            return true;
        }

        var status = util.next();
        return status.status === OPENREST_STATUS_STATUS_AVAILABLE;
    }

    self.getOneLineAddress = function(restaurant)
    {
        var s = restaurant.address.street+" "+restaurant.address.number+" "+restaurant.address.city;
        if (restaurant.address.postalCode)
        {
            s += " "+restaurant.address.postalCode;
        }
        return s;
    }

    function getNextTimeStatusIs(restaurant, times, wantedStatus, _time, inactive)
    {
        var time = new timezoneJS.Date();
        time.setTimezone(restaurant.timezone);
        time.setTimestampToNow();

        if (_time)
        {
            time.setTimezone(_time.getTimezone());
            time.setTime(_time.getTime());
        }

        if (inactive)
        {
            return status === OPENREST_STATUS_STATUS_UNAVAILABLE ? {when:time} : {when:Number.MAX_VALUE};
        }

        var when = time.getTime() || Number.MAX_VALUE;

        var times = times || {weekly:[], exceptions:[]};
        var util = new availability.AvailabilityIterator({
        	cal : time,
        	availability : times
        });

        if (!util.hasNext())
        {
            return {when:Number.MAX_VALUE};
        }

        var status = util.next();
        var reason = status.reason;
        var comment = status.comment;
        while (status.status !== wantedStatus)
        {
            if (!status.until) return {when:Number.MAX_VALUE};

            when = status.until;
            reason = status.reason;
            comment = status.comment;

        	status = util.next();
        }
        return {when:when, reason:reason, comment:comment}
    }

    self.getStatus = function(restaurant, time)
    {
        if (restaurant.inactive)
        {
            return {'status': OPENREST_STATUS_STATUS_UNAVAILABLE, until:Number.MAX_VALUE}; 
        }

        var nextAvailable = getNextTimeStatusIs(restaurant, restaurant.openTimes, OPENREST_STATUS_STATUS_AVAILABLE, time, restaurant.inactive);
        var nextUnavailable = getNextTimeStatusIs(restaurant, restaurant.openTimes, OPENREST_STATUS_STATUS_UNAVAILABLE, time, restaurant.inactive);

        if (nextAvailable.when < nextUnavailable.when)
        {
            return {status:OPENREST_STATUS_STATUS_AVAILABLE, until:nextUnavailable.when, reason:nextAvailable.reason, comment:nextAvailable.comment};
        }
        else
        {
            return {status:OPENREST_STATUS_STATUS_UNAVAILABLE, until:nextAvailable.when, reason:nextUnavailable.reason, comment:nextUnavailable.comment};
        }
    }

    self.getDeliveryStatus = function(restaurant, time)
    {
        if (restaurant.inactive)
        {
            return {'status': OPENREST_STATUS_STATUS_UNAVAILABLE, until:Number.MAX_VALUE}; 
        }

        var nextAvailable = getNextTimeStatusIs(restaurant, restaurant.deliveryTimes, OPENREST_STATUS_STATUS_AVAILABLE, time, restaurant.inactive);
        var nextUnavailable = getNextTimeStatusIs(restaurant, restaurant.deliveryTimes, OPENREST_STATUS_STATUS_UNAVAILABLE, time, restaurant.inactive);

        if (nextAvailable.when < nextUnavailable.when)
        {
            return {status:OPENREST_STATUS_STATUS_AVAILABLE, until:nextUnavailable.when, reason:nextAvailable.reason, comment:nextAvailable.comment};
        }
        else
        {
            return {status:OPENREST_STATUS_STATUS_UNAVAILABLE, until:nextAvailable.when, reason:nextUnavailable.reason, comment:nextUnavailable.comment};
        }
    };

    self.isTakeoutInactive = function(restaurant)
    {
        for (var i in restaurant.deliveryInfos)
        {
            if (restaurant.deliveryInfos[i].type == "takeout") 
                return restaurant.deliveryInfos[i].inactive || false;
        }
        return true;
    }

    self.isDeliveryInactive = function(restaurant)
    {
        for (var i in restaurant.deliveryInfos)
        {
            if ((restaurant.deliveryInfos[i].type !== "takeout") && (!restaurant.deliveryInfos[i].inactive))
                return false;
        }
        return true;
    }

    self.getDeliveryInfoWithMinCharge = function(data, time)
    {
        var value = undefined;

        for (var i = 0 ; i < data.deliveryInfos.length ; i++)
        {
            var deliveryInfo = data.deliveryInfos[i];
            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;
            if (!isAvailable(data, deliveryInfo.availability, time)) continue;

            if ((typeof(value) == "undefined") || (deliveryInfo.charge < value.charge)) {
                value = deliveryInfo;
            }
        }   

        if (typeof(value) == "undefined") return undefined;
        return value;
    }

    self.getMinDeliveryChargePossible = function(data, time) 
    {
        var info = self.getDeliveryInfoWithMinCharge(data, time);
        if (typeof(info) == "undefined") return 0;
        if (typeof(info.charge) == "undefined") return 0;
        return info.charge;
    }

    self.getMinMinPricedInfoContainingGecode = function(restaurant, geocode, time)
    {
        var best = undefined;

        for (var i = 0 ; i < restaurant.deliveryInfos.length ; i++)
        {
            var deliveryInfo = restaurant.deliveryInfos[i];

            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;
            if (!isAvailable(restaurant, deliveryInfo.availability, time)) continue;
            if ((best) && (best.minOrderPrice < deliveryInfo.minOrderPrice)) continue;

            if (isInPolygon(deliveryInfo.area.polygon, geocode))
            {
                best = deliveryInfo;
            }
        }

        return best;
    }
    self.getInactiveDeliveryAreas = function(restaurant, geocode)
    {
        var ret = [];

        for (var i = 0 ; i < restaurant.deliveryInfos.length ; i++)
        {
            var deliveryInfo = restaurant.deliveryInfos[i];

            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;

            if (isInPolygon(deliveryInfo.area.polygon, geocode))
            {
                ret.push(deliveryInfo);
            }
        }

        return ret;
    }

    self.getMaxDeliveryChargePossible = function(data, time)
    {
        var value = undefined;

        for (var i = 0 ; i < data.deliveryInfos.length ; i++)
        {
            var deliveryInfo = data.deliveryInfos[i];
            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;
            if (!isAvailable(data, deliveryInfo.availability, time)) continue;

            if ((typeof(value) == "undefined") || (deliveryInfo.charge > value)) {
                value = deliveryInfo.charge;
            }
        }   

        if (typeof(value) == "undefined") return 0;
        return value;
    }

    self.getMinMinOrderPossible = function(data, time) 
    {
        var value = undefined;

        for (var i = 0 ; i < data.deliveryInfos.length ; i++)
        {
            var deliveryInfo = data.deliveryInfos[i];
            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;
            if (!isAvailable(data, deliveryInfo.availability, time)) continue;

            if ((typeof(value) == "undefined") || (deliveryInfo.minOrderPrice < value)) {
                value = deliveryInfo.minOrderPrice;
            }
        }   

        if (typeof(value) == "undefined") return 0;
        return value;
    }

    self.getMaxMinOrderPossible = function(data, time) 
    {
        var value = undefined;

        for (var i = 0 ; i < data.deliveryInfos.length ; i++)
        {
            var deliveryInfo = data.deliveryInfos[i];
            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;
            if (!isAvailable(data, deliveryInfo.availability, time)) continue;

            if ((typeof(value) == "undefined") || (deliveryInfo.minOrderPrice > value)) {
                value = deliveryInfo.minOrderPrice;
            }
        }   

        if (typeof(value) == "undefined") return 0;
        return value;
    }

    self.getMinDelayMinsPossible = function(data, time) 
    {
        var value = undefined;

        for (var i = 0 ; i < data.deliveryInfos.length ; i++)
        {
            var deliveryInfo = data.deliveryInfos[i];
            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;
            if (!isAvailable(data, deliveryInfo.availability, time)) continue;

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

    self.getDeliveryInfo = function(total, restaurant, geocode, time)
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
            if (!isAvailable(restaurant, deliveryInfo.availability, time)) continue;
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

