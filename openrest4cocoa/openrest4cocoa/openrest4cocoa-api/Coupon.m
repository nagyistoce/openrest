//
//  Coupon.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 7/12/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Coupon.h"


@implementation Coupon

@synthesize type;
@synthesize title;
@synthesize description;
@synthesize maxNumAllowed;

-(id)init
{
    if ((self = [super init]))
    {
        [self setMaxNumAllowed:[NSNumber numberWithInt:INT32_MAX]];
        othersAllowed = true;
    }
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [self init]))
    {
        [self setType:[data valueForKey:@"type"]];
        [self setTitle:[LocalizedDictionary dictionaryWithDictionary:[data valueForKey:@"title"]]];
        [self setDescription:[LocalizedDictionary dictionaryWithDictionary:[data valueForKey:@"description"]]];
        [self setMaxNumAllowed:[data valueForKey:@"maxNumAllowed"]];
        othersAllowed = ([[data valueForKey:@"othersAllowed"] intValue] == 1);
    }
    return self; 
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    if (type != nil) {[ret setValue:type forKey:@"type"];}
    if (title != nil) {[ret setValue:title forKey:@"title"];}
    if (description != nil) {[ret setValue:description forKey:@"description"];}
    if (maxNumAllowed != nil) {[ret setValue:maxNumAllowed forKey:@"maxNumAllowed"];}
    if (othersAllowed) 
    {
        [ret setValue:@"true" forKey:@"othersAllowed"];
    }
    else
    {
        [ret setValue:@"false" forKey:@"othersAllowed"];        
    }
    
    return ret;
}

-(void)dealloc
{
    [type release];
    [title release];
    [description release];
    [maxNumAllowed release];
    [super dealloc];
}
@end
