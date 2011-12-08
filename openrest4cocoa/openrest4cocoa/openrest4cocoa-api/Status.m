//
//  Status.m
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Status.h"


@implementation Status

@synthesize status;
@synthesize until;

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        [self setStatus:[data valueForKey:@"status"]];
        [self setUntil:[data valueForKey:@"until"]];        
    }
    return self;
}

-(void)dealloc
{
    [status release];
    [until release];
    [super dealloc];
}

@end
