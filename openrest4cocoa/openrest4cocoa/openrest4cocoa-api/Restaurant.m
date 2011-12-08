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
@synthesize title;
@synthesize restaurantDescription;
@synthesize contact;
@synthesize address;
@synthesize messages;
@synthesize colorScheme;
@synthesize openTimes;
@synthesize deliveryTimes;
@synthesize inactive;
@synthesize status;
@synthesize deliveryStatus;
@synthesize timezone;
@synthesize paymentTypes;
@synthesize minPayments;
@synthesize link;
@synthesize picture;
@synthesize icon;
@synthesize deliveryInfos;
@synthesize properties;
@synthesize locales;
@synthesize locale;
@synthesize currency;
@synthesize rank;
@synthesize apps;

-(id)init
{
    if ((self = [super init]))
    {
        // Setup defaults
        [self setTitle:[LocalizedDictionary dictionary]];
        [self setRestaurantDescription:[LocalizedDictionary dictionary]];
        [self setOpenTimes:[[[OpenrestAvailability alloc] init] autorelease]];
        [self setDeliveryTimes:[[[OpenrestAvailability alloc] init] autorelease]];
        [self setInactive:false];
        [self setDeliveryInfos:[NSArray array]];
        [self setPaymentTypes:[NSArray array]];
        [self setMinPayments:[NSDictionary dictionary]];
        [self setProperties:[NSDictionary dictionary]];
        [self setLocales:[NSArray array]];
        [self setMessages:[NSDictionary dictionary]];
        [self setApps:[NSArray array]];
    }
    
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [self init]))
    {
        [self setRestaurantId:[data valueForKey:@"id"]];
        [self setTitle:[LocalizedDictionary dictionaryWithDictionary:[data valueForKey:@"title"]]];
        [self setRestaurantDescription:[LocalizedDictionary dictionaryWithDictionary:[data valueForKey:@"description"]]];
        [self setContact:[[[Contact alloc] initWithDictionary:
                           [data valueForKey:@"contact"]] autorelease]];
        [self setAddress:[[[Address alloc] initWithDictionary:
                           [data valueForKey:@"address"]] autorelease]];
        [self setMessages:[data valueForKey:@"messages"]];
        [self setColorScheme:[[[ColorScheme alloc] initWithDictionary:
                           [data valueForKey:@"colorScheme"]] autorelease]];
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
        [self setStatus:[[[Status alloc] initWithDictionary:
                          [data valueForKey:@"status"]] autorelease]];
        [self setDeliveryStatus:[[[Status alloc] initWithDictionary:
                                  [data valueForKey:@"deliveryStatus"]] autorelease]];
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
        [self setLocales:[data valueForKey:@"locales"]];
        [self setLocale:[data valueForKey:@"locale"]];        
        [self setCurrency:[data valueForKey:@"currency"]];        
        [self setRank:[data valueForKey:@"rank"]];        
        if ([data valueForKey:@"apps"] != nil)
        {
            [self setApps:[Utils refactorJsonArray:[data valueForKey:@"apps"]
                                                    toClass:@"App"]];
        }
    }
    
    return self;
}

-(NSString *)description
{
    NSMutableString* ret = [NSMutableString stringWithCapacity:0];
    [ret appendFormat:@"\n%@ (%@)\n", restaurantId, title];
    [ret appendFormat:@"   - RestaurantDescription: %@\n", restaurantDescription];
    [ret appendFormat:@"   - Contact: %@\n", contact];
    [ret appendFormat:@"   - Address: %@\n", address];
    [ret appendFormat:@"   - Messages: %@\n", messages];
    [ret appendFormat:@"   - ColorScheme: %@\n", colorScheme];
    [ret appendFormat:@"   - OpenTimes: %@\n", openTimes];
    [ret appendFormat:@"   - DeliveryTimes: %@\n", deliveryTimes];
    [ret appendFormat:@"   - Inactive: %d\n", inactive];
    [ret appendFormat:@"   - Status: %@\n", status];
    [ret appendFormat:@"   - DeliveryStatus: %@\n", deliveryStatus];
    [ret appendFormat:@"   - Timezone: %@\n", timezone];
    [ret appendFormat:@"   - PaymentTypes: %@\n", paymentTypes];
    [ret appendFormat:@"   - MinPayments: %@\n", minPayments];
    [ret appendFormat:@"   - Link: %@\n", link];
    [ret appendFormat:@"   - Picture: %@\n", picture];
    [ret appendFormat:@"   - Icon: %@\n", icon];
    [ret appendFormat:@"   - DeliveryInfos: %@\n", deliveryInfos];
    [ret appendFormat:@"   - Properties: %@\n", properties];
    [ret appendFormat:@"   - Rank: %@\n", rank];
    return ret;
}

-(void)dealloc
{
    [restaurantId release];
    [title release];
    [restaurantDescription release];
    [contact release];
    [address release];
    [messages release];
    [colorScheme release];
    [openTimes release];
    [deliveryTimes release];
    [status release];
    [deliveryStatus release];
    [timezone release];
    [paymentTypes release];
    [minPayments release];
    [link release];
    [picture release];
    [icon release];
    [properties release];
    [deliveryInfos release];
    [locales release];
    [locale release];
    [currency release];
    [super dealloc];
}

@end
