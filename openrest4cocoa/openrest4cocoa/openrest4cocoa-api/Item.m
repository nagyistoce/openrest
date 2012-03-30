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
@synthesize inactive;
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
        if ([data valueForKey:@"inactive"])
        {
            [self setInactive:([[data valueForKey:@"inactive"] intValue] == 1)];
        }
        [self setPicture:[data valueForKey:@"picture"]];
    }
    return self;
}

-(NSUInteger)hash
{
    return [itemId hash];
}

-(BOOL)isEqual:(id)object
{
    if (object == self) return TRUE;
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[self class]]) return FALSE;

    Item* other = (Item*)object;
    if (![itemId isEqualToString:other.itemId]) return FALSE;
    if (![restaurantId isEqualToString:other.restaurantId]) return FALSE;
    if (![title isEqual:other.title]) return FALSE;
    if (![itemDescription isEqual:other.itemDescription]) return FALSE;
    if (price != other.price) return FALSE;
    if ((variations != other.variations) && (![variations isEqualToArray:other.variations])) return FALSE;
    if ((availability !=other.availability) && (![availability isEqual:other.availability])) return FALSE;
    if (inactive != other.inactive) return FALSE;
    if ((picture != other.picture) && (![picture isEqualToString:other.picture])) return FALSE;
    
    return TRUE;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];

    if (itemId != nil) {[ret setValue:itemId forKey:@"id"];}
    if (restaurantId != nil) {[ret setValue:restaurantId forKey:@"restaurantId"];}
    if (title != nil) {[ret setValue:title forKey:@"title"];}
    if (itemDescription != nil) {[ret setValue:itemDescription forKey:@"description"];}
    [ret setValue:[NSNumber numberWithInt:price] forKey:@"price"];
    if (variations != nil) {[ret setValue:variations forKey:@"variations"];}
    if (availability != nil) {[ret setValue:availability forKey:@"availability"];}
    [ret setValue:[NSNumber numberWithBool:inactive] forKey:@"inactive"];
    if (picture != nil) {[ret setValue:picture forKey:@"picture"];}

    
    return ret;
}

-(void)dealloc
{
    [itemId release];
    [restaurantId release];
    [title release];
    [itemDescription release];
    [variations release];
    [availability release];
    [picture release];
    [super dealloc];
}
@end
