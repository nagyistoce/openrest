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
        [self setLat:[[data valueForKey:@"lat"] doubleValue]];
        [self setLng:[[data valueForKey:@"lng"] doubleValue]];
    }
    return self;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    [ret setValue:[NSNumber numberWithDouble:lat] forKey:@"lat"];
    [ret setValue:[NSNumber numberWithDouble:lng] forKey:@"lng"];

    return ret;
}

@end
