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

-(BOOL)isEqual:(id)object
{
    if (self == object) return TRUE;
    
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[self class]]) return FALSE;
    
    
    DeliveryInfo* other = (DeliveryInfo*)object;

    if (![type isEqualToString:other.type]) return FALSE;
    if ((area != other.area) && (![area isEqual:other.area])) return FALSE;
    if (minOrderPrice != other.minOrderPrice) return FALSE;
    if (charge != other.charge) return FALSE;
    if (delayMins != other.delayMins) return FALSE;
    if (inactive != other.inactive) return FALSE;
    
    return TRUE;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    if (type != nil) {[ret setValue:type forKey:@"type"];}
    if (area != nil) {[ret setValue:area forKey:@"area"];}
    [ret setValue:[NSNumber numberWithInt:minOrderPrice] forKey:@"minOrderPrice"];
    [ret setValue:[NSNumber numberWithInt:charge] forKey:@"charge"];
    [ret setValue:[NSNumber numberWithInt:delayMins] forKey:@"delayMins"];
    [ret setValue:[NSNumber numberWithBool:inactive] forKey:@"inactive"];

    return ret;
}


-(NSUInteger)hash
{
    NSUInteger ret = [type hash] + [area hash] + minOrderPrice + charge + delayMins;
    return ret;
}

-(void)dealloc
{
    [type release];
    [area release];
    [super dealloc];
}
@end
