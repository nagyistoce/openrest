OrderItem.prototype.gTotalPrice = function()
{
    var price = this.price;
    if (!price) price = 0;

    Ti.API.timestamp("0. "+price);
    for (var i in this.variationsChoices) {
        Ti.API.timestamp("1. "+i);
        for (var j in this.variationsChoices[i]) {
            Ti.API.timestamp("2. "+j);
            var tempPrice = this.variationsChoices[i][j].price;
            if (!tempPrice) tempPrice = 0;
            var tempCount = this.variationsChoices[i][j].count;
            if (!tempCount) tempCount = 1;
            price += tempPrice*tempCount;
            Ti.API.timestamp("3. "+price);
        }
    }

    var count = this.count;
    if (!count) {
        count = 1;
    }
    price = price * count;
    return price;
};
