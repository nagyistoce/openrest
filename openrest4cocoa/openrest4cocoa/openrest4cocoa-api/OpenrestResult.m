//
//  OpenrestError.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/6/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "OpenrestResult.h"


@implementation OpenrestResult

@synthesize timestamp;
@synthesize code;
@synthesize error;
@synthesize errorMessage;

-(id)initSuccess:(NSNumber*)_timestamp
{
    if ((self = [super init]))
    {
        [self setTimestamp:_timestamp];
        [self setCode:OPENREST_ERROR_CODE_SUCCESS];
    }
    
    return self;
    
}

-(id)initError:(NSNumber*)_timestamp withCode:(int)_code andError:(NSString*)_error andMessage:(NSString*)_message
{
    if ((self = [super init]))
    {
        [self setTimestamp:_timestamp];
        [self setCode:_code];
        [self setError:_error];
        [self setErrorMessage:_message];
    }
    
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        [self setTimestamp:[data valueForKey:@"timestamp"]];
        if ([data valueForKey:@"error"] != nil)
        {
            [self setCode:OPENREST_ERROR_CODE_SERVER];
            [self setError:[data valueForKey:@"error"]];
            [self setErrorMessage:[data valueForKey:@"errorMessage"]];
        }
        else
        {
            [self setCode:OPENREST_ERROR_CODE_SUCCESS];            
        }
    }
    return self;
}

-(bool)isError
{
    return code != OPENREST_ERROR_CODE_SUCCESS;
}

-(void)dealloc
{
    [timestamp release];
    [error release];
    [errorMessage release];
    [super dealloc];
}

@end
