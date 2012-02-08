Item.prototype.getItemImage = function(size, refresh)
{
    if (!this.picture)
    {
        return __itemprivate[this.privateid].restaurantClient.distributor.getNoImageUrl(size);
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
