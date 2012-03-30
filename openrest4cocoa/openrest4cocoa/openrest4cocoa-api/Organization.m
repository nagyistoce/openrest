//
//  Restaurant.m
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Organization.h"
#import "DeliveryInfo.h"
#import "Utils.h"

@implementation Organization

@synthesize organizationId;
@synthesize modified;
@synthesize created;
@synthesize title;
@synthesize organizationDescription;
@synthesize contact;
@synthesize address;
@synthesize colorScheme;
@synthesize timezone;
@synthesize link;
@synthesize properties;
@synthesize locales;
@synthesize locale;
@synthesize picture;
@synthesize icon;
@synthesize noImagePicture;
@synthesize apps;

-(id)init
{
    if ((self = [super init]))
    {
        // Setup defaults
        [self setTitle:[LocalizedDictionary dictionary]];
        [self setOrganizationDescription:[LocalizedDictionary dictionary]];
        [self setProperties:[NSDictionary dictionary]];
        [self setLocales:[NSSet set]];
        [self setApps:[NSSet set]];
    }
    
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [self init]))
    {
        [self setOrganizationId:[data valueForKey:@"id"]];
        [self setModified:[data valueForKey:@"modified"]];
        [self setCreated:[data valueForKey:@"created"]];
        [self setTitle:[LocalizedDictionary dictionaryWithDictionary:[data valueForKey:@"title"]]];
        [self setOrganizationDescription:[LocalizedDictionary dictionaryWithDictionary:[data valueForKey:@"description"]]];
        [self setContact:[[[Contact alloc] initWithDictionary:
                           [data valueForKey:@"contact"]] autorelease]];
        [self setAddress:[[[Address alloc] initWithDictionary:
                           [data valueForKey:@"address"]] autorelease]];
        [self setColorScheme:[[[ColorScheme alloc] initWithDictionary:
                               [data valueForKey:@"colorScheme"]] autorelease]];
        [self setTimezone:[data valueForKey:@"timezone"]];
        [self setLink:[data valueForKey:@"link"]];
        if ([data valueForKey:@"properties"] != nil)
        {        
            [self setProperties:[data valueForKey:@"properties"]];
        }
        
        [self setLocales:[NSSet setWithArray:[data valueForKey:@"locales"]]];
        [self setLocale:[data valueForKey:@"locale"]];        
        [self setPicture:[data valueForKey:@"picture"]];
        [self setIcon:[data valueForKey:@"icon"]];
        [self setNoImagePicture:[data valueForKey:@"noImagePicture"]];
        if ([data valueForKey:@"apps"] != nil)
        {
            [self setApps:[Utils refactorJsonArrayToSet:[data valueForKey:@"apps"]
                                                toClass:@"App"]];
        }
    }
    
    return self;
}

-(NSString *)description
{
    NSMutableString* ret = [NSMutableString stringWithCapacity:0];
    [ret appendFormat:@"\n%@ (%@)\n", organizationId, title];
    [ret appendFormat:@"   - Description: %@\n", organizationDescription];
    [ret appendFormat:@"   - Contact: %@\n", contact];
    [ret appendFormat:@"   - Address: %@\n", address];
    [ret appendFormat:@"   - ColorScheme: %@\n", colorScheme];
    [ret appendFormat:@"   - Timezone: %@\n", timezone];
    [ret appendFormat:@"   - Link: %@\n", link];
    [ret appendFormat:@"   - Picture: %@\n", picture];
    [ret appendFormat:@"   - Icon: %@\n", icon];
    [ret appendFormat:@"   - Properties: %@\n", properties];
    return ret;
}

-(void)dealloc
{
    self.organizationId = nil;
    self.modified = nil;
    self.created = nil;
    self.title = nil;
    self.organizationDescription = nil;
    self.contact = nil;
    self.address = nil;
    self.colorScheme = nil;
    self.timezone = nil;
    self.link = nil;
    self.properties = nil;
    self.locales = nil;
    self.locale = nil;
    self.picture = nil;
    self.icon = nil;
    self.noImagePicture = nil;
    self.apps = nil;
    [super dealloc];
}

-(BOOL)isEqual:(id)object
{
    if (self == object) return TRUE;
    
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[Organization class]]) return FALSE;
    
    Organization* other = (Organization*)object;
    
    if (![organizationId isEqualToString:other.organizationId]) return FALSE;
    if (![modified isEqualToNumber:other.modified]) return FALSE;
    if (![created isEqualToNumber:other.created]) return FALSE;
    if (![title isEqual:other.title]) return  FALSE;
    if (![organizationDescription isEqual:other.organizationDescription]) return  FALSE;
    if (![contact isEqual:other.contact]) return FALSE;
    if (![address isEqual:other.address]) return FALSE;
    if (![colorScheme isEqual:other.colorScheme]) return FALSE;
    if (![timezone isEqualToString:other.timezone]) return FALSE;
    if (![link isEqualToString:other.link]) return FALSE;
    if (![picture isEqualToString:other.picture]) return FALSE;
    if (![icon isEqualToString:other.icon]) return FALSE;
    if (![properties isEqualToDictionary:other.properties]) return FALSE;
    if (![noImagePicture isEqualToString:other.noImagePicture]) return FALSE;
    if (![locales isEqualToSet:other.locales]) return FALSE;
    if (![locale isEqualToString:other.locale]) return FALSE;
    if (![apps isEqualToSet:other.apps]) return FALSE;
    
    return TRUE;
}


-(NSMutableDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    if (organizationId != nil) {[ret setValue:organizationId forKey:@"id"];}
    if (modified != nil) {[ret setValue:modified forKey:@"modified"];}
    if (created != nil) {[ret setValue:created forKey:@"created"];}
    if (title != nil) {[ret setValue:title forKey:@"title"];}
    if (organizationDescription != nil) {[ret setValue:organizationDescription forKey:@"description"];}
    if (contact != nil) {[ret setValue:contact forKey:@"contact"];}
    if (address != nil) {[ret setValue:address forKey:@"address"];}
    if (colorScheme != nil) {[ret setValue:colorScheme forKey:@"colorScheme"];}
    if (timezone != nil) {[ret setValue:timezone forKey:@"timezone"];}
    if (link != nil) {[ret setValue:link forKey:@"link"];}
    if (picture != nil) {[ret setValue:picture forKey:@"picture"];}
    if (icon != nil) {[ret setValue:icon forKey:@"icon"];}
    if (noImagePicture != nil) {[ret setValue:noImagePicture forKey:@"noImagePicture"];}
    if (properties != nil) {[ret setValue:properties forKey:@"properties"];}
    if (locales != nil) {[ret setValue:locales forKey:@"locales"];}
    if (locale != nil) {[ret setValue:locale forKey:@"locale"];}
    if (apps != nil) {[ret setValue:apps forKey:@"apps"];}
    return ret;
}

-(NSUInteger)hash
{
    return [organizationId hash]+[modified intValue];
}

@end
