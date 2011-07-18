//
//  Availability.m
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "OpenrestAvailability.h"
#import "WeeklyTimeWindow.h"
#import "DateTimeWindow.h"
#import "Utils.h"

@implementation OpenrestAvailability

@synthesize weekly;
@synthesize exceptions;

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        if ([data valueForKey:@"weekly"] != nil)
        {
            [self setWeekly:[Utils refactorJsonArray:[data valueForKey:@"weekly"]
                                                    toClass:@"WeeklyTimeWindow"]];
        }

        if ([data valueForKey:@"exceptions"] != nil)
        {
            [self setExceptions:[Utils refactorJsonArray:[data valueForKey:@"exceptions"]
                                             toClass:@"DateTimeWindow"]];
        }
    }
    
    return self;
}
-(void)dealloc
{
    [weekly release];
    [exceptions release];
    [super dealloc];
}

@end
