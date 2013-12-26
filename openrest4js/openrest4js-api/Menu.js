function MenuFromObj(data)
{
    return new Menu(data.items ? ORJSArrayFromJsonArray(data.items, ItemFromObj) : undefined,
            data.categories ? ORJSArrayFromJsonArray(data.categories, CategoryFromObj) : undefined);

}

function Menu(items, categories) 
{
    this.items = items;
    this.categories = categories;
}

Menu.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (this.items) ret['items'] = ORJsonArrayFromJSArray(this.items);
    if (this.categories) ret['categories'] = ORJsonArrayFromJSArray(this.categories);

    return ret;
}
