//
//  Charge.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 7/12/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Charge.h"


@implementation Charge

@synthesize chargeId;
@synthesize restaurantId;
@synthesize type;
@synthesize priority;
@synthesize code;
@synthesize tagId;
@synthesize tagMode;
@synthesize amountRuleType;
@synthesize amountRule;
@synthesize coupon;
@synthesize amount;

-(id)init
{
    if ((self = [super init]))
    {
        [self setPriority:[NSNumber numberWithFloat:0.0]];
        [self setTagMode:TAG_MODE_INCLUDE];
        [self setAmountRuleType:CHARGE_AMOUNT_RULE_TYPE_VARIABLE];
    }
    return self;
}

-(id)initWithDictionary:(NSDictionary *)data
{
    if ((self = [self init]))
    {
        [self setChargeId:[data valueForKey:@"id"]];
        [self setRestaurantId:[data valueForKey:@"restaurantId"]];
        [self setType:[data valueForKey:@"type"]];
        [self setPriority:[data valueForKey:@"priority"]];
        [self setCode:[data valueForKey:@"code"]];
        [self setTagId:[data valueForKey:@"tagId"]];
        [self setTagMode:[data valueForKey:@"tagMode"]];
        [self setAmountRuleType:[data valueForKey:@"amountRuleType"]];
        [self setAmountRule:[data valueForKey:@"amountRule"]];
        [self setCoupon:[[[Coupon alloc] initWithDictionary:
                                  [data valueForKey:@"coupon"]] autorelease]];
        [self setAmount:[data valueForKey:@"amount"]];
    }
    
    return self;
}

-(NSDictionary*)proxyForJson
{   
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    if (type != nil) {[ret setValue:type forKey:@"type"];}
    if (chargeId != nil) {[ret setValue:chargeId forKey:@"id"];}
    if (restaurantId != nil) {[ret setValue:restaurantId forKey:@"restaurantId"];}
    if (type != nil) {[ret setValue:type forKey:@"type"];}
    if (priority != nil) {[ret setValue:priority forKey:@"priority"];}
    if (code != nil) {[ret setValue:code forKey:@"code"];}
    if (tagId != nil) {[ret setValue:tagId forKey:@"tagId"];}
    if (tagMode != nil) {[ret setValue:tagMode forKey:@"tagMode"];}
    if (amountRuleType != nil) {[ret setValue:amountRuleType forKey:@"amountRuleType"];}
    if (amountRule != nil) {[ret setValue:amountRule forKey:@"amountRule"];}
    if (coupon != nil) {[ret setValue:coupon forKey:@"coupon"];}
    if (amount != nil) {[ret setValue:amount forKey:@"amount"];}
    
    return ret;
}

-(NSString*)getTitle
{
    if (([type isEqualToString:CHARGE_TYPE_COUPON]) || ([type isEqualToString:CHARGE_TYPE_CLUB_COUPON]))
    {
        return [coupon title];
    }
    return @"";
}

-(NSString*)getDescription
{
    if (([type isEqualToString:CHARGE_TYPE_COUPON]) || ([type isEqualToString:CHARGE_TYPE_CLUB_COUPON]))
    {
        return [coupon description];
    }
    return @"";    
}

-(void)dealloc
{
    [chargeId release];
    [restaurantId release];
    [type release];
    [priority release];
    [code release];
    [tagId release];
    [tagMode release];
    [amountRuleType release];
    [amountRule release];
    [coupon release];
    [amount release];
    [super dealloc];
}
@end
