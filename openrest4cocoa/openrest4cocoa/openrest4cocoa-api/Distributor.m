//
//  Distributor.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 2/1/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "Distributor.h"

@implementation Distributor

@synthesize facebookAppId;

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
        [self setFacebookAppId:[data valueForKey:@"facebookAppId"]];
    }
    
    return self;
}

-(NSString *)description
{
    NSMutableString* ret = (NSMutableString*)[super description];
    
    [ret appendFormat:@"   - facebookAppId: %@\n", facebookAppId];

    return ret;
}

-(void)dealloc
{
    self.facebookAppId = nil;
    
    [super dealloc];
}

-(BOOL)isEqual:(id)object
{
    if (self == object) return TRUE;
    
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[Distributor class]]) return FALSE;
    if (![super isEqual:object]) return FALSE;
    
    Distributor* other = (Distributor*)object;
    
    if (![facebookAppId isEqual:other.facebookAppId]) return FALSE;

    return TRUE;
}


-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = (NSMutableDictionary*)[super proxyForJson];
    
    if (facebookAppId != nil) {[ret setValue:facebookAppId forKey:@"facebookAppId"];}

    return ret;
}
@end
