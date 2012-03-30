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
@synthesize othersAllowed;

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
        othersAllowed = TRUE;
        if ([data valueForKey:@"othersAllowed"] != NULL)
        {
            othersAllowed = ([[data valueForKey:@"othersAllowed"] boolValue]);
        }
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
    [ret setValue:[NSNumber numberWithBool:othersAllowed] forKey:@"othersAllowed"]; 

    
    return ret;
}

-(BOOL)isEqual:(id)object
{
    if (self == object) return TRUE;
    
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[self class]]) return FALSE;
    
    
    Coupon* other = (Coupon*)object;
    
    if ((type != other.type) && (![type isEqual:other.type])) return FALSE;
    if ((title != other.title) && (![title isEqual:other.title])) return FALSE;
    if ((description != other.description) && (![description isEqual:other.description])) return FALSE;
    if ((maxNumAllowed != other.maxNumAllowed) && (![maxNumAllowed isEqual:other.maxNumAllowed])) return FALSE;
    if (othersAllowed != other.othersAllowed) return FALSE;
    
    return TRUE;
}

-(NSUInteger)hash
{
    NSUInteger ret = [title hash] + [type hash];
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
