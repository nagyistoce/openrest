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

-(BOOL)isEqual:(id)object
{
    if (self == object) return TRUE;
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[self class]]) return FALSE;
    App* other = (App*)object;
    
    if ((type != other.type) && (![type isEqualToString:other.type])) return FALSE;
    if ((platform != other.platform) && (![platform isEqualToString:other.platform])) return FALSE;
    if ((appId != other.appId) && (![appId isEqualToString:other.appId])) return FALSE;
    if ((version != other.version) && (![version isEqualToString:other.version])) return FALSE;
    if ((link != other.link) && (![link isEqualToString:other.link])) return FALSE;
    
    return TRUE;
}

-(NSDictionary*)proxyForJson
{   
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    if (type != nil) {[ret setValue:type forKey:@"type"];}
    if (platform != nil) {[ret setValue:platform forKey:@"platform"];}
    if (appId != nil) {[ret setValue:appId forKey:@"id"];}
    if (version != nil) {[ret setValue:version forKey:@"version"];}
    if (link != nil) {[ret setValue:link forKey:@"link"];}
    
    return ret;
}

-(NSUInteger)hash
{
    return [type hash] + [platform hash] + [appId hash] + [version hash] + [link hash];
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
