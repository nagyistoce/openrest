function AreaFromObj(data)
{
    return new Area(data.title, data.polygon);
}

function Area(title, polygon)
{
    this.title = title;
    this.polygon = polygon;
}

Area.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (typeof(this.title) != "undefined") ret['title'] = this.title;
    if (typeof(this.polygon) != "undefined") ret['polygon'] = this.polygon;
    
    return ret;
}
