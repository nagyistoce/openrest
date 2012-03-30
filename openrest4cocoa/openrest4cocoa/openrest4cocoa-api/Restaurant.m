//
//  Restaurant.m
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Restaurant.h"
#import "DeliveryInfo.h"
#import "Utils.h"

@implementation Restaurant

@synthesize messages;
@synthesize openTimes;
@synthesize deliveryTimes;
@synthesize inactive;
@synthesize paymentTypes;
@synthesize minPayments;
@synthesize deliveryInfos;
@synthesize currency;
@synthesize rank;
@synthesize distributorId;

-(id)init
{
    if ((self = [super init]))
    {
        // Setup defaults
        [self setOpenTimes:[[[OpenrestAvailability alloc] init] autorelease]];
        [self setDeliveryTimes:[[[OpenrestAvailability alloc] init] autorelease]];
        [self setInactive:false];
        [self setDeliveryInfos:[NSSet set]];
        [self setPaymentTypes:[NSSet set]];
        [self setMinPayments:[NSDictionary dictionary]];
        [self setMessages:[NSDictionary dictionary]];
    }
    
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super initWithDictionary:data]))
    {
        [self setMessages:[data valueForKey:@"messages"]];
        if ([data valueForKey:@"openTimes"] != nil)
        {
            [self setOpenTimes:[[[OpenrestAvailability alloc] initWithDictionary:
                                 [data valueForKey:@"openTimes"]] autorelease]];
        }
        if ([data valueForKey:@"deliveryTimes"] != nil)
        {
            [self setDeliveryTimes:[[[OpenrestAvailability alloc] initWithDictionary:
                                 [data valueForKey:@"deliveryTimes"]] autorelease]];
        }
        if ([data valueForKey:@"inactive"] != nil)
        {
            [self setInactive:([[data valueForKey:@"inactive"] intValue] == 1)];
        }
        if ([data valueForKey:@"paymentTypes"] != nil)
        {
            [self setPaymentTypes:[NSSet setWithArray:[data valueForKey:@"paymentTypes"]]];
        }
        if ([data valueForKey:@"minPayments"] != nil)
        {
            [self setMinPayments:[data valueForKey:@"minPayments"]];
        }       
        if ([data valueForKey:@"deliveryInfos"] != nil)
        {
            [self setDeliveryInfos:[Utils refactorJsonArrayToSet:[data valueForKey:@"deliveryInfos"]
                                                    toClass:@"DeliveryInfo"]];
        }
        [self setCurrency:[data valueForKey:@"currency"]];        
        [self setRank:[data valueForKey:@"rank"]];        
        [self setDistributorId:[data valueForKey:@"distributorId"]];        
    }
    
    return self;
}

-(NSString *)description
{
    NSMutableString* ret = (NSMutableString*)[super description];
 
    [ret appendFormat:@"   - Messages: %@\n", messages];
    [ret appendFormat:@"   - OpenTimes: %@\n", openTimes];
    [ret appendFormat:@"   - DeliveryTimes: %@\n", deliveryTimes];
    [ret appendFormat:@"   - Inactive: %d\n", inactive];
    [ret appendFormat:@"   - PaymentTypes: %@\n", paymentTypes];
    [ret appendFormat:@"   - MinPayments: %@\n", minPayments];
    [ret appendFormat:@"   - DeliveryInfos: %@\n", deliveryInfos];
    [ret appendFormat:@"   - Rank: %@\n", rank];
    [ret appendFormat:@"   - DistributorId: %@\n", distributorId];
    return ret;
}

-(void)dealloc
{
    self.messages = nil;
    self.openTimes = nil;
    self.deliveryTimes = nil;
    self.inactive = nil;
    self.paymentTypes = nil;
    self.minPayments = nil;
    self.deliveryInfos = nil;
    self.currency = nil;
    self.rank = nil;
    self.distributorId = nil;

    [super dealloc];
}

-(BOOL)isEqual:(id)object
{
    if (self == object) return TRUE;
    
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[Restaurant class]]) return FALSE;
    if (![super isEqual:object]) return FALSE;
        
    Restaurant* other = (Restaurant*)object;
    
    if (![messages isEqualToDictionary:other.messages]) return FALSE;
    if (![openTimes isEqual:other.openTimes]) return FALSE;
    if (![deliveryTimes isEqual:other.deliveryTimes]) return FALSE;;
    if (inactive != other.inactive) return FALSE;;
    if (![paymentTypes isEqualToSet:other.paymentTypes]) return FALSE;
    if (![minPayments isEqualToDictionary:other.minPayments]) return FALSE;
    if (![deliveryInfos isEqualToSet:other.deliveryInfos]) return FALSE;
    if (![currency isEqualToString:other.currency]) return FALSE;
    if (![rank isEqualToNumber:other.rank]) return FALSE;
    if (![distributorId isEqualToString:other.distributorId]) return FALSE;
    
    return TRUE;
}


-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = (NSMutableDictionary*)[super proxyForJson];
    
    if (messages != nil) {[ret setValue:messages forKey:@"messages"];}
    if (openTimes != nil) {[ret setValue:openTimes forKey:@"openTimes"];}
    if (deliveryTimes != nil) {[ret setValue:deliveryTimes forKey:@"deliveryTimes"];}
    [ret setValue:[NSNumber numberWithBool:inactive] forKey:@"inactive"];
    if (paymentTypes != nil) {[ret setValue:paymentTypes forKey:@"paymentTypes"];}
    if (minPayments != nil) {[ret setValue:minPayments forKey:@"minPayments"];}
    if (deliveryInfos != nil) {[ret setValue:deliveryInfos forKey:@"deliveryInfos"];}
    if (currency != nil) {[ret setValue:currency forKey:@"currency"];}
    if (rank != nil) {[ret setValue:rank forKey:@"rank"];}
    if (distributorId != nil) {[ret setValue:distributorId forKey:@"distributorId"];}

    return ret;
}

@end
