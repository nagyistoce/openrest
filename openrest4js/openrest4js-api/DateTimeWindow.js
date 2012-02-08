function DateTimeWindowFromObj(data)
{
    return new DateTimeWindow(data.start ? OpenrestDateFromObj(data.start) : undefined,
            data.end ? OpenrestDateFromObj(data.end) : undefined);
}

function DateTimeWindow(start, end, available)
{
    this.start = start;
    this.end = end;
    this.available = available;
}
    
DateTimeWindow.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (typeof(this.start) != "undefined") ret['start'] = this.start.toOpenRestObj();
    if (typeof(this.end) != "undefined") ret['end'] = this.end.toOpenRestObj();
    if (typeof(this.available) != "undefined") ret['available'] = this.available;

    return ret;
}
