function CategoryFromObj(data)
{
    return new Category(data.id, data.restaurantId, data.description, data.itemIds, data.title, data.parentCategoryId, data.properties);
}

function Category(id, restaurantId, description, itemIds, title, parentCategoryId, properties)
{
    this.id = id;
    this.restaurantId = restaurantId;
    this.description = description;
    this.itemIds = itemIds;
    this.title = title;
    this.parentCategoryId = parentCategoryId;
    this.properties = ORDeserializeProperties(properties);
}

Category.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (this.id) ret['id'] = this.id;
    if (this.restaurantId) ret['restaurantId'] = this.restaurantId;
    if (this.description) ret['description'] = this.description;
    if (this.itemIds) ret['itemIds'] = this.itemIds;
    if (this.title) ret['title'] = this.title;
    if (this.parentCategoryId) ret['parentCategoryId'] = this.parentCategoryId;
    if (this.properties) ret['properties'] = ORSerializeProperties(this.properties);

    return ret;
}
