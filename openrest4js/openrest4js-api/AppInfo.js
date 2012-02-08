OPENREST_APPINFO_PLATFORM_ANDROID = "android";
OPENREST_APPINFO_PLATFORM_IOS = "ios";
OPENREST_APPINFO_APP_TYPE_CLIENT = "client";
OPENREST_APPINFO_APP_TYPE_EMPLOYEE = "employee";

function AppInfoFromObj(data)
{
    return new AppInfo(data.type, data.platform, data.id, data.version, data.link);
}

function AppInfo(type, platform, id, version, link)
{
    this.type = type;
    this.platform = platform;
    this.id = id;
    this.version = version;
    this.link = link;
}

AppInfo.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (typeof(this.type) != "undefined") ret['type'] = this.type;
    if (typeof(this.platform) != "undefined") ret['platform'] = this.platform;
    if (typeof(this.id) != "undefined") ret['id'] = this.id;
    if (typeof(this.version) != "undefined") ret['version'] = this.version;
    if (typeof(this.link) != "undefined") ret['link'] = this.link;

    return ret;
}
