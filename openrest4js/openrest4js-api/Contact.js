function ContactFromObj(data)
{
    return new Contact(data.firstName, data.lastName, data.email, data.phone, data.fax);
}

function Contact(firstName, lastName, email, phone, fax) 
{
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.fax = fax;
}

Contact.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (this.firstName) ret['firstName'] = this.firstName;
    if (this.lastName) ret['lastName'] = this.lastName;
    if (this.email) ret['email'] = this.email;
    if (this.phone) ret['phone'] = this.phone;
    if (this.fax) ret['fax'] = this.fax;

    return ret;
}
