function OrderItemFromObj(data)
{
    var choices = [];
    if (data.variationsChoices)
    {
        for (var i = 0, l = data.variationsChoices.length ; i < l ; i++)
        {
            choices.push(ORJSArrayFromJsonArray(data.variationsChoices[i], OrderItemFromObj));
        }
    }
    return new OrderItem(data.itemId, data.price, data.variations, choices, 
            data.comment, typeof(data.count) === "undefined" ? 1 : data.count);
}

function OrderItem(itemId, price, variations, variationsChoices, comment, count)
{
    this.itemId = itemId;
    this.price = price;
    this.variations = JSON.parse(JSON.stringify(variations || []));
    this.variationsChoices = JSON.parse(JSON.stringify(variationsChoices || []));
    this.comment = comment;
    this.count = count;
}

OrderItem.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (this.itemId) ret['itemId'] = this.itemId;
    if (this.price) ret['price'] = this.price;
    if (this.variations) ret['variations'] = this.variations;
    if (this.variationsChoices) ret['variationsChoices'] = this.variationsChoices;
    if (this.comment) ret['comment'] = this.comment;
    if (this.count) ret['count'] = this.count;

    return ret;
}
