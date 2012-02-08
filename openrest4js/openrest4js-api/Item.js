function ItemFromObj(data)
{
    return new Item(data.id, data.restaurantId, data.title, data.description, data.price, 
            data.variations ? ORJSArrayFromJsonArray(data.variations, VariationFromObj) : undefined, 
            data.availability ? AvailabilityFromObj(data.availability) : undefined, data.inactive, data.externalIds, 
            data.picture, data.labels);
}

function Item(id, restaurantId, title, description, price, variations, availability,
            inactive, externalIds, picture, labels)
{
    this.id = id;
    this.restaurantId = restaurantId;
    this.title = title;
    this.description = description;
    this.price = price ? price : 0;
    this.variations = variations ? variations : [];
    this.availability = availability ? availability : new Availability([], []);
    this.inactive = inactive ? inactive : false;
    this.externalIds = externalIds ? externalIds : {};
    this.picture = picture;
    this.labels = labels ? labels : [];
}

Item.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (this.id) ret['id'] = this.id;
    if (this.restaurantId) ret['restaurantId'] = this.restaurantId;
    if (this.title) ret['title'] = this.title;
    if (this.description) ret['description'] = this.description;
    if (this.price) ret['price'] = this.price;
    if (this.variations) ret['variations'] = ORJsonArrayFromJSArray(this.variations);
    if (this.availability) ret['availability'] = this.availability.toOpenRestObj();
    if (this.inactive) ret['inactive'] = this.inactive;
    if (this.externalIds) ret['externalIds'] = this.externalIds;
    if (this.labels) ret['labels'] = this.labels;

    return ret;
}
