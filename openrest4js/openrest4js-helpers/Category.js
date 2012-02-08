Category.prototype.getStatus = function(timezone)
{
    if (!this.properties)
    {
        return {'status': STATUS_AVAILABLE, until:Number.MAX_VALUE}; 
    }

    if (this.properties['com.implied2.spice'].inactive)
    {
        return {'status': STATUS_TEMPORARY_UNAVAILABLE, until:Number.MAX_VALUE}; 
    }

    var now = new timezoneJS.Date();
    now.setTimezone(timezone);
    now.setTimestampToNow();

    var util = new TimeWindowsIterator(now, this.properties['com.implied2.spice'].availability ? this.properties['com.implied2.spice'].availability : {});
    if (!util.hasNext())
    {
        console.log("TimeWindowsIterator >> item availability hasNext returned false!");
        return {'status': STATUS_AVAILABLE, until:Number.MAX_VALUE}; 
    }

    var availability = util.next();
    if (typeof(availability.until) == "undefined") availability.until = Number.MAX_VALUE;
    return availability;
}
