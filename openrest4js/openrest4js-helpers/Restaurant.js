openrest = openrest || {};

openrest.RestaurantHelper = openrest.RestaurantHelper || (function() {
	var self = {};

    function canMeetTime(delayMins, deliveryTime) {
        if (!deliveryTime) return true;

        var time = new timezoneJS.Date();
        var diff = (deliveryTime - time)/1000/60;

        if (diff < delayMins) return false;
        return true;
    }

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

    self.getStatus = function(restaurant, time)
    {
        if (restaurant.inactive)
        {
            return {'status': OPENREST_STATUS_STATUS_UNAVAILABLE, until:Number.MAX_VALUE}; 
        }

        return openrest.StatusHelper.getStatus(restaurant.openTimes, time);
    }

    self.getDeliveryStatus = function(restaurant, time)
    {
        if (restaurant.inactive)
        {
            return {'status': OPENREST_STATUS_STATUS_UNAVAILABLE, until:Number.MAX_VALUE}; 
        }

        if (self.isDeliveryInactive(restaurant))
        {
            return {'status': OPENREST_STATUS_STATUS_UNAVAILABLE, until:Number.MAX_VALUE}; 
        }

        return openrest.StatusHelper.getStatus(restaurant.deliveryTimes, time);
    };

    self.isTakeoutInactive = function(restaurant)
    {
        var deliveryInfos = restaurant.deliveryInfos || [];

        for (var i = 0, l = deliveryInfos.length ; i < l ; i++)
        {
            if (deliveryInfos[i].type == "takeout") 
                return deliveryInfos[i].inactive || false;
        }
        return true;
    }

    self.isDeliveryInactive = function(restaurant)
    {
        var deliveryInfos = restaurant.deliveryInfos;

        if (!deliveryInfos)
        {
            return false;
        }

        for (var i = 0, l = deliveryInfos.length ; i < l ; i++)
        {
            if ((deliveryInfos[i].type !== "takeout") && (!deliveryInfos[i].inactive))
                return false;
        }
        return true;
    }

    self.getDeliveryInfoWithMinCharge = function(data, time)
    {
        var value = undefined;

        var deliveryInfos = data.deliveryInfos || [];

        for (var i = 0 ; i < deliveryInfos.length ; i++)
        {
            var deliveryInfo = deliveryInfos[i];
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

        var deliveryInfos = restaurant.deliveryInfos || [];

        for (var i = 0 ; i < deliveryInfos.length ; i++)
        {
            var deliveryInfo = deliveryInfos[i];

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

        var deliveryInfos = restaurant.deliveryInfos || [];

        for (var i = 0 ; i < deliveryInfos.length ; i++)
        {
            var deliveryInfo = deliveryInfos[i];

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

        var deliveryInfos = data.deliveryInfos || [];

        for (var i = 0 ; i < deliveryInfos.length ; i++)
        {
            var deliveryInfo = deliveryInfos[i];
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

        var deliveryInfos = data.deliveryInfos || [];

        for (var i = 0 ; i < deliveryInfos.length ; i++)
        {
            var deliveryInfo = deliveryInfos[i];
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

        var deliveryInfos = data.deliveryInfos || [];

        for (var i = 0 ; i < deliveryInfos.length ; i++)
        {
            var deliveryInfo = deliveryInfos[i];
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
        var value = null;

        var deliveryInfos = data.deliveryInfos || [];

        for (var i = 0 ; i < deliveryInfos.length ; i++)
        {
            var deliveryInfo = deliveryInfos[i];
            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;
            if (!isAvailable(data, deliveryInfo.availability, time)) continue;

            if ((value == null) || (deliveryInfo.delayMins < value)) {
                value = deliveryInfo.delayMins;
            }
        }   

        return value || 0;
    }

    self.getMaxDelayMinsPossible = function(data, time) 
    {
        var value = undefined;

        var deliveryInfos = data.deliveryInfos || [];

        for (var i = 0 ; i < deliveryInfos.length ; i++)
        {
            var deliveryInfo = deliveryInfos[i];
            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;
            if (!isAvailable(data, deliveryInfo.availability, time)) continue;

            if ((value == null) || (deliveryInfo.delayMins > value)) {
                value = deliveryInfo.delayMins;
            }
        }   

        return value || 0;
    }

    self.isMultipleDeliveryArea = function(data)
    {
        var areas = {};

        var deliveryInfos = data.deliveryInfos || [];

        for (var i = 0 ; i < deliveryInfos.length ; i++)
        {
            var deliveryInfo = deliveryInfos[i];

            if ((deliveryInfo.type == "delivery") && (!deliveryInfo.inactive))
            {
                var key = JSON.stringify(deliveryInfo.title)+"_"+deliveryInfo.minOrderPrice+"_"+deliveryInfo.charge;
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

    self.getDeliveryInfo = function(total, restaurant, geocode, time, deliveryTime)
    {
        var deliveryInfos = restaurant.deliveryInfos || [];

        if (!geocode)
        {
            for (var i = 0 ; i < deliveryInfos.length ; i++)
            {
                var deliveryInfo = deliveryInfos[i];
                if (deliveryInfo.type == "takeout") 
                {
                    return deliveryInfo;
                }
            }
            return undefined;
        }

        var best = undefined;

        for (var i = 0 ; i < deliveryInfos.length ; i++)
        {
            var deliveryInfo = deliveryInfos[i];

            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;
            if (!isAvailable(restaurant, deliveryInfo.availability, time)) continue;
            if (!canMeetTime(deliveryInfo.delayMins, deliveryTime)) continue;
            if (total < deliveryInfo.minOrderPrice) continue;
            if ((best) && (best.charge < deliveryInfo.charge)) continue;

            if (isInPolygon(deliveryInfo.area.polygon, geocode))
            {
                best = deliveryInfo;
            }
        }

        return best;
    }

    self.getAllDeliveryInfos = function(restaurant, geocode)
    {
        var ret = [];

        var deliveryInfos = restaurant.deliveryInfos || [];

        for (var i = 0 ; i < deliveryInfos.length ; i++)
        {
            var deliveryInfo = deliveryInfos[i];

            if (deliveryInfo.type != "delivery") continue;
            if (deliveryInfo.inactive) continue;

            if (isInPolygon(deliveryInfo.area.polygon, geocode))
            {
                ret.push(deliveryInfo);
            }
        }

        return ret;
    }

	return self;
}());
