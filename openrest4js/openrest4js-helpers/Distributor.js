Distributor.prototype.getLogoUrl = function(width)
{
    if (typeof(width) == "undefined")
    {
        return spiceApiUrl+"/distributors/"+this.id+"/picture";
    }
    
    if (this.picture)
    {
        return this.picture+"=s"+parseInt(width);
    }
    else
    {
        return this.getNoImageUrl(width);
    }
}

Distributor.prototype.getIconUrl = function()
{
    return spiceApiUrl+"/distributors/"+this.id+"/picture?type=icon";
}

Distributor.prototype.getNoImageUrl = function(width)
{
    if (typeof(width) == "undefined")
    {
        return spiceApiUrl+"/distributors/"+this.id+"/picture?type=no_image";
    }

    if (this.noImagePicture)
    {
        return this.noImagePicture+"=s"+parseInt(width);
    }
    else
    {
        return getStaticUrl("NoImage.png");
    }
}
