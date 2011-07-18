//
//  Stats.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/31/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Stats.h"


@implementation Stats

@synthesize date;
@synthesize count;
@synthesize total;

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        self.date = [[[OpenRestDate alloc] initWithDictionary:[data objectForKey:@"date"]] autorelease];
        self.count = [[data objectForKey:@"count"] intValue];
        self.total = [[data objectForKey:@"total"] intValue];
    }
    return self;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];

    [ret setValue:[date proxyForJson] forKey:@"Date"];
    [ret setValue:[NSNumber numberWithInt:count] forKey:@"count"];
    [ret setValue:[NSNumber numberWithInt:total] forKey:@"total"];
    
    return ret;
}

-(void)dealloc
{
    [self setDate:nil];
    [super dealloc];
}

@end
