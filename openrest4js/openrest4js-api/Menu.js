function MenuFromObj(data)
{
    return new Menu(data.items ? ORJSArrayFromJsonArray(data.items, ItemFromObj) : undefined,
            data.tags ? ORJSArrayFromJsonArray(data.tags, TagFromObj) : undefined,
            data.categories ? ORJSArrayFromJsonArray(data.categories, CategoryFromObj) : undefined);

}

function Menu(items, tags, categories) 
{
    this.items = items;
    this.tags = tags;
    this.categories = categories;
}

Menu.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (this.items) ret['items'] = ORJsonArrayFromJSArray(this.items);
    if (this.tags) ret['tags'] = ORJsonArrayFromJSArray(this.tags);
    if (this.categories) ret['categories'] = ORJsonArrayFromJSArray(this.categories);

    return ret;
}
