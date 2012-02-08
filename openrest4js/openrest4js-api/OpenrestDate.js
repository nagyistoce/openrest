function OpenrestDateFromObj(data)
{
    return new OpenrestDate(data.year, data.month, data.day, data.hour, data.minute);
}

function OpenrestDate(year, month, day, hour, minute) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
}

OpenrestDate.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (typeof(this.year) != "undefined") ret['year'] = this.year;
    if (typeof(this.month) != "undefined") ret['month'] = this.month;
    if (typeof(this.day) != "undefined") ret['day'] = this.day;
    if (typeof(this.hour) != "undefined") ret['hour'] = this.hour;
    if (typeof(this.minute) != "undefined") ret['minute'] = this.minute;

    return ret;
}
