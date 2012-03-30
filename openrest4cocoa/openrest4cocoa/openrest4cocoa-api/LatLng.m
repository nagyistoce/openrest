//
//  LatLng.m
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "LatLng.h"


@implementation LatLng

@synthesize lat;
@synthesize lng;

-(id)initWithDictionary:(NSDictionary *)data
{
    if ((self = [super init]))
    {
        [self setLat:[data valueForKey:@"lat"]];
        [self setLng:[data valueForKey:@"lng"]];
    }
    return self;
}

-(void)dealloc
{
    [lat release];
    [lng release];
    [super dealloc];
}

-(BOOL)isEqual:(id)object
{
    if (self == object) return TRUE;
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[self class]]) return FALSE;
    LatLng* other = (LatLng*)object;
    
    if (![other.lat isEqual:lat]) return FALSE;
    if (![other.lng isEqual:lng]) return FALSE;
    
    return TRUE;
}

-(NSUInteger)hash
{
    return [lat doubleValue]+[lng doubleValue];
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    [ret setValue:lat forKey:@"lat"];
    [ret setValue:lng forKey:@"lng"];

    return ret;
}

@end
