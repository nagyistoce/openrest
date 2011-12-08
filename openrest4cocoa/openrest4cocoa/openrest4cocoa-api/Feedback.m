//
//  Feedback.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 12/6/11.
//  Copyright (c) 2011 __MyCompanyName__. All rights reserved.
//

#import "Feedback.h"

@implementation Feedback

@synthesize type;
@synthesize comment;
@synthesize contact;
@synthesize restaurantId;

-(id)init
{
    if ((self = [super init]))
    {
        contact = [[Contact alloc] init];
    }    
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        type = [[data objectForKey:@"type"] retain];
        comment = [[data objectForKey:@"comment"] retain];
        contact = [[Contact alloc] initWithDictionary:[data objectForKey:@"contact"]];
        restaurantId = [[data objectForKey:@"restaurantId"] retain];
    }
    return self;
}

-(void)dealloc
{
    self.type = NULL;
    self.comment = NULL;
    self.contact = NULL;
    self.restaurantId = NULL;
    [super dealloc];
}


-(NSDictionary*)proxyForJson
{
    return [NSDictionary dictionaryWithObjectsAndKeys:type, @"type", comment, @"comment", contact, @"contact", restaurantId, @"restaurantId", nil];
}

@end
