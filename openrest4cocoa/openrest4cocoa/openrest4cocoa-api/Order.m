//
//  Order.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Order.h"
#import "Utils.h"
#import "OrderItem.h"
#import "LogEntry.h"

@implementation Order

@synthesize orderId;
@synthesize restaurantId;
@synthesize orderItems;
@synthesize comment;
@synthesize price;
@synthesize delivery;
@synthesize contact;
@synthesize payments;
@synthesize created;
@synthesize modified;
@synthesize user;
@synthesize status;
@synthesize shareToken;
@synthesize takeoutPacks;
@synthesize charges;
@synthesize log;
@synthesize locale;

-(id)init
{
    if ((self = [super init]))
    {
        [self setOrderItems:[NSArray array]];
        [self setPrice:0];
        [self setPayments:[NSArray array]];
        [self setCharges:[NSArray array]];
        [self setLog:[NSArray array]];
    }
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [self init]))
    {
        [self setOrderId:[data valueForKey:@"id"]];
        [self setRestaurantId:[data valueForKey:@"restaurantId"]];
        if ([data valueForKey:@"orderItems"] != nil)
        {
            [self setOrderItems:[Utils refactorJsonArray:[data valueForKey:@"orderItems"]
                                                 toClass:@"OrderItem"]];
        }
        [self setComment:[data valueForKey:@"comment"]];
        if ([data valueForKey:@"price"] != nil)
        {
            [self setPrice:[[data valueForKey:@"price"] intValue]];
        }
        if ([data valueForKey:@"delivery"] != nil)
        {
             [self setDelivery:[[[Delivery alloc] initWithDictionary:
                                 [data valueForKey:@"delivery"]] autorelease]];
        }
        if ([data valueForKey:@"contact"] != nil)
        {
            [self setContact:[[[Contact alloc] initWithDictionary:
                                [data valueForKey:@"contact"]] autorelease]];
        }
        if ([data valueForKey:@"payments"] != nil)
        {
            [self setPayments:[Utils refactorJsonArray:[data valueForKey:@"payments"]
                                                 toClass:@"Payment"]];
        }
        [self setCreated:[data valueForKey:@"created"]];
        [self setModified:[data valueForKey:@"modified"]];
        if ([data valueForKey:@"user"] != nil)
        {
            [self setUser:[[[User alloc] initWithDictionary:
                               [data valueForKey:@"user"]] autorelease]];
        }      
        if ([data valueForKey:@"status"] != nil)
        {
            [self setStatus:[data valueForKey:@"status"]];
        } 
        if ([data valueForKey:@"takeoutPacks"] != [NSNull null])
        {
            [self setTakeoutPacks:[data valueForKey:@"takeoutPacks"]];
        } 
        if ([data valueForKey:@"charges"] != nil)
        {
            [self setCharges:[Utils refactorJsonArray:[data valueForKey:@"charges"]
                                               toClass:@"Charge"]];
        }
        if ([data valueForKey:@"log"] != nil)
        {
            [self setLog:[Utils refactorJsonArray:[data valueForKey:@"log"]
                                              toClass:@"LogEntry"]];
        }        
        [self setLocale:[data valueForKey:@"locale"]];
        [self setShareToken:[data valueForKey:@"shareToken"]];
    }
    return self;
}

-(NSString*)description
{
    NSMutableString* ret = [NSMutableString stringWithCapacity:0];
    [ret appendFormat:@"\nOrder: %@\n", orderId];
    [ret appendFormat:@"    - restaurantId: %@\n", restaurantId];
    [ret appendFormat:@"    - orderItems: %@\n", orderItems];
    [ret appendFormat:@"    - comment: %@\n", comment];
    [ret appendFormat:@"    - price: %d\n", price];
    [ret appendFormat:@"    - delivery: %@\n", delivery];
    [ret appendFormat:@"    - contact: %@\n", contact];
    [ret appendFormat:@"    - payments: %@\n", payments];
    [ret appendFormat:@"    - created: %@\n", created];
    [ret appendFormat:@"    - modified: %@\n", modified];
    [ret appendFormat:@"    - user: %@\n", user];
    [ret appendFormat:@"    - status: %@\n", status];
    [ret appendFormat:@"    - shareToken: %@\n", shareToken];
    [ret appendFormat:@"    - takeout packs: %@\n", takeoutPacks];
    [ret appendFormat:@"    - log entry: %@\n", log];

    return ret;
}

-(int)getDeliveryPrice
{
    int total = 0;
    for (int i = 0 ; i < [orderItems count] ; i++)
    {
        OrderItem* item = [orderItems objectAtIndex:i];
        total += [item getTotalPrice];
    }
    
    return price - total;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];

    if (orderId != nil) { [ret setValue:orderId forKey:@"id"]; }
    if (orderItems != nil) { [ret setValue:orderItems forKey:@"orderItems"]; }
    if (comment != nil) { [ret setValue:comment forKey:@"comment"]; }
    if (price > 0) { [ret setValue:[NSNumber numberWithInt:price] forKey:@"price"]; }
    if (delivery != nil) { [ret setValue:delivery forKey:@"delivery"]; }
    if (contact != nil) { [ret setValue:contact forKey:@"contact"]; }
    if (payments != nil) { [ret setValue:payments forKey:@"payments"]; }
    if (status != nil) { [ret setValue:status forKey:@"status"]; }
    if (takeoutPacks != nil) { [ret setValue:takeoutPacks forKey:@"takeoutPacks"]; }
    if (charges != nil) { [ret setValue:charges forKey:@"charges"];}
    if (locale != nil) { [ret setValue:locale forKey:@"locale"];}
    if (log != nil) { [ret setValue:log forKey:@"log"];}
    return ret;
}

-(NSComparisonResult)compareByDate:(Order*)other
{
    Order* objOrder1 = self;
    Order* objOrder2 = other;
    NSComparisonResult result;
    if (([objOrder1 modified] == nil) || ([objOrder2 modified] == nil))
    {
        result = ([[objOrder1 created] compare:[objOrder2 created]]);
    } else
    {
        result = ([[objOrder1 modified] compare:[objOrder2 modified]]);
    }
    if (result == NSOrderedAscending) return NSOrderedDescending;
    if (result == NSOrderedDescending) return NSOrderedAscending;
    return NSOrderedSame;
}

-(void)dealloc
{
    [orderId release];
    [restaurantId release];
    [orderItems release];
    [comment release];
    [delivery release];
    [contact release];
    [payments release];
    [created release];
    [modified release];
    [user release];
    [status release];
    [shareToken release];
    [takeoutPacks release];
    [charges release];
    [log release];
    [locale release];
    [super dealloc];
}

@end
