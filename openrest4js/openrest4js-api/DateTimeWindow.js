function DateTimeWindowFromObj(data)
{
    return new DateTimeWindow(data.start ? OpenrestDateFromObj(data.start) : undefined,
            data.end ? OpenrestDateFromObj(data.end) : undefined,
            typeof(data.available) === "undefined" ? true : data.available, data.reason, data.comment);
}

function DateTimeWindow(start, end, available, reason, comment)
{
    this.start = start;
    this.end = end;
    this.available = available;
    this.reason = reason;
    this.comment = comment;
}
    
DateTimeWindow.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (typeof(this.start) != "undefined") ret['start'] = this.start.toOpenRestObj();
    if (typeof(this.end) != "undefined") ret['end'] = this.end.toOpenRestObj();
    if (typeof(this.available) != "undefined") ret['available'] = this.available;
    if (typeof(this.reason) != "undefined") ret['reason'] = this.reason;
    if (typeof(this.comment) != "undefined") ret['comment'] = this.comment;

    return ret;
}
