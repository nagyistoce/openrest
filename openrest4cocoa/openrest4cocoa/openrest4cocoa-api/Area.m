//
//  Area.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Area.h"
#import "LatLng.h"
#import "Utils.h"

@implementation Area

@synthesize title;
@synthesize polygon;

-(id)init
{
    if ((self = [super init]))
    {
        [self setPolygon:[NSArray array]];
    }
    return self;
}

-(id)initWithDictionary:(NSDictionary *)data
{
    if ((self = [self init]))
    {
        [self setTitle:[LocalizedDictionary dictionaryWithDictionary:[data valueForKey:@"title"]]];
        
        if ([data valueForKey:@"polygon"] != nil)
        {
            [self setPolygon:[Utils refactorJsonArray:[data valueForKey:@"polygon"]
                                                 toClass:@"LatLng"]];

        }
    }
    return self;
}

-(NSString *)description
{
    return [title objectForKey:[Utils getLocale]];
}

-(void)dealloc
{
    [title release];
    [polygon release];
    [super dealloc];
}
@end
