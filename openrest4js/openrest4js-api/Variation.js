OPENREST_VARIATION_DISPLAY_TYPE_DIFF = "diff";
OPENREST_VARIATION_DISPLAY_TYPE_CHOICE = "choice";
    
function VariationFromObj(data)
{
    return new Variation(data.title, data.itemIds, data.minNumAllowed, 
            data.maxNumAllowed, data.prices, data.defaults, data.displayType);
}

function Variation(title, itemIds, minNumAllowed, maxNumAllowed, prices, defaults, displayType) {
    this.title = title;
    this.itemIds = itemIds;
    this.minNumAllowed = minNumAllowed;
    this.maxNumAllowed = maxNumAllowed;
    this.prices = prices;
    this.defaults = defaults;
    this.displayType = displayType;
}

Variation.prototype.toOpenRestObj = function()
{
    var ret = {};
    
    if (typeof(this.title) != "undefined") ret['title'] = this.title;
    if (typeof(this.itemIds) != "undefined") ret['itemIds'] = this.itemIds;
    if (typeof(this.minNumAllowed) != "undefined") ret['minNumAllowed'] = this.minNumAllowed;
    if (typeof(this.maxNumAllowed) != "undefined") ret['maxNumAllowed'] = this.maxNumAllowed;
    if (typeof(this.prices) != "undefined") ret['prices'] = this.prices;
    if (typeof(this.defaults) != "undefined") ret['defaults'] = this.defaults;
    if (typeof(this.displayType) != "undefined") ret['displayType'] = this.displayType;

    return ret;
}
