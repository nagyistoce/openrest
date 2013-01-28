var openrest = openrest || {};

openrest.DistributorHelper = openrest.DistributorHelper || (function() {
	var self = {};

    self.getLogoUrl = function(params)
    {
        var distributor = params.distributor;
        var width = params.width;

        if (typeof(width) == "undefined")
        {
            return spiceApiUrl+"/distributors/"+distributor.id+"/picture";
        }

        if (distributor.picture)
        {
            return distributor.picture+"=s"+parseInt(width);
        }
        else
        {
            return distributor.getNoImageUrl(width);
        }
    }

    self.getIconUrl = function(params)
    {
        var distributor = params.distributor;

        return spiceApiUrl+"/distributors/"+distributor.id+"/picture?type=icon";
    }

    self.getNoImageUrl = function(params)
    {
        var distributor = params.distributor;
        var width = params.width;
        var defaultNoImage = params.defaultNoImage;

        if (typeof(width) == "undefined")
        {
            return spiceApiUrl+"/distributors/"+distributor.id+"/picture?type=no_image";
        }

        if (distributor.noImagePicture)
        {
            return distributor.noImagePicture+"=s"+parseInt(width);
        }
        else
        {
            return defaultNoImage;
        }
    }

	return self;
}());

