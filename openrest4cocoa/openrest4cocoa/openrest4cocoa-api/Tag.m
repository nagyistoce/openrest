//
//  Tag.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Tag.h"


@implementation Tag

@synthesize tagId;
@synthesize restaurantId;
@synthesize title;
@synthesize itemIds;


-(id)init
{
    if ((self = [super init]))
    {
        [self setItemIds:[NSArray array]];
    }
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [self init]))
    {
        [self setTagId:[data valueForKey:@"id"]];
        [self setRestaurantId:[data valueForKey:@"restaurantId"]];
        [self setTitle:[LocalizedDictionary dictionaryWithDictionary:[data valueForKey:@"title"]]];
        if ([data valueForKey:@"itemIds"] != nil)
        {
            [self setItemIds:[data valueForKey:@"itemIds"]];
        }
    }
    return self;
}

-(NSUInteger)hash
{
    return [tagId hash];
}

-(BOOL)isEqual:(id)object
{
    if (object == self) return TRUE;
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[self class]]) return FALSE;
    Tag* other = (Tag*)object;

    if (![tagId isEqualToString:other.tagId]) return FALSE;
    if (![restaurantId isEqualToString:other.restaurantId]) return FALSE;
    if (![title isEqual:other.title]) return FALSE;
    if (![itemIds isEqualToArray:other.itemIds]) return FALSE;

    return TRUE;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];

    if (tagId != nil) {[ret setValue:tagId forKey:@"id"];}
    if (restaurantId != nil) {[ret setValue:restaurantId forKey:@"restaurantId"];}
    if (title != nil) {[ret setValue:title forKey:@"title"];}
    if (itemIds != nil) {[ret setValue:itemIds forKey:@"itemIds"];}
    
    return ret;
}

-(NSString*)description
{
    return [NSString stringWithFormat:@"Tag[%@]: %@ %@ %@", tagId, title, restaurantId, itemIds];
}

-(void)dealloc
{
    [tagId release];
    [restaurantId release];
    [title release];
    [itemIds release];
    [super dealloc];
}

@end
