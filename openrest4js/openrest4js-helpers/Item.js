var openrest = openrest || {};

openrest.ItemHelper = openrest.ItemHelper || (function() {
	var self = {};

    function indexOf(arr, val)
    {
        for (var i in arr) if (arr[i] === val) return i;
        return -1;
    };
    
    var cachedTimezone = null;
    var cachedTime = new timezoneJS.Date();
    var cachedTimeTime = null;

    self.getCurrentStatus = function(item, timezone, _time)
    {
        if (_time)
        {
            if (_time.getTimezone() !== cachedTimezone)
            {
                cachedTimezone = _time.getTimezone();
                cachedTime.setTimezone(_time.getTimezone());
            }
            cachedTime.setTime(_time.getTime());
        }
        else
        {
            if (timezone !== cachedTimezone)
            {
                cachedTimezone = timezone;
                cachedTime.setTimezone(timezone);
            }

            var now = new Date().getTime();

            if ((!cachedTimeTime) || (now > cachedTimeTime+5000))
            {
                cachedTime.setTimestampToNow();
                cachedTimeTime = now;
            }
        }

        var times = item.availability || {weekly:[], exceptions:[]};
        var util = new availability.AvailabilityIterator({
        	cal : cachedTime.clone(),
        	availability : times
        });

        if (!util.hasNext())
        {
            return {status:OPENREST_STATUS_STATUS_UNAVAILABLE, until:Number.MAX_VALUE};
        }

        var status = util.next();
        return status;
    };

    self.getStatus = function(item, time)
    {
        if (item.inactive)
        {
            return {'status': OPENREST_STATUS_STATUS_UNAVAILABLE, until:Number.MAX_VALUE}; 
        }

        return openrest.StatusHelper.getStatus(item.availability, time);
    };

    self.getUrl = function(item, local, distributorId)
    {
        var base = getBaseSpiceUrl(distributorId);
        return base+"/restaurants/"+item.restaurantId+"/items/"+item.id;
    };

    self.getItemImage = function(params)
    {
        var item = params.item;
        var size = params.size;
        var distributor = params.distributor;
        var refresh = params.refresh;

        if (!item.picture)
        {
            return distributor.getNoImageUrl(size);
        }

        var ret = item.picture;
        if (size) { ret += "=s"+parseInt(size) };
        if (refresh) { ret += "?time="+(new Date()).getTime(); }
        return ret;
    };

    self.doesFitOrderMinimum = function(params)
    {
        var item = params.item;
        var orderCost = params.orderCost;

        for (var i in item.labels)
        {
            var label = item.labels[i];
            if (label.indexOf(LABEL_MINIMAL_ORDER) > -1)
            {
                var minimum = parseInt(label.substring(10));
                if (minimum > orderCost) return false;
            }
        }

        return true;
    };

    function getMinimumPriceRange(variation, tagMap)
    {
        var minNumAllowed = variation.minNumAllowed || 0;
        switch (minNumAllowed) {
            case 0:
                return [0];
            case 1:
                {
                    var itemIds = tagMap[variation.tagId].itemIds;
                    var minimumPriceRange = [];
                    for (var i in itemIds) {
                        var itemId = itemIds[i];
                        minimumPriceRange.push(((variation.prices) && (variation.prices[itemId])) || 0);
                    }
                    return minimumPriceRange;
                }
            default:
                return [0];
        }
    };


    self.getPriceRange = function(params)
    {
        var item = params.item;
        var tagMap = params.tagMap;

        var basePrice = item.price || 0;
        var minimumPriceRanges = [[basePrice]];
        for (var i in item.variations || [])
        {
            minimumPriceRanges.push(getMinimumPriceRange(item.variations[i], tagMap));
        }

        var numNonTrivialRanges = 0;
        var wasPairRange = false;
        var minMinimumPrice = 0;
        var maxMinimumPrice = 0;
        for (var i in minimumPriceRanges)
        {
            var minimumPriceRange = minimumPriceRanges[i];
            if (indexOf(minimumPriceRange, 0) == -1)
            {
                var minPrice = Number.MAX_VALUE;
                var maxPrice = Number.MIN_VALUE;

                for (var j in minimumPriceRange) {
                    minPrice = Math.min(minPrice, parseInt(minimumPriceRange[j]));
                    maxPrice = Math.max(maxPrice, parseInt(minimumPriceRange[j]));
                }

                minMinimumPrice += minPrice;
                maxMinimumPrice += maxPrice;

                var rangeSize = minimumPriceRange.length;
                if (rangeSize > 1) {
                    ++numNonTrivialRanges;
                    if (rangeSize == 2) {
                        wasPairRange = true;
                    }
                }
            }
        }

        var s = "";
        if (minMinimumPrice != maxMinimumPrice) {
            if (numNonTrivialRanges != 1) {
                return {"min":basePrice, "max":basePrice};
            } else if (wasPairRange) {
                return {"min":minMinimumPrice, "max":maxMinimumPrice, "range":false}
            } else {
                return {"min":minMinimumPrice, "max":maxMinimumPrice, "range":true}
            }
        } 
        else 
        {
            return {"min":minMinimumPrice, "max":minMinimumPrice};
        }
    };

	return self;
}());
