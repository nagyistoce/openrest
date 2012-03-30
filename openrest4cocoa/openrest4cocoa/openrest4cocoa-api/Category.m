//
//  Category.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Category.h"
#import "Utils.h"

@implementation Category

@synthesize categoryId;
@synthesize restaurantId;
@synthesize title;
@synthesize description;
@synthesize parentCategoryId;
@synthesize itemIds;
@synthesize priority;
@synthesize properties;

-(id)init
{
    if ((self = [super init]))
    {
        [self setItemIds:[NSArray array]];
        [self setPriority:0.0];
        [self setProperties:[NSDictionary dictionary]];
    }
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [self init]))
    {
        [self setCategoryId:[data valueForKey:@"id"]];
        [self setRestaurantId:[data valueForKey:@"restaurantId"]];
        [self setTitle:[LocalizedDictionary dictionaryWithDictionary:[data valueForKey:@"title"]]];
        [self setDescription:[LocalizedDictionary dictionaryWithDictionary:[data valueForKey:@"description"]]];
        [self setParentCategoryId:[data valueForKey:@"parentCategoryId"]];
        if ([data valueForKey:@"itemIds"] != nil)
        {
            [self setItemIds:[data valueForKey:@"itemIds"]];
        }
        if ([data valueForKey:@"priority"] != nil)
        {
            [self setPriority:[[data valueForKey:@"priority"] doubleValue]];
        }
        if ([data valueForKey:@"properties"] != nil)
        {
            [self setProperties:[data valueForKey:@"properties"]];
        }
    }
    return self;
}

-(NSUInteger)hash
{
    return [categoryId hash];
}

-(BOOL)isEqual:(id)object
{
    if (object == self) return TRUE;
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[self class]]) return FALSE;
    
    Category* other = (Category*)object;
    if (![categoryId isEqualToString:other.categoryId]) return FALSE;
    if (![restaurantId isEqualToString:other.restaurantId]) return FALSE;
    if (![title isEqual:other.title]) return FALSE;
    if (![description isEqual:other.description]) return FALSE;
    if ((parentCategoryId != other.parentCategoryId) && (![parentCategoryId isEqualToString:other.parentCategoryId])) return FALSE;
    if (![itemIds isEqualToArray:other.itemIds]) return FALSE;
    if (priority != other.priority) return FALSE;
    if (![properties isEqualToDictionary:other.properties]) return FALSE;
    
    return TRUE;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    if (categoryId != nil) {[ret setValue:categoryId forKey:@"id"];}
    if (restaurantId != nil) {[ret setValue:restaurantId forKey:@"restaurantId"];}
    if (title != nil) {[ret setValue:title forKey:@"title"];}
    if (description != nil) {[ret setValue:description forKey:@"description"];}
    if (parentCategoryId != nil) {[ret setValue:parentCategoryId forKey:@"parentCategoryId"];}
    if (itemIds != nil) {[ret setValue:itemIds forKey:@"itemIds"];}
    [ret setValue:[NSNumber numberWithDouble:priority] forKey:@"priority"];
    [ret setValue:properties forKey:@"properties"];
    
    return ret;
}

-(void)dealloc
{
    [categoryId release];
    [restaurantId release];
    [title release];
    [description release];
    [parentCategoryId release];
    [itemIds release];
    [properties release];
    [super dealloc];
}
@end
