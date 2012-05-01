Item.prototype.getItemImage = function(size, distributor, refresh)
{
    if (!this.picture)
    {
        return distributor.getNoImageUrl(size);
    }

    var ret = this.picture;
    if (size) { ret += "=s"+parseInt(size) };
    if (refresh) { ret += "?time="+(new Date()).getTime(); }
    return ret;
}

Item.prototype.getAvailabilityStr = function()
{
    var printer = new AvailabilityPrinter();
    return printer.prettyPrint(SPICE_STR("${AvailabilityAlways}"), this.availability);
}

Item.prototype.doesFitOrderMinimum = function(orderCost)
{
    for (var i in this.labels)
    {
        var label = this.labels[i];
        if (label.indexOf(LABEL_MINIMAL_ORDER) > -1)
        {
            var minimum = parseInt(label.substring(10));
            if (minimum > orderCost) return false;
        }
    }

    return true;
}

Item.prototype.getStatus = function(timezone)
{
    if (this.inactive)
    {
        return {'status': STATUS_TEMPORARY_UNAVAILABLE, until:Number.MAX_VALUE}; 
    }

    var now = new timezoneJS.Date();
    now.setTimezone(timezone);
    now.setTimestampToNow();

    var util = new TimeWindowsIterator(now, this.availability);
    if (!util.hasNext())
    {
        console.log("TimeWindowsIterator >> item availability hasNext returned false!");
        return {'status': STATUS_AVAILABLE, until:Number.MAX_VALUE}; 
    }

    var availability = util.next();
    if (typeof(availability.until) == "undefined") availability.until = Number.MAX_VALUE;
    return availability;
}

Item.prototype.getUrl = function(local, distributorId)
{
    var base = getBaseSpiceUrl(distributorId);
    return base+"/restaurants/"+this.restaurantId+"/items/"+this.id;
}

Item.prototype.getPriceRange = function(tagMap)
{
    var basePrice = this.price;
    var minimumPriceRanges = [[basePrice]];
    for (var i in this.variations)
    {
        minimumPriceRanges.push(this.getMinimumPriceRange(this.variations[i], tagMap));
    }

    var numNonTrivialRanges = 0;
	var wasPairRange = false;
	var minMinimumPrice = 0;
	var maxMinimumPrice = 0;
    for (var i in minimumPriceRanges)
    {
        var minimumPriceRange = minimumPriceRanges[i];
        if (dojo.indexOf(minimumPriceRange, 0) == -1)
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
}

Item.prototype.getMinimumPriceRange = function(variation, tagMap)
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
}
