//
//  WeeklyTimeWindow.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "WeeklyTimeWindow.h"


@implementation WeeklyTimeWindow

@synthesize minuteOfWeek;
@synthesize durationMins;

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        [self setMinuteOfWeek:[[data valueForKey:@"minuteOfWeek"] intValue]];
        [self setDurationMins:[[data valueForKey:@"durationMins"] intValue]];
    }
    return self;
}

@end
