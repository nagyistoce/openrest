//
//  Utils.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Utils.h"


@implementation Utils

+(NSSet*)refactorJsonArrayToSet:(NSArray*)array toClass:(NSString*)classStr
{
    NSMutableSet* result = [NSMutableSet setWithCapacity:[array count]];
    
    for (int i = 0 ; i < [array count] ; i++)
    {
        [result addObject:[[[NSClassFromString(classStr) alloc] initWithDictionary:[array objectAtIndex:i]] autorelease]];
    }
    
    return result;  
}

+(NSArray*)refactorJsonArray:(NSArray*)array toClass:(NSString*)classStr
{
    NSMutableArray* result = [NSMutableArray arrayWithCapacity:
                                          [array count]];
    
    for (int i = 0 ; i < [array count] ; i++)
    {
        [result addObject:[[[NSClassFromString(classStr) alloc] initWithDictionary:[array objectAtIndex:i]] autorelease]];
    }
    
    return result;
}

+(NSDictionary*)refactorJsonHash:(NSDictionary*)hash toClass:(NSString*)classStr
{
    NSMutableDictionary* result = [NSMutableDictionary dictionaryWithCapacity:[hash count]];
    
    [hash enumerateKeysAndObjectsUsingBlock:^(id key, id obj, BOOL *stop) {
        
        [result setObject:[[NSClassFromString(classStr) alloc] initWithDictionary:obj] forKey:key];
    }];
    
    return result;
}

+(NSArray*)refactorJsonArrayOfArray:(NSArray *)array toClass:(NSString *)classStr
{
    NSMutableArray* result = [NSMutableArray arrayWithCapacity:
                              [array count]];
    
    for (int i = 0 ; i < [array count] ; i++)
    {
        [result addObject:[Utils refactorJsonArray:[array objectAtIndex:i] 
                                           toClass:classStr]];
    }
    
    return result;    
}

+(NSString*)appendQueryToUrl:(NSString*)url query:(NSString*)query
{
    NSRange questionMark = [url rangeOfString:@"?"];
    if (questionMark.location == NSNotFound)
    {
        return [NSString stringWithFormat:@"%@?%@", url, query];
    }
    else
    {
        return [NSString stringWithFormat:@"%@&%@",url, query];
    }
    return url;
}

+(NSString*)getLocale
{
    return @"he_IL"; //TODO!
}

@end
