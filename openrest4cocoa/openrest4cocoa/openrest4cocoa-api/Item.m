//
//  Item.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Item.h"
#import "Utils.h"

@implementation Item

@synthesize itemId;
@synthesize restaurantId;
@synthesize title;
@synthesize itemDescription;
@synthesize price;
@synthesize variations;
@synthesize availability;
@synthesize availabilityStr;
@synthesize inactive;
@synthesize status;
@synthesize picture;

-(id)init
{
    if ((self = [super init]))
    {
        [self setVariations:[NSArray array]];
        [self setAvailability:[[[OpenrestAvailability alloc] init] autorelease]];
        [self setPrice:0];
        [self setInactive:false];
    }
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [self init]))
    {
        [self setItemId:[data valueForKey:@"id"]];
        [self setRestaurantId:[data valueForKey:@"restaurantId"]];
        [self setTitle:[LocalizedDictionary dictionaryWithDictionary:[data valueForKey:@"title"]]];
        [self setItemDescription:[LocalizedDictionary dictionaryWithDictionary:[data valueForKey:@"description"]]];
        if ([data valueForKey:@"price"])
        {
            [self setPrice:[[data valueForKey:@"price"] intValue]];
        }
        if ([data valueForKey:@"variations"])
        {
            [self setVariations:[Utils refactorJsonArray:[data valueForKey:@"variations"]
                                                 toClass:@"Variation"]];
        }
        if ([data valueForKey:@"availability"])
        {
            [self setAvailability:[[[OpenrestAvailability alloc] initWithDictionary:[data valueForKey:@"availability"]] autorelease]];
        }
        [self setAvailabilityStr:[data valueForKey:@"availabilityStr"]];
        if ([data valueForKey:@"inactive"])
        {
            [self setInactive:([[data valueForKey:@"inactive"] intValue] == 1)];
        }
        if ([data valueForKey:@"status"])
        {
            [self setStatus:[[[Status alloc] initWithDictionary:[data valueForKey:@"status"]] autorelease]];
        }
        [self setPicture:[data valueForKey:@"picture"]];
    }
    return self;
}

-(void)dealloc
{
    [itemId release];
    [restaurantId release];
    [title release];
    [itemDescription release];
    [variations release];
    [availability release];
    [availabilityStr release];
    [status release];
    [picture release];
    [super dealloc];
}
@end
