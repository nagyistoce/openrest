function AvailabilityFromObj(data)
{
    return new Availability(ORJSArrayFromJsonArray(data.weekly, WeeklyTimeWindowFromObj), 
            ORJSArrayFromJsonArray(data.exceptions, DateTimeWindowFromObj));
}

function Availability(weekly, exceptions)
{
    this.weekly = weekly;
    this.exceptions = exceptions;
}

Availability.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (typeof(this.weekly) != "undefined") ret['weekly'] = ORJsonArrayFromJSArray(this.weekly);
    if (typeof(this.exceptions) != "undefined") ret['exceptions'] = ORJsonArrayFromJSArray(this.exceptions);

    return ret;
}
