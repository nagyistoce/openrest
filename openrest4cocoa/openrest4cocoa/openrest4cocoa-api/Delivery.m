//
//  Delivery.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Delivery.h"


@implementation Delivery

@synthesize type;
@synthesize address;
@synthesize charge;

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        [self setCharge:nil];
        [self setType:nil];
        [self setAddress:nil];
        
        [self setType:[data valueForKey:@"type"]];
        if ([data valueForKey:@"address"] != nil)
        {
            [self setAddress:[[[Address alloc] initWithDictionary:
                               [data valueForKey:@"address"]] autorelease]];
        }
        if ([data valueForKey:@"charge"])
        {
            [self setCharge:[data valueForKey:@"charge"]];
        }
    }
    return self;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    if (type != nil) { [ret setValue:type forKey:@"type"]; }
    if (address != nil) { [ret setValue:address forKey:@"address"]; }
    if (charge != nil) { [ret setValue:charge forKey:@"charge"]; }
    return ret;
}

-(void)dealloc
{
    [type release];
    [address release];
    [charge release];
    [super dealloc];
}

@end
