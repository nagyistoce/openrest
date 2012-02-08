OPENREST_STATUS_STATUS_AVAILABLE = "available";
OPENREST_STATUS_STATUS_UNAVAILABLE = "unavailable";

function StatusFromObj(data)
{
    return new Status(data.status, data.until);
}

function Status(status, until) 
{
    this.status = status;
    this.until = until;
}

Status.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (this.status) ret['status'] = this.status;
    if (this.until) ret['until'] = this.until;
    
    return ret;
}
