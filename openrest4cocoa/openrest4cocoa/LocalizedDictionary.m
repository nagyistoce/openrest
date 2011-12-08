//
//  LocalizedDictionary.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 10/2/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "LocalizedDictionary.h"

@interface LocalizedDictionary ()

@property (nonatomic, retain) NSDictionary* dictionary;

@end

static NSString* locale;

@implementation LocalizedDictionary

@synthesize dictionary;

+(void)setDefaultLocale:(NSString*)_locale
{
    [locale release];
    locale = [_locale retain];
}

+(id)dictionaryWithDictionary:(NSDictionary *)otherDictionary
{
    return [[[LocalizedDictionary alloc] initWithDictionary:otherDictionary] autorelease];    
}

+(id)dictionary
{
    return [[[LocalizedDictionary alloc] initWithDictionary:[NSDictionary dictionary]] autorelease];
}

-(id)initWithDictionary:(NSDictionary *)otherDictionary
{
    if ((self = [super init]))
    {
        dictionary = [[NSDictionary alloc] initWithDictionary:otherDictionary];
    }
    return self;
}

-(NSString*)description
{
    if (locale == NULL)
    {
        return [dictionary description];
    }
    return [dictionary objectForKey:locale];
}

-(NSString*)objectForKey:(NSString*)key
{
    return [dictionary objectForKey:key];
}

-(NSDictionary*)proxyForJson
{
    return dictionary;
}

@end
