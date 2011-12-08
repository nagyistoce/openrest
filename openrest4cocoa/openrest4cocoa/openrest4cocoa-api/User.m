//
//  User.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/6/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "User.h"


@implementation User

@synthesize userId;
@synthesize ipAddress;
@synthesize fwdIpAddresses;

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        [self setUserId:[data valueForKey:@"id"]];
        [self setIpAddress:[data valueForKey:@"ipAddress"]];
        [self setFwdIpAddresses:[data valueForKey:@"fwdIpAddresses"]];
    }
    return self;
}

-(NSDictionary*)proxyForJson
{    
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    if (userId != nil) {[ret setValue:userId forKey:@"id"];}
    if (ipAddress != nil) {[ret setValue:ipAddress forKey:@"ipAddress"];}
    if (fwdIpAddresses != nil) {[ret setValue:fwdIpAddresses forKey:@"fwdIpAddresses"];}
    
    return ret;
}

-(void)dealloc
{
    [userId release];
    [ipAddress release];
    [fwdIpAddresses release];
    [super dealloc];
}

@end
