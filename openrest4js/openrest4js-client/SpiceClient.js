function SpiceClient(url)
{
    this.url = url;
}

SpiceClient.prototype.getRestaurant = function(name, context, callback) 
{
    this.get(this.url+"/restaurants/"+name, 
            function(result)
            {
                if (result.error) 
                {
                    if (typeof(callback) != "undefined")
                    {
                        context[callback](undefined, result.obj);
                    }
                    else
                    {
                        context(undefined, result.obj);
                    }
                }
                else
                {
                    if (typeof(callback) != "undefined")
                    {
                        context[callback](result.obj.value);
                    }
                    else
                    {
                        context(RestaurantFromObj(result.obj.value));
                    }
                }
            });
}

SpiceClient.prototype.getRestaurantMenu = function(name, context, callback) 
{
    this.get(this.url+"/restaurants/"+name+"/menu", 
            function(result)
            {
                if (result.error) 
                {
                    if (typeof(callback) != "undefined")
                    {
                        context[callback](undefined, result.obj);
                    }
                    else
                    {
                        context(undefined, result.obj);
                    }
                }
                else
                {
                    if (typeof(callback) != "undefined")
                    {
                        context[callback](result.obj.value);
                    }
                    else
                    {
                        context(MenuFromObj(result.obj.value));
                    }
                }
            });
}

SpiceClient.prototype.getRestaurantOrder = function(name, order, context, callback) 
{
    this.get("https://api.openrest.com/v1.1/restaurants/pekin/orders/1809164?access_token=spice%7Cpekin%7Cpekin102030&restaurantView=true&media=json",
           // this.url+"/restaurants/"+name+"/orders/"+order, 
            function(result)
            {
                if (result.error) 
                {
                    if (typeof(callback) != "undefined")
                    {
                        context[callback](undefined, result.obj);
                    }
                    else
                    {
                        context(undefined, result.obj);
                    }
                }
                else
                {
                    if (typeof(callback) != "undefined")
                    {
                        context[callback](OrderFromObj(result.obj.value));
                    }
                    else
                    {
                        context(OrderFromObj(result.obj.value));
                    }
                }
            });
}

SpiceClient.prototype.get = function(url, callback)
{
    dojo.xhrGet( {
        url: url,
        handleAs: "json",
        headers: {'Accept':'application/json'},
        timeout: 120000,
        load: function(response, ioArgs) {
            if (!response)
            {
                callback({error: true, obj: ioArgs.xhr});
                return response;
            }
            
            if (typeof(response.error) == "undefined")
            {
                callback({error: false, obj: response});
            }
            else
            {
                var obj = {};
                obj.responseText = ioArgs.xhr.responseText;
                obj.status = convertErrorToStatus(response.error);
                callback({error: true, obj: obj});
            }

            return response;
        },
        error: function(response, ioArgs) {
            callback({error: true, obj: ioArgs.xhr});
            return response;
        },
        sync: false,
        preventCache: false
    });
}
