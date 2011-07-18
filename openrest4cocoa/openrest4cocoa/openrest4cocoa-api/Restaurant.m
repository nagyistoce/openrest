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

@synthesize restaurantId;
@synthesize restaurantName;
@synthesize restaurantDescription;
@synthesize contact;
@synthesize address;
@synthesize welcomeMessage;
@synthesize confirmationMessage;
@synthesize colorScheme;
@synthesize openTimes;
@synthesize deliveryTimes;
@synthesize inactive;
@synthesize status;
@synthesize deliveryStatus;
@synthesize openTimesStr;
@synthesize deliveryTimesStr;
@synthesize timezone;
@synthesize paymentTypes;
@synthesize minPayments;
@synthesize link;
@synthesize picture;
@synthesize icon;
@synthesize deliveryInfos;
@synthesize properties;

-(id)init
{
    if ((self = [super init]))
    {
        // Setup defaults
        [self setOpenTimes:[[[OpenrestAvailability alloc] init] autorelease]];
        [self setDeliveryTimes:[[[OpenrestAvailability alloc] init] autorelease]];
        [self setInactive:false];
        [self setDeliveryInfos:[NSArray array]];
        [self setPaymentTypes:[NSArray array]];
        [self setMinPayments:[NSDictionary dictionary]];
        [self setProperties:[NSDictionary dictionary]];
    }
    
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [self init]))
    {
        [self setRestaurantId:[data valueForKey:@"id"]];
        [self setRestaurantName:[data valueForKey:@"name"]];
        [self setRestaurantDescription:[data valueForKey:@"description"]];
        [self setContact:[[[Contact alloc] initWithDictionary:
                           [data valueForKey:@"contact"]] autorelease]];
        [self setAddress:[[[Address alloc] initWithDictionary:
                           [data valueForKey:@"address"]] autorelease]];
        [self setWelcomeMessage:[data valueForKey:@"welcomeMessage"]];
        [self setConfirmationMessage:[data valueForKey:@"confirmationMessage"]];
        [self setColorScheme:[[[ColorScheme alloc] initWithDictionary:
                           [data valueForKey:@"colorScheme"]] autorelease]];
        if ([data valueForKey:@"openTimes"] != nil)
        {
            [self setOpenTimes:[[[OpenrestAvailability alloc] initWithDictionary:
                                 [data valueForKey:@"openTimes"]] autorelease]];
        }
        if ([data valueForKey:@"deliveryTimes"] != nil)
        {
            [self setOpenTimes:[[[OpenrestAvailability alloc] initWithDictionary:
                                 [data valueForKey:@"openTimes"]] autorelease]];
        }
        if ([data valueForKey:@"inactive"] != nil)
        {
            [self setInactive:([[data valueForKey:@"inactive"] intValue] == 1)];
        }
        [self setStatus:[[[Status alloc] initWithDictionary:
                          [data valueForKey:@"status"]] autorelease]];
        [self setDeliveryStatus:[[[Status alloc] initWithDictionary:
                                  [data valueForKey:@"deliveryStatus"]] autorelease]];
        [self setOpenTimesStr:[data valueForKey:@"openTimesStr"]];
        [self setDeliveryTimesStr:[data valueForKey:@"deliveryTimesStr"]];
        [self setTimezone:[data valueForKey:@"timezone"]];
        if ([data valueForKey:@"paymentTypes"] != nil)
        {
            [self setPaymentTypes:[data valueForKey:@"paymentTypes"]];
        }
        if ([data valueForKey:@"minPayments"] != nil)
        {
            [self setMinPayments:[data valueForKey:@"minPayments"]];
        }
        [self setLink:[data valueForKey:@"link"]];
        [self setPicture:[data valueForKey:@"picture"]];
        [self setIcon:[data valueForKey:@"icon"]];
        if ([data valueForKey:@"properties"] != nil)
        {        
            [self setProperties:[data valueForKey:@"properties"]];
        }
        
        if ([data valueForKey:@"deliveryInfos"] != nil)
        {
            [self setDeliveryInfos:[Utils refactorJsonArray:[data valueForKey:@"deliveryInfos"]
                                                    toClass:@"DeliveryInfo"]];
        }
        
    }
    
    return self;
}

-(NSString *)description
{
    NSMutableString* ret = [NSMutableString stringWithCapacity:0];
    [ret appendFormat:@"\n%@ (%@)\n", restaurantId, restaurantName];
    [ret appendFormat:@"   - RestaurantDescription: %@\n", restaurantDescription];
    [ret appendFormat:@"   - Contact: %@\n", contact];
    [ret appendFormat:@"   - Address: %@\n", address];
    [ret appendFormat:@"   - WelcomeMessage: %@\n", welcomeMessage];
    [ret appendFormat:@"   - ConfirmationMessage: %@\n", confirmationMessage];
    [ret appendFormat:@"   - ColorScheme: %@\n", colorScheme];
    [ret appendFormat:@"   - OpenTimes: %@\n", openTimes];
    [ret appendFormat:@"   - DeliveryTimes: %@\n", deliveryTimes];
    [ret appendFormat:@"   - Inactive: %d\n", inactive];
    [ret appendFormat:@"   - Status: %@\n", status];
    [ret appendFormat:@"   - DeliveryStatus: %@\n", deliveryStatus];
    [ret appendFormat:@"   - OpenTimesStr: %@\n", openTimesStr];
    [ret appendFormat:@"   - DeliveryTimesStr: %@\n", deliveryTimesStr];
    [ret appendFormat:@"   - Timezone: %@\n", timezone];
    [ret appendFormat:@"   - PaymentTypes: %@\n", paymentTypes];
    [ret appendFormat:@"   - MinPayments: %@\n", minPayments];
    [ret appendFormat:@"   - Link: %@\n", link];
    [ret appendFormat:@"   - Picture: %@\n", picture];
    [ret appendFormat:@"   - Icon: %@\n", icon];
    [ret appendFormat:@"   - DeliveryInfos: %@\n", deliveryInfos];
    [ret appendFormat:@"   - Properties: %@\n", properties];
    return ret;
}

-(void)dealloc
{
    [restaurantId release];
    [restaurantName release];
    [restaurantDescription release];
    [contact release];
    [address release];
    [welcomeMessage release];
    [confirmationMessage release];
    [colorScheme release];
    [openTimes release];
    [deliveryTimes release];
    [status release];
    [deliveryStatus release];
    [openTimesStr release];
    [deliveryTimesStr release];
    [timezone release];
    [paymentTypes release];
    [minPayments release];
    [link release];
    [picture release];
    [icon release];
    [properties release];
    [deliveryInfos release];
    [super dealloc];
}

@end
