OPENREST_TAG_MODE_INCLUDE = "include";
OPENREST_TAG_MODE_EXCLUDE = "exclude";
	
function TagFromObj(data)
{
    return new Tag(data.id, data.restaurantId, data.title, data.itemIds, data.properties);
}

function Tag(id, restaurantId, title, itemIds, properties) {
    this.id = id;
    this.restaurantId = restaurantId;
    this.title = title;
    this.itemIds = itemIds;
    this.properties = properties;
}

Tag.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (typeof(this.id) != "undefined") ret['id'] = this.id;
    if (typeof(this.restaurantId) != "undefined") ret['restaurantId'] = this.restaurantId;
    if (typeof(this.title) != "undefined") ret['title'] = this.title;
    if (typeof(this.itemIds) != "undefined") ret['itemIds'] = this.itemIds;
    if (typeof(this.properties) != "undefined") ret['properties'] = this.properties;

    return ret;
}
