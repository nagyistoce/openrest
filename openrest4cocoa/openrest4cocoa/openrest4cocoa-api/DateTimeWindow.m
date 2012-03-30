//
//  DateTimeWindow.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "DateTimeWindow.h"


@implementation DateTimeWindow

@synthesize start;
@synthesize end;
@synthesize available;

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        [self setStart:[[[OpenRestDate alloc] initWithDictionary:[data valueForKey:@"start"]] autorelease]];
        [self setEnd:[[[OpenRestDate alloc] initWithDictionary:[data valueForKey:@"end"]] autorelease]];
        [self setAvailable:[[data valueForKey:@"available"] boolValue]];
    }
    return self;
}

-(NSDictionary*)proxyForJson
{    
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    [ret setValue:start forKey:@"start"];
    [ret setValue:end forKey:@"end"];
    [ret setValue:[NSNumber numberWithBool:available] forKey:@"available"];
    
    return ret;
}

-(BOOL)isEqual:(id)object
{
    if (object == self) return TRUE;
    
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[self class]]) return FALSE;
    
    DateTimeWindow* other = (DateTimeWindow*)object;
    
    if ((start != other.start) && (![start isEqual:other.start])) return FALSE;
    if ((end != other.end) && (![end isEqual:other.end])) return FALSE;
    if (available != other.available) return FALSE;
    
    return TRUE;
}

-(NSUInteger)hash
{
    return [start hash] + [end hash] + (available ? 0 : 1);
}

-(void)dealloc
{
    [super dealloc];
}

@end
