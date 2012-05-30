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

	return self;
}());

