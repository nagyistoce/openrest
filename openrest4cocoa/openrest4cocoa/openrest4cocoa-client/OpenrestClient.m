//
//  OpenrestClient.m
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "OpenrestClient.h"
#import "../JSON.h"
#import "../Utils.h"

@interface OpenrestClient () 
-(NSObject*)createObjectFromUrl:(NSString*)url ofClass:(NSString*)objectClass result:(OpenrestResult**)result postData:(NSString*)postData putData:(NSString*)putData;
-(NSDictionary*)getJsonDictionary:(NSString*)url result:(OpenrestResult**)result postData:(NSString*)postData putData:(NSString*)putData;

@end

@implementation OpenrestClient

@synthesize restaurantUri;
@synthesize accessToken;


-(id)initWithRestaurantUri:(NSString*)_restaurantUri accessToken:(NSString*)_accessToken
{
    if ((self = [super init]))
    {
        [self setRestaurantUri:_restaurantUri];
        [self setAccessToken:_accessToken];
    }
    return self;
}

-(id)initWithRestaurantUri:(NSString*)_restaurantUri
{
    return [self initWithRestaurantUri:_restaurantUri accessToken:nil];
}

-(Restaurant*)createRestaurant:(OpenrestResult**)result;
{
    return (Restaurant*)[self createObjectFromUrl:restaurantUri ofClass:@"Restaurant" result:result postData:nil putData:nil];    
}

-(Menu*)createMenu:(OpenrestResult**)result;
{
    return (Menu*)[self createObjectFromUrl:[NSString stringWithFormat:@"%@/menu", restaurantUri] ofClass:@"Menu" result:result postData:nil putData:nil];    
}

-(Order*)getOrder:(NSString*)orderId result:(OpenrestResult**)result
{
    return (Order*)[self createObjectFromUrl:[NSString stringWithFormat:@"%@/orders/%@", restaurantUri, orderId] ofClass:@"Order" result:result postData:nil putData:nil];        
}

-(NSArray*)getStats:(NSString*)granularity result:(OpenrestResult**)result
{
    NSDictionary* json = [self 
                          getJsonDictionary:[NSString stringWithFormat:@"%@/stats?granularity=%@", restaurantUri, granularity]
                          result:result 
                          postData:nil putData:nil];

    if (json == nil)
    {
        return nil;
    }
    
    return [Utils refactorJsonArray:[json valueForKey:@"value"] toClass:@"Stats"];
}

-(NSArray*)getOrders:(NSString*)parameters result:(OpenrestResult**)result
{
    
    NSDictionary* data;
    if (parameters != nil) 
    {
        data  = [self getJsonDictionary:[NSString stringWithFormat:@"%@/orders/?%@", restaurantUri, parameters] result:result postData:nil putData:nil];
    }
    else
    {
        data  = [self getJsonDictionary:[NSString stringWithFormat:@"%@/orders/", restaurantUri] result:result postData:nil putData:nil];
    }
    
    if (data == nil) return nil;

    if ([data valueForKey:@"value"] == nil)
    {
        return [NSArray array];
    }
    return [Utils refactorJsonArray:[data valueForKey:@"value"] toClass:@"Order"];
}

-(void)saveOrder:(Order*)order result:(OpenrestResult**)result
{
    [self getJsonDictionary:[NSString stringWithFormat:@"%@/orders/%@", restaurantUri, [order orderId]]
                     result:result postData:nil putData:[order openrest4cocoaJSONRepresentation]];
}

-(NSDictionary*)getJsonDictionary:(NSString*)url result:(OpenrestResult**)result postData:(NSString*)postData putData:(NSString*)putData
{
    if ([self accessToken] != nil)
    {
        url = [Utils appendQueryToUrl:url query:[NSString stringWithFormat:@"access_token=%@",
                                                 [accessToken stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]]];
    }
    url = [Utils appendQueryToUrl:url query:@"media=json"];
    NSLog(@"request: %@", url);
    NSMutableURLRequest* request = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:url]];
                                   /* cachePolicy:NSURLRequestReloadIgnoringLocalAndRemoteCacheData 
                                                    timeoutInterval:30];*/
    [request setValue:@"application/json" forHTTPHeaderField:@"Accept"];
    if (postData != nil)
    {
        [request setHTTPMethod:@"POST"];
        [request setHTTPBody:[postData dataUsingEncoding:NSUTF8StringEncoding]];
    }    
    if (putData != nil)
    {
        [request setHTTPMethod:@"PUT"];
        [request setHTTPBody:[putData dataUsingEncoding:NSUTF8StringEncoding]];
    }
         
    NSURLResponse* response;
    NSError* error = nil;
    NSData* data = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:(&error)];
    
    if (error != nil)
    {
        (*result) = [[[OpenrestResult alloc] initError:[NSNumber numberWithInt:0] 
                                              withCode:OPENREST_ERROR_CODE_INTERNAL
                                              andError:[NSString stringWithFormat:@"%d", [error code]]
                                            andMessage:[error localizedDescription]] autorelease];
        NSLog(@"error = %@", [(*result) errorMessage]);
        return nil;
    }
    
    if (data == nil)
    {
        return nil;
    }
    
    NSString* content = [[[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding] autorelease];
    NSDictionary* json = [content openrest4cocoaJSONValue];
    if (json == nil)
    {
        NSLog(@"Error parsing JSON for: %@", content);
        NSLog(@"Put data: %@", putData);
        json = [content openrest4cocoaJSONValue];
        (*result) = [[[OpenrestResult alloc] initError:[NSNumber numberWithInt:0]
                                              withCode:OPENREST_ERROR_CODE_INTERNAL
                                              andError:@"json_parse"
                                            andMessage:@"json parsing error"] autorelease];
        NSLog(@"error = %@", [(*result) errorMessage]);
        return nil;
    }
    
    if ([json valueForKey:@"error"] != nil)
    {
        (*result) = [[[OpenrestResult alloc] initWithDictionary:json] autorelease];
        NSLog(@"error = %@", [(*result) errorMessage]);
        return nil;        
    }
    
    (*result) = [[[OpenrestResult alloc] initWithDictionary:json] autorelease];
    return json;
}

-(NSObject*)createObjectFromUrl:(NSString*)url ofClass:(NSString*)objectClass result:(OpenrestResult**)result postData:(NSString*)postData putData:(NSString*)putData
{
    NSDictionary* json = [self getJsonDictionary:url result:result postData:postData putData:putData];
    if (json == nil)
    {
        return nil;
    }
    return [[NSClassFromString(objectClass) alloc] initWithDictionary:[json valueForKey:@"value"]];
}

-(void)dealloc
{
    if (restaurantUri) [restaurantUri release];
    if (accessToken) [accessToken release];
    [super dealloc];
}

@end
