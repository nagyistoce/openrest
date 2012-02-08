function AddressFromObj(data)
{
    return new Address(data.country, data.city, data.street, data.number, data.apt, data.floor, data.entrance, 
            data.comment, data.latLng, data.countryCode, data.postalCode);
}

function Address(country, city, street, number, apt, floor, entrance, comment, latLng,
        countryCode, postalCode) {
    this.country = country;
    this.city = city;
    this.street = street;
    this.number = number;
    this.apt = apt;
    this.floor = floor;
    this.entrance = entrance;
    this.comment = comment;
    this.latLng = latLng;
    this.countryCode = countryCode;
    this.postalCode = postalCode;
}

Address.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (typeof(this.country) != "undefined") ret['country'] = this.country;
    if (typeof(this.city) != "undefined") ret['city'] = this.city;
    if (typeof(this.street) != "undefined") ret['street'] = this.street;
    if (typeof(this.number) != "undefined") ret['number'] = this.number;
    if (typeof(this.apt) != "undefined") ret['apt'] = this.apt;
    if (typeof(this.floor) != "undefined") ret['floor'] = this.floor;
    if (typeof(this.entrance) != "undefined") ret['entrance'] = this.entrance;
    if (typeof(this.comment) != "undefined") ret['comment'] = this.comment;
    if (typeof(this.latLng) != "undefined") ret['latLng'] = this.latLng;
    if (typeof(this.countryCode) != "undefined") ret['countryCode'] = this.countryCode;
    if (typeof(this.postalCode) != "undefined") ret['postalCode'] = this.postalCode;

    return ret;
}
