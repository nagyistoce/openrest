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

-(id)init
{
    if ((self = [super init]))
    {
        [self setWeekly:[NSArray array]];
        [self setExceptions:[NSArray array]];
    }
    
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [self init]))
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

-(NSUInteger)hash
{
    return [weekly hash] + [exceptions hash];
}

-(BOOL)isEqual:(id)object
{
    if (object == self) return TRUE;
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[OpenrestAvailability class]]) return FALSE;
    
    OpenrestAvailability* other = (OpenrestAvailability*)object;
    
    if ((weekly != other.weekly) && (![weekly isEqualToArray:other.weekly])) return FALSE;
    if ((exceptions != other.exceptions) && (![exceptions isEqualToArray:other.exceptions])) return FALSE;

    return TRUE;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    if (weekly != nil) 
    {
        [ret setValue:weekly forKey:@"weekly"];
    }
    else
    {
        [ret setValue:[NSArray array] forKey:@"weekly"];        
    }
    
    if (exceptions != nil) 
    {
        [ret setValue:exceptions forKey:@"exceptions"];
    }
    else
    {
        [ret setValue:[NSArray array] forKey:@"exceptions"];    
    }
    
    return ret;
}

-(void)dealloc
{
    [weekly release];
    [exceptions release];
    [super dealloc];
}

@end
