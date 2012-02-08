function DistributorFromObj(element)
{
    return new Distributor(element.id, element.title, element.description, element.contact, element.address,
            element.locale, element.locales, element.link, element.picture, element.icon,
            element.properties, element.facebookAppId, element.domain, element.noImagePicture);
}

function Distributor(id, title, description, contact, address, locale, locales, 
        link, picture, icon, properties, facebookAppId, domain, noImagePicture)
{
    this.id = element.id;
    this.title = dojo.fromJson(dojo.toJson(element.title));
    this.description = dojo.fromJson(dojo.toJson(element.description));
    this.contact = dojo.fromJson(dojo.toJson(element.contact));
    this.address = dojo.fromJson(dojo.toJson(element.address));
    this.locale = element.locale;
    this.locales = dojo.fromJson(dojo.toJson(element.locales));
    this.link = element.link;
    this.picture = element.picture;
    this.icon = element.icon;
    this.properties = dojo.fromJson(dojo.toJson(element.properties));
    this.facebookAppId = element.facebookAppId;
    this.domain = element.domain;
    this.noImagePicture = element.noImagePicture;

    if (!this.properties)
    {
        this.properties = {};
    }
}

Distributor.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (this.id) ret['id'] = this.id;
    if (this.title) ret['title'] = this.title;
    if (this.description) ret['description'] = this.description;
    if (this.contact) ret['contact'] = this.contact;
    if (this.address) ret['address'] = this.address;
    if (this.locale) ret['locale'] = this.locale;
    if (this.locales) ret['locales'] = this.locales;
    if (this.link) ret['link'] = this.link;
    if (this.properties) ret['properties'] = this.properties;
    if (this.facebookAppId) ret['facebookAppId'] = this.facebookAppId;
    if (this.domain) ret['domain'] = this.domain;
    if (this.noImagePicture) ret['noImagePicture'] = this.noImagePicture;

    return ret;
}
