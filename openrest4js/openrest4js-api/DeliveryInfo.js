function DeliveryInfoFromObj(data)
{
    return new DeliveryInfo(data.type, data.area ? AreaFromObj(data.area) : undefined, data.minOrderPrice, 
            data.charge, data.delayMins, data.inactive);
}

function DeliveryInfo(type, area, minOrderPrice, charge, delayMins, inactive) 
{
    this.type = type;
    this.area = area;
    this.minOrderPrice = minOrderPrice;
    this.charge = charge;
    this.delayMins = delayMins;
    this.inactive = inactive;
}

DeliveryInfo.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (typeof(this.type) != "undefined") ret['type'] = this.type;
    if (typeof(this.area) != "undefined") ret['area'] = this.area.toOpenRestObj();
    if (typeof(this.minOrderPrice) != "undefined") ret['minOrderPrice'] = this.minOrderPrice;
    if (typeof(this.charge) != "undefined") ret['charge'] = this.charge;
    if (typeof(this.delayMins) != "undefined") ret['delayMins'] = this.delayMins;
    if (typeof(this.inactive) != "undefined") ret['inactive'] = this.inactive;

    return ret;
}
