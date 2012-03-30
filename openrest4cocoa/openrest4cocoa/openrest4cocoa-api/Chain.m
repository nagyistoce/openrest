//
//  Chain.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 2/1/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "Chain.h"

@implementation Chain

@synthesize distributorId;

-(id)init
{
    if ((self = [super init]))
    {
    }
    
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super initWithDictionary:data]))
    {
        [self setDistributorId:[data valueForKey:@"distributorId"]];
    }
    
    return self;
}

-(NSString *)description
{
    NSMutableString* ret = (NSMutableString*)[super description];
    
    [ret appendFormat:@"   - distributorId: %@\n", distributorId];
    
    return ret;
}

-(void)dealloc
{
    self.distributorId = nil;
    
    [super dealloc];
}

-(BOOL)isEqual:(id)object
{
    if (self == object) return TRUE;
    
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[Chain class]]) return FALSE;
    if (![super isEqual:object]) return FALSE;
    
    Chain* other = (Chain*)object;
    
    if (![distributorId isEqual:other.distributorId]) return FALSE;
    
    return TRUE;
}


-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = (NSMutableDictionary*)[super proxyForJson];
    
    if (distributorId != nil) {[ret setValue:distributorId forKey:@"distributorId"];}
    
    return ret;
}

@end

