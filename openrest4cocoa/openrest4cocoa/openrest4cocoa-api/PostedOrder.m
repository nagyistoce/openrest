//
//  PostedOrder.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 8/24/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "PostedOrder.h"


@implementation PostedOrder

@synthesize order;
@synthesize message;

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        [self setMessage:[LocalizedDictionary dictionaryWithDictionary:[data valueForKey:@"message"]]];
        [self setOrder:[[[Order alloc] initWithDictionary:
                          [data valueForKey:@"order"]] autorelease]];
    }
    return self;
}

-(void)dealloc
{
    [message release];
    [order release];
    [super dealloc];
}

@end
