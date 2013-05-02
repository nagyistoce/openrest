var openrest = openrest || {};

openrest.CategoryHelper = openrest.CategoryHelper || (function() {
	var self = {};

    var cachedTimezone = null;
    var cachedTime = new timezoneJS.Date();

    function getNextTimeStatusIs(timezone, times, _time)
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
            cachedTime.setTimestampToNow();
        }

        var when = cachedTime.getTime() || Number.MAX_VALUE; // Has to be before the iterator since
                // the iterator changes cachedTime.getTime()

        var times = times || {weekly:[], exceptions:[]};
        var util = new availability.AvailabilityIterator({
        	cal : cachedTime,
        	availability : times
        });

        if (!util.hasNext())
        {
            return {"available":{when:Number.MAX_VALUE}, "unavailable":{when:Number.MAX_VALUE}};
        }

        var ret = {"available":{}, "unavailable":{}};

        var nowStatus = util.next();

        var reason = nowStatus.reason;
        var comment = nowStatus.comment;

        ret[nowStatus.status].when = when;
        ret[nowStatus.status].until = nowStatus.until || Number.MAX_VALUE;
        ret[nowStatus.status].comment = nowStatus.comment;
        ret[nowStatus.status].reason = nowStatus.reason;
        when = nowStatus.until || Number.MAX_VALUE;

        if (!nowStatus.until)
        {
            return ret;
        }

        var nextStatus = util.next();
        while (nextStatus.status === nowStatus.status)
        {
            ret[nextStatus.status].until = nextStatus.until || Number.MAX_VALUE;
            when = nextStatus.until || Number.MAX_VALUE;

            if (!nextStatus.until) { return ret; }

            nextStatus = util.next();
        }

        ret[nextStatus.status].when = when;
        ret[nextStatus.status].until = nextStatus.until || Number.MAX_VALUE;
        ret[nextStatus.status].comment = nextStatus.comment;
        ret[nextStatus.status].reason = nextStatus.reason;

        // TODO, until not completely correct here, should go next until hitting the first...

        return ret;
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

        if (properties.inactive)
        {
            return {'status': OPENREST_STATUS_STATUS_UNAVAILABLE, until:Number.MAX_VALUE}; 
        }

        var times = properties.availability || {weekly:[], exceptions:[]};

        var statuses = getNextTimeStatusIs(timezone, times, time);
        var nextAvailable = statuses[OPENREST_STATUS_STATUS_AVAILABLE].when || Number.MAX_VALUE;
        var nextUnavailable = statuses[OPENREST_STATUS_STATUS_UNAVAILABLE].when || Number.MAX_VALUE;

        if (nextAvailable < nextUnavailable)
        {
            return {status:OPENREST_STATUS_STATUS_AVAILABLE, until:nextUnavailable, reason:statuses[OPENREST_STATUS_STATUS_AVAILABLE].reason, comment:statuses[OPENREST_STATUS_STATUS_AVAILABLE].comment};
        }
        else
        {
            return {status:OPENREST_STATUS_STATUS_UNAVAILABLE, until:nextAvailable, reason:statuses[OPENREST_STATUS_STATUS_UNAVAILABLE].reason, comment:statuses[OPENREST_STATUS_STATUS_UNAVAILABLE].comment};
        }
    };

	return self;
}());

eee = 0;
ddd = 0;
