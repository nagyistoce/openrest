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

-(NSString*)description
{
    return [NSString stringWithFormat:@"Tag[%@]: %@", tagId, title];
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
