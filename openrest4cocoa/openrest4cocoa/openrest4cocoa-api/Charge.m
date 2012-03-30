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
@synthesize clubId;

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
        [self setClubId:[data valueForKey:@"clubId"]];
        [self setTagId:[data valueForKey:@"tagId"]];
        [self setTagMode:[data valueForKey:@"tagMode"]];
        [self setAmountRuleType:[data valueForKey:@"amountRuleType"]];
        [self setAmountRule:[data valueForKey:@"amountRule"]];
        if (([type isEqualToString:CHARGE_TYPE_COUPON]) || ([type isEqualToString:CHARGE_TYPE_CLUB_COUPON]))
        {
            [self setCoupon:[[[Coupon alloc] initWithDictionary:
                                    [data valueForKey:@"coupon"]] autorelease]];
        }
        [self setAmount:[data valueForKey:@"amount"]];
    }
    
    return self;
}

-(NSDictionary*)proxyForJson
{   
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    if (chargeId != nil) {[ret setValue:chargeId forKey:@"id"];}
    if (restaurantId != nil) {[ret setValue:restaurantId forKey:@"restaurantId"];}
    if (type != nil) {[ret setValue:type forKey:@"type"];}
    if (priority != nil) {[ret setValue:priority forKey:@"priority"];}
    if (code != nil) {[ret setValue:code forKey:@"code"];}
    if (clubId != nil) {[ret setValue:clubId forKey:@"clubId"];}
    if (tagId != nil) {[ret setValue:tagId forKey:@"tagId"];}
    if (tagMode != nil) {[ret setValue:tagMode forKey:@"tagMode"];}
    if (amountRuleType != nil) {[ret setValue:amountRuleType forKey:@"amountRuleType"];}
    if (amountRule != nil) {[ret setValue:amountRule forKey:@"amountRule"];}
    if (coupon != nil) {[ret setValue:coupon forKey:@"coupon"];}
    if (amount != nil) {[ret setValue:amount forKey:@"amount"];}
    
    return ret;
}

-(BOOL)isEqual:(id)object
{
    if (self == object) return TRUE;
    
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[self class]]) return FALSE;
    
    
    Charge* other = (Charge*)object;

    if ((chargeId != other.chargeId) && (![chargeId isEqual:other.chargeId])) return FALSE;
    if ((restaurantId != other.restaurantId) && (![restaurantId isEqual:other.type])) return FALSE;
    if ((type != other.type) && (![type isEqual:other.type])) return FALSE;
    if ((clubId != other.clubId) && (![clubId isEqual:other.clubId])) return FALSE;
    if ((priority != other.priority) && (![priority isEqual:other.priority])) return FALSE;
    if ((tagId != other.tagId) && (![tagId isEqual:other.tagMode])) return FALSE;
    if ((tagMode != other.tagMode) && (![tagMode isEqual:other.type])) return FALSE;
    if ((amountRuleType != other.amountRuleType) && (![amountRuleType isEqual:other.amountRuleType])) return FALSE;
    if ((amountRule != other.amountRule) && (![amountRule isEqual:other.amountRule])) return FALSE;
    if ((coupon != other.coupon) && (![coupon isEqual:other.coupon])) return FALSE;
    
    return TRUE;
}

-(NSUInteger)hash
{
    NSUInteger ret = [chargeId hash] + [restaurantId hash];
    return ret;
}

-(void)dealloc
{
    [chargeId release];
    [restaurantId release];
    [type release];
    [priority release];
    [code release];
    [clubId release];
    [tagId release];
    [tagMode release];
    [amountRuleType release];
    [amountRule release];
    [coupon release];
    [amount release];
    [super dealloc];
}
@end
