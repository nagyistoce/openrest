//
//  App.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 11/22/11.
//  Copyright (c) 2011 __MyCompanyName__. All rights reserved.
//

#import "App.h"

@implementation App

@synthesize type;
@synthesize platform;
@synthesize appId;
@synthesize version;
@synthesize link;

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        self.type = [data objectForKey:@"type"];
        self.platform = [data objectForKey:@"platform"];
        self.appId = [data objectForKey:@"id"];
        self.version = [data objectForKey:@"version"];
        self.link = [data objectForKey:@"link"];
    }
    
    return self;
}

-(void)dealloc
{
    self.type = NULL;
    self.platform = NULL;
    self.appId = NULL;
    self.version = NULL;
    self.link = NULL;
    [super dealloc];
}

@end
