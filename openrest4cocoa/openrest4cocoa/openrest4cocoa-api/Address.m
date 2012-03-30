//
//  Address.m
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Address.h"


@implementation Address

@synthesize country;
@synthesize city;
@synthesize street;
@synthesize number;
@synthesize apt;
@synthesize floor;
@synthesize entrance;
@synthesize comment;
@synthesize latLng;
@synthesize countryCode;

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        [self setCountry:[data valueForKey:@"country"]];
        [self setCity:[data valueForKey:@"city"]];
        [self setStreet:[data valueForKey:@"street"]];
        [self setNumber:[data valueForKey:@"number"]];
        [self setApt:[data valueForKey:@"apt"]];
        [self setFloor:[data valueForKey:@"floor"]];
        [self setEntrance:[data valueForKey:@"entrance"]];
        [self setComment:[data valueForKey:@"comment"]];
        [self setCountryCode:[data valueForKey:@"countryCode"]];
        
        if ([data valueForKey:@"latLng"] != nil)
        {
            [self setLatLng:[[[LatLng alloc] initWithDictionary:
                              [data valueForKey:@"latLng"]] autorelease]];
        }
    }
    return self;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];

    if (country != nil) {[ret setValue:country forKey:@"country"];}
    if (city != nil) {[ret setValue:city forKey:@"city"];}
    if (street != nil) {[ret setValue:street forKey:@"street"];}
    if (number != nil) {[ret setValue:number forKey:@"number"];}
    if (apt != nil) {[ret setValue:apt forKey:@"apt"];}
    if (floor != nil) {[ret setValue:floor forKey:@"floor"];}
    if (entrance != nil) {[ret setValue:entrance forKey:@"entrance"];}
    if (comment != nil) {[ret setValue:comment forKey:@"comment"];}
    if (latLng != nil) {[ret setValue:latLng forKey:@"latLng"];}
    if (countryCode != nil) {[ret setValue:countryCode forKey:@"countryCode"];}
    
    return ret;
}

-(NSString*)description
{
    NSMutableString* string = [NSMutableString stringWithCapacity:0];
    [string appendFormat:@"%@ %@, %@", street, number, city];
    if ((apt) && ([apt length] > 0)) { [string appendFormat:@" דירה: %@", apt]; }
    if ((floor) && ([floor length] > 0)) { [string appendFormat:@" קומה: %@", floor]; }
    if ((entrance) && ([entrance length] > 0)) { [string appendFormat:@" כניסה: %@", entrance]; }
    if ((comment) && ([comment length] > 0)) { [string appendFormat:@" הערה: %@", comment]; }
    return string;
}

-(NSUInteger)hash
{
    return [[self description] hash];
}

-(BOOL)isEqual:(id)object
{
    if (self == object) return TRUE;
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[self class]]) return FALSE;
    Address* other = (Address*)object;
    
    if (![country isEqualToString:other.country]) return FALSE;
    if (![city isEqualToString:other.city]) return FALSE;
    if (![street isEqualToString:other.street]) return FALSE;
    if (![number isEqualToString:other.number]) return FALSE;
    
    if (([apt length] + [other.apt length] == 0) || ([apt isEqualToString:other.apt]))
        if (([floor length] + [other.floor length] == 0) || ([floor isEqualToString:other.floor]))
            if (([entrance length] + [other.entrance length] == 0) || ([entrance isEqualToString:other.entrance]))
                if (([comment length] + [other.comment length] == 0) || ([comment isEqualToString:other.comment]))
                    return TRUE;
    
    return FALSE;
}


-(void)dealloc
{
    [city release];
    [street release];
    [number release];
    [apt release];
    [floor release];
    [entrance release];
    [comment release];
    [latLng release];
    [super dealloc];
}
@end
