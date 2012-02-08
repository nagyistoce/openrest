function WeeklyTimeWindowFromObj(data)
{
    return new WeeklyTimeWindow(data.minuteOfWeek, data.durationMins);
}

function WeeklyTimeWindow(minuteOfWeek, durationMins) {
    this.minuteOfWeek = minuteOfWeek;
    this.durationMins = durationMins;
}

WeeklyTimeWindow.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (typeof(this.minuteOfWeek) != "undefined") ret['minuteOfWeek'] = this.minuteOfWeek;
    if (typeof(this.durationMins) != "undefined") ret['durationMins'] = this.durationMins;

    return ret;
}
