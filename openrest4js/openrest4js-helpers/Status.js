openrest = openrest || {};

openrest.StatusHelper = openrest.StatusHelper || (function() {
	var self = {};

    self.toTimeText = function(params)
    {
        var milliseconds = params.milliseconds;
        var basetext = params.basetext;
        var i18n = params.i18n;

        if (milliseconds == null) {
            return "";
        }

        var now = new timezoneJS.Date();
        now = now.getTime();

        var time = milliseconds - now;
        time = Math.ceil(time / 60 / 1000);

        if (time > 60) return "";

        if (time > 1) return i18n.get(basetext, {params:{'minutes':time}});

        return i18n.get(basetext+"OneMinute");
    }

    var items = null;
    var categories = null;
    var baseTime = null;

    self.buildForMenu = function(params)
    {
        var time = params.time;
        var menu = params.menu || {};

        items = {};
        categories = {};

        baseTime = time.clone();

        var _items = menu.items || [];
        var _categories = menu.categories || [];

        _.each(_items, function(item)
        {
            items[item.id] = openrest.ItemHelper.getStatus(item, time);
        });
        _.each(_categories, function(category)
        {
            categories[category.id] = openrest.ItemHelper.getStatus(category, time);
        });
    };

    self.getStatusForItem = function(params)
    {
        var item = params.item;
        var time = params.time;

        var cache = (items || {})[item.id];

        var useCache = true;

        if (!cache) useCache = false;
        else if (!baseTime) useCache = false;
        else if (time.getTime() < baseTime.getTime()) useCache = false;
        else if (time.getTimezone() !== baseTime.getTimezone()) useCache = false;
        else if (cache.until < time.getTime()) useCache = false;

        if (useCache) return cache;

        return openrest.ItemHelper.getStatus(item, time);
    }

    self.getStatusForCategory = function(params)
    {
        var category = params.category;
        var time = params.time;

        var cache = (categories || {})[category.id];

        if (!cache) useCache = false;
        else if (!baseTime) useCache = false;
        else if (time.getTime() < baseTime.getTime()) useCache = false;
        else if (time.getTimezone() !== baseTime.getTimezone()) useCache = false;
        else if (cache.until < time.getTime()) useCache = false;

        if (useCache) return cache;

        return openrest.CategoryHelper.getStatus(category, time);
    }

    self.getCombinedStatus = function(_availability, time)
    {
        var when = time.getTime() || Number.MAX_VALUE; // Has to be before the iterator since
                // the iterator changes cachedTime.getTime()

        var _availability = _availability || {weekly:[], exceptions:[]};
        var util = new availability.AvailabilityIterator({
        	cal : time.clone(),
        	availability : _availability
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
    };

    self.getStatus = function(availability, time)
    {
        var statuses = self.getCombinedStatus(availability, time);
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

