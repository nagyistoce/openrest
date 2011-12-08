//
//  LogEntry.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 8/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "LogEntry.h"


@implementation LogEntry

@synthesize timestamp;
@synthesize user;
@synthesize comment;

-(id)init
{
    if ((self = [super init]))
    {
        comment = [NSString string];
    }
    
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [self init]))
    {
        [self setTimestamp:[data valueForKey:@"timestamp"]];
        if ([data valueForKey:@"user"] != NULL)
        {
            [self setUser:[[[User alloc] initWithDictionary:
                          [data valueForKey:@"user"]] autorelease]];
        }
        [self setComment:[data valueForKey:@"comment"]];
    }
    
    return self;
}

-(NSDictionary*)proxyForJson
{    
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    if (timestamp != nil) {[ret setValue:timestamp forKey:@"timestamp"];}
    if (user != nil) {[ret setValue:user forKey:@"user"];}
    if (comment != nil) {[ret setValue:comment forKey:@"comment"];}
    
    return ret;
}

-(void)dealloc
{
    [timestamp release];
    [user release];
    [comment release];
    [super dealloc];
}

@end
