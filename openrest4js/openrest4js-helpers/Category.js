var openrest = openrest || {};

openrest.CategoryHelper = openrest.CategoryHelper || (function() {
	var self = {};

    self.getStatus = function(params)
    {
        var category = params.category;
        var time = params.time;

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

        return openrest.StatusHelper.getStatus(times, time);
    };

	return self;
}());
