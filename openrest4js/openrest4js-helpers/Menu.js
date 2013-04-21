openrest = openrest || {};

openrest.MenuHelper = openrest.MenuHelper || (function() {
	var self = {};

    self.getTagMap = function(params)
    {
        var menu = params.menu;
        var tags = menu.tags || [];
        var tagMap = {};

        for (var i = 0, l = tags.length ; i < l ; i++)
        {
            var tag = tags[i];
            tagMap[tag.id] = tag;
        }

        return tagMap;
    };

	return self;
}());

