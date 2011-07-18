//
//  Variation.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Variation.h"


@implementation Variation

@synthesize title;
@synthesize tagId;
@synthesize minNumAllowed;
@synthesize maxNumAllowed;
@synthesize prices;
@synthesize defaults;
@synthesize displayType;

-(id)init
{
    if ((self = [super init]))
    {
        [self setMinNumAllowed:0];
        [self setMaxNumAllowed:INT32_MAX];
        [self setPrices:[NSDictionary dictionary]];
        [self setDefaults:[NSArray array]];
        [self setDisplayType:VARIATION_DISPLAY_TYPE_CHOICE];
    }
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [self init]))
    {
        [self setTitle:[data valueForKey:@"title"]];
        [self setTagId:[data valueForKey:@"tagId"]];
        if ([data valueForKey:@"minNumAllowed"] != nil)
        {
            [self setMinNumAllowed:[[data valueForKey:@"minNumAllowed"] intValue]];
        }
        if ([data valueForKey:@"maxNumAllowed"] != nil)
        {
            [self setMaxNumAllowed:[[data valueForKey:@"maxNumAllowed"] intValue]];
        }
        if ([data valueForKey:@"prices"] != nil)
        {
            [self setPrices:[data valueForKey:@"prices"]];
        }
        if ([data valueForKey:@"defaults"] != nil)
        {
            [self setDefaults:[data valueForKey:@"defaults"]];
        }
        if ([data valueForKey:@"displayType"] != nil)
        {
            [self setDisplayType:[data valueForKey:@"displayType"]];
        }
    }
    return self;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    if (title != nil) {[ret setValue:title forKey:@"title"];}
    if (tagId != nil) {[ret setValue:tagId forKey:@"tagId"];}
    if (prices != nil) {[ret setValue:prices forKey:@"prices"];}
    [ret setValue:[NSNumber numberWithInt:minNumAllowed] forKey:@"minNumAllowed"];
    [ret setValue:[NSNumber numberWithInt:maxNumAllowed] forKey:@"maxNumAllowed"];
    if (defaults != nil) {[ret setValue:defaults forKey:@"defaults"];}
    if (displayType != nil) {[ret setValue:displayType forKey:@"displayType"];}
    
    return ret;
}


-(void)dealloc
{
    [title release];
    [tagId release];
    [prices release];
    [defaults release];
    [displayType release];
    [super dealloc];
}
@end
