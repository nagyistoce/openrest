function RestaurantFromObj(data)
{
    return new Restaurant(
            data.id, data.created, data.modified, data.distributorId, data.title, data.description, data.contact ? ContactFromObj(data.contact) : undefined, 
            data.address ? AddressFromObj(data.address) : undefined, data.messages, data.colorScheme ? ColorSchemeFromObj(data.colorScheme) : undefined, 
            data.openTime ? AvailabilityFromObj(data.openTimes) : undefined, data.deliveryTimes ? AvailabilityFromObj(data.deliveryTimes) : undefined, data.inactive, 
            ORJSArrayFromJsonArray(data.deliveryInfos, DeliveryInfoFromObj), data.timezone, data.currency, data.locale, data.locales, data.paymentTypes, 
            data.minPayments, data.link, data.domain, data.altDomains, data.picture, data.icon, ORJSArrayFromJsonArray(data.apps, AppInfoFromObj), data.properties, 
            data.state, data.labels, data.externalIds, data.rank); 
}

function Restaurant(id, created, modified, distributorId, title, description, contact, address, 
        messages, colorScheme, openTimes, deliveryTimes, inactive, deliveryInfos, 
        timezone, currency, locale, locales, paymentTypes, minPayments, link, domain, altDomains, picture, 
        icon, apps, properties, state, labels, externalIds, rank) 
{ 
    this.id = id;
    this.created = created;
    this.modified = modified;
    this.distributorId = distributorId;
    this.title = title;
    this.description = description;
    this.contact = contact;
    this.address = address;
    this.messages = messages;
    this.colorScheme = colorScheme;
    this.openTimes = openTimes;
    this.deliveryTimes = deliveryTimes;
    this.inactive = inactive;
    this.deliveryInfos = deliveryInfos;
    this.timezone = timezone;
    this.currency = currency;
    this.locale = locale;
    this.locales = locales;
    this.paymentTypes = paymentTypes;
    this.minPayments = minPayments;
    this.link = link;
    this.domain = domain;
    this.altDomains = altDomains;
    this.picture = picture;
    this.icon = icon;
    this.apps = apps;
    this.properties = properties;
    this.state = state;
    this.labels = labels;
    this.externalIds = externalIds;
    this.rank = rank;
}

Restaurant.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (this.id) ret['id'] = this.id;
    if (this.created) ret['created'] = this.created;
    if (this.modified) ret['modified'] = this.modified;
    if (this.distributorId) ret['distributorId'] = this.distributorId;
    if (this.title) ret['title'] = this.title;
    if (this.description) ret['description'] = this.description;
    if (this.contact) ret['contact'] = this.contact;
    if (this.address) ret['address'] = this.address;
    if (this.messages) ret['messages'] = this.messages;
    if (this.colorScheme) ret['colorScheme'] = this.colorScheme;
    if (this.openTimes) ret['openTimes'] = this.openTimes;
    if (this.deliveryTimes) ret['deliveryTimes'] = this.deliveryTimes;
    if (this.inactive) ret['inactive'] = this.inactive;
    if (this.deliveryInfos) ret['deliveryInfos'] = this.deliveryInfos;
    if (this.timezone) ret['timezone'] = this.timezone;
    if (this.currency) ret['currency'] = this.currency;
    if (this.locale) ret['locale'] = this.locale;
    if (this.locales) ret['locales'] = this.locales;
    if (this.paymentTypes) ret['paymentTypes'] = this.paymentTypes;
    if (this.minPayments) ret['minPayments'] = this.minPayments;
    if (this.link) ret['link'] = this.link;
    if (this.domain) ret['domain'] = this.domain;
    if (this.altDomains) ret['altDomains'] = this.altDomains;
    if (this.picture) ret['picture'] = this.picture;
    if (this.icon) ret['icon'] = this.icon;
    if (this.apps) ret['apps'] = this.apps;
    if (this.properties) ret['properties'] = this.properties;
    if (this.state) ret['state'] = this.state;
    if (this.labels) ret['labels'] = this.labels;
    if (this.externalIds) ret['externalIds'] = this.externalIds;
    if (this.rank) ret['rank'] = this.rank;

    return ret;
}
