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

-(id)init
{
    if ((self = [super init]))
    {
        [self setItemIds:[NSArray array]];
        [self setPriority:0.0];
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
    }
    return self;
}

-(void)dealloc
{
    [categoryId release];
    [restaurantId release];
    [title release];
    [description release];
    [parentCategoryId release];
    [itemIds release];
    [super dealloc];
}
@end
