OrderItem.prototype.gTotalPrice = function()
{
    var price = this.price;
    if (!price) price = 0;

    for (var i in this.variationsChoices) {
        for (var j in this.variationsChoices[i]) {
            var tempPrice = this.variationsChoices[i][j].price;
            if (!tempPrice) tempPrice = 0;
            var tempCount = this.variationsChoices[i][j].count;
            if (!tempCount) tempCount = 1;
            price += tempPrice*tempCount;
        }
    }

    var count = this.count;
    if (typeof(count) === "undefined") {
        count = 1;
    }
    price = price * count;
    return price;
};
