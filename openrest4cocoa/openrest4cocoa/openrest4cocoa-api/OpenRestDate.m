//
//  OpenRestDate.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/31/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "OpenRestDate.h"


@implementation OpenRestDate

@synthesize year;
@synthesize month;
@synthesize day;
@synthesize hour;
@synthesize minute;

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        year = [[data objectForKey:@"year"] intValue];
        month = [[data objectForKey:@"month"] intValue];
        day = [[data objectForKey:@"day"] intValue];
        hour = [[data objectForKey:@"hour"] intValue];
        minute = [[data objectForKey:@"minute"] intValue];
    }
    return self;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];

    [ret setValue:[NSNumber numberWithInt:year] forKey:@"year"];
    [ret setValue:[NSNumber numberWithInt:month] forKey:@"month"];
    [ret setValue:[NSNumber numberWithInt:day] forKey:@"day"];
    [ret setValue:[NSNumber numberWithInt:hour] forKey:@"hour"];
    [ret setValue:[NSNumber numberWithInt:minute] forKey:@"minute"];
    
    return ret;
}

-(BOOL)isEqual:(id)object
{
    if (object == self) return TRUE;
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[self class]]) return FALSE;
    OpenRestDate* other = (OpenRestDate*)object;
   
    if (year != other.year) return FALSE;
    if (month != other.month) return FALSE;
    if (day != other.day) return FALSE;
    if (hour != other.hour) return FALSE;
    if (minute != other.minute) return FALSE;
    
    return TRUE;
}

-(NSUInteger)hash
{
    return year+month+day+hour+minute;
}

-(NSString*)description
{
    return [NSString stringWithFormat:@"%d/%d/%d %d:%d", day, month, year, hour, minute];
}

@end
