Category.prototype.getStatus = function(timezone)
{
    if (!this.properties)
    {
        return {'status': OPENREST_STATUS_STATUS_AVAILABLE, until:Number.MAX_VALUE}; 
    }

    var properties = this.properties["com.implied2.spice"] || {inactive:false, availability:{}};
    properties.availability = properties.availability || {};
   
    if (properties.inactive)
    {
        return {'status': OPENREST_STATUS_STATUS_UNAVAILABLE, until:Number.MAX_VALUE}; 
    }

    var now = new timezoneJS.Date();
    now.setTimezone(timezone);
    now.setTimestampToNow();

    var util = new TimeWindowsIterator(now, properties.availability);
    if (!util.hasNext())
    {
        console.log("TimeWindowsIterator >> item availability hasNext returned false!");
        return {'status': OPENREST_STATUS_STATUS_AVAILABLE, until:Number.MAX_VALUE}; 
    }

    var availability = util.next();
    if (typeof(availability.until) == "undefined") availability.until = Number.MAX_VALUE;
    return availability;
}
