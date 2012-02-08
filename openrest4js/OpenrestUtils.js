function ORJSArrayFromJsonArray(array, constructor)
{
    var ret = [];
    for (var i in array)
    {
        ret.push(constructor(array[i]));
    }

    return ret;
}

function ORJsonArrayFromJSArray(array)
{
    var ret = [];
    for (var i in array)
    {
        ret.push(array[i].toOpenRestObj());
    }

    return ret;
}

function ORDeserializeProperties(properties)
{
    var ret = {};
    if (properties)
    {
        for (var i in properties)
        {
            ret[i] = dojo.fromJson(this.properties[i]);
        }
    }

    return ret;
}

function ORSerializeProperties(properties)
{
    var ret = {};
    if (properties)
    {
        for (var i in properties)
        {
            ret[i] = dojo.toJson(this.properties[i]);
        }
    }

    return ret;
}
