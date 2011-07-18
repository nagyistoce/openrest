//
//  Address.m
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Address.h"


@implementation Address

@synthesize city;
@synthesize street;
@synthesize number;
@synthesize apt;
@synthesize floor;
@synthesize entrance;
@synthesize comment;
@synthesize latLng;

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        [self setCity:[data valueForKey:@"city"]];
        [self setStreet:[data valueForKey:@"street"]];
        [self setNumber:[data valueForKey:@"number"]];
        [self setApt:[data valueForKey:@"apt"]];
        [self setFloor:[data valueForKey:@"floor"]];
        [self setEntrance:[data valueForKey:@"entrance"]];
        [self setComment:[data valueForKey:@"comment"]];
        
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

    if (city != nil) {[ret setValue:city forKey:@"city"];}
    if (street != nil) {[ret setValue:street forKey:@"street"];}
    if (number != nil) {[ret setValue:number forKey:@"number"];}
    if (apt != nil) {[ret setValue:apt forKey:@"apt"];}
    if (floor != nil) {[ret setValue:floor forKey:@"floor"];}
    if (entrance != nil) {[ret setValue:entrance forKey:@"entrance"];}
    if (comment != nil) {[ret setValue:comment forKey:@"comment"];}
    if (latLng != nil) {[ret setValue:latLng forKey:@"latLng"];}
    
    return ret;
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
