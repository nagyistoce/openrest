var openrest = openrest || {};

openrest.CategoryHelper = openrest.CategoryHelper || (function() {
	var self = {};

    function getNextTimeStatusIs(timezone, times, wantedStatus, _time)
    {
        var time = new timezoneJS.Date();
        time.setTimezone(timezone);
        time.setTimestampToNow();

        if (_time)
        {
            time.setTimezone(_time.getTimezone());
            time.setTime(_time.getTime());
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

    self.getStatus = function(params)
    {
        var category = params.category;
        var time = params.time;
        var timezone = params.timezone || time.getTimezone();

        if (!category.properties)
        {
            return {'status': OPENREST_STATUS_STATUS_AVAILABLE, until:Number.MAX_VALUE}; 
        }

        var properties = JSON.parse(category.properties["com.implied2.spice"] || "{\"inactive\":false}");
        var times = properties.availability || {weekly:[], exceptions:[]};

        var nextAvailable = getNextTimeStatusIs(timezone, times, OPENREST_STATUS_STATUS_AVAILABLE, time);
        var nextUnavailable = getNextTimeStatusIs(timezone, times, OPENREST_STATUS_STATUS_UNAVAILABLE, time);

        if (nextAvailable.when < nextUnavailable.when)
        {
            return {status:OPENREST_STATUS_STATUS_AVAILABLE, until:nextUnavailable.when, reason:nextAvailable.reason, comment:nextAvailable.comment};
        }
        else
        {
            return {status:OPENREST_STATUS_STATUS_UNAVAILABLE, until:nextAvailable.when, reason:nextUnavailable.reason, comment:nextUnavailable.comment};
        }
    };

	return self;
}());

