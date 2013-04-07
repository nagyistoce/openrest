var openrest = openrest || {};

openrest.CategoryHelper = openrest.CategoryHelper || (function() {
	var self = {};

    self.getStatus = function(params)
    {
        var category = params.category;
        var timezone = params.timezone;

        if (!category.properties)
        {
            return {'status': OPENREST_STATUS_STATUS_AVAILABLE, until:Number.MAX_VALUE}; 
        }

        var properties = JSON.parse(category.properties["com.implied2.spice"] || "{\"inactive\":false}");
        var times = properties.availability || {weekly:[], exceptions:[]};
   
        if (properties.inactive)
        {
            return {'status': OPENREST_STATUS_STATUS_UNAVAILABLE, until:Number.MAX_VALUE}; 
        }

        var now = new timezoneJS.Date();
        now.setTimezone(timezone);
        now.setTimestampToNow();

        var util = new availability.AvailabilityIterator({
        	cal : now,
        	availability : times
        });
        if (!util.hasNext())
        {
            return {'status': OPENREST_STATUS_STATUS_AVAILABLE, until:Number.MAX_VALUE}; 
        }

        var status = util.next();
        if (!status.until) {
        	status.until = Number.MAX_VALUE;
        }
        return status;
    };

	return self;
}());

