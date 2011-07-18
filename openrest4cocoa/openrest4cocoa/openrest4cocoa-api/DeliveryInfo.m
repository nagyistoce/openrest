//
//  DeliveryInfo.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "DeliveryInfo.h"


@implementation DeliveryInfo

@synthesize type;
@synthesize area;
@synthesize minOrderPrice;
@synthesize charge;
@synthesize delayMins;
@synthesize inactive;

-(id)init
{
    if ((self = [super init]))
    {
        [self setMinOrderPrice:0];
        [self setCharge:0];
        [self setDelayMins:0];
        [self setInactive:false];
    }
    return self;
}

-(id)initWithDictionary:(NSDictionary *)data
{
    if ((self = [self init]))
    {
        [self setType:[data valueForKey:@"type"]];
        if ([data valueForKey:@"area"])
        {
            [self setArea:[[[Area alloc] initWithDictionary:
                            [data valueForKey:@"area"]] autorelease]];
        }
        if ([data valueForKey:@"minOrderPrice"])
        {
            [self setMinOrderPrice:[[data valueForKey:@"minOrderPrice"] intValue]];
        }
        if ([data valueForKey:@"charge"])
        {
            [self setCharge:[[data valueForKey:@"charge"] intValue]];
        }
        if ([data valueForKey:@"delayMins"])
        {
            [self setDelayMins:[[data valueForKey:@"delayMins"] intValue]];
        }
        if ([data valueForKey:@"inactive"])
        {
            [self setInactive:([[data valueForKey:@"inactive"] intValue] == 1)];
        }        
    }
    return self;
}

-(NSString *)description
{
    return [NSString stringWithFormat:@"DeliveryInfo(%@): Area: %@ min: %d charge: %d, delay: %d, inactive: %d", type, area, minOrderPrice, charge, delayMins, inactive];
}

-(void)dealloc
{
    [type release];
    [area release];
    [super dealloc];
}
@end
