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
+(NSObject*)createObjectFromUrl:(NSString*)url ofClass:(NSString*)objectClass result:(OpenrestResult**)result postData:(NSString*)postData putData:(NSString*)putData accessToken:(NSString*)accessToken ref:(NSString*)ref;
+(NSDictionary*)getJsonDictionary:(NSString*)url result:(OpenrestResult**)result postData:(NSString*)postData putData:(NSString*)putData delete:(BOOL)delete accessToken:(NSString*)accessToken ref:(NSString*)ref;

@end

@implementation OpenrestClient

@synthesize restaurantUri;
@synthesize accessToken;
@synthesize ref;

+(RestaurantFull*)createRestaurantFullNamed:(NSString*)name from:(NSString*)base result:(OpenrestResult**)result accessToken:(NSString*)accessToken ref:(NSString*)ref
{
    NSString* url = [NSString stringWithFormat:@"%@/restaurants.full/?ids=%@", base, name];
    
    NSDictionary* json = [OpenrestClient getJsonDictionary:url result:result postData:nil putData:nil delete:FALSE accessToken:accessToken ref:ref];
    if (json == nil)
    {
        return nil;
    }
    
    NSArray* value = [json objectForKey:@"value"];
    RestaurantFull* ret = [[RestaurantFull alloc] initWithDictionary:[value objectAtIndex:0]];
    
    return ret;
}

-(id)initWithRestaurantUri:(NSString*)_restaurantUri accessToken:(NSString*)_accessToken ref:(NSString*)_ref
{
    if ((self = [super init]))
    {
        [self setRestaurantUri:_restaurantUri];
        [self setAccessToken:[_accessToken stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
        [self setRef:_ref];
    }
    return self;
}

-(id)initWithRestaurantUri:(NSString*)_restaurantUri ref:(NSString*)_ref
{
    return [self initWithRestaurantUri:_restaurantUri accessToken:nil ref:_ref];
}

-(Restaurant*)createRestaurant:(OpenrestResult**)result;
{
    return (Restaurant*)[OpenrestClient createObjectFromUrl:restaurantUri ofClass:@"Restaurant" result:result postData:nil putData:nil accessToken:[self accessToken] ref:ref];    
}

-(Menu*)createMenu:(OpenrestResult**)result;
{
    return (Menu*)[OpenrestClient createObjectFromUrl:[NSString stringWithFormat:@"%@/menu", restaurantUri] ofClass:@"Menu" result:result postData:nil putData:nil accessToken:[self accessToken] ref:ref];    
}

-(Order*)getOrder:(NSString*)orderId result:(OpenrestResult**)result
{
    return (Order*)[OpenrestClient createObjectFromUrl:[NSString stringWithFormat:@"%@/orders/%@", restaurantUri, orderId] ofClass:@"Order" result:result postData:nil putData:nil accessToken:[self accessToken] ref:ref];        
}

-(Order*)getOrderRestaurantView:(NSString*)orderId result:(OpenrestResult**)result
{
    return (Order*)[OpenrestClient createObjectFromUrl:[NSString stringWithFormat:@"%@/orders/%@?restaurantView=true", restaurantUri, orderId] ofClass:@"Order" result:result postData:nil putData:nil accessToken:[self accessToken] ref:ref];        
}

-(NSArray*)getStats:(NSString*)granularity since:(NSDate*)since result:(OpenrestResult**)result
{
    NSString* sinceString = [NSString string];
    if (since != NULL)
    {
        NSDateFormatter *f = [[NSDateFormatter alloc] init];
        [f setDateFormat:@"yyyy-MM-dd 00:00"];
        sinceString = [[f stringFromDate:since] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    }
    NSDictionary* json = [OpenrestClient 
                          getJsonDictionary:[NSString stringWithFormat:@"%@/stats?granularity=%@%@", restaurantUri, granularity,
                                             (since != NULL ? [NSString stringWithFormat:@"&since=%@", sinceString] : @"")]
                          result:result 
                          postData:nil putData:nil delete:FALSE accessToken:[self accessToken] ref:NULL];

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
        data  = [OpenrestClient getJsonDictionary:[NSString stringWithFormat:@"%@/orders/?%@", restaurantUri, parameters] result:result postData:nil putData:nil delete:FALSE accessToken:[self accessToken] ref:ref];
    }
    else
    {
        data  = [OpenrestClient getJsonDictionary:[NSString stringWithFormat:@"%@/orders/", restaurantUri] result:result postData:nil putData:nil delete:FALSE accessToken:[self accessToken] ref:ref];
    }
    
    if (data == nil) return nil;

    if ([data valueForKey:@"value"] == nil)
    {
        return [NSArray array];
    }
    return [Utils refactorJsonArray:[data valueForKey:@"value"] toClass:@"Order"];
}


-(PostedOrder*)postOrder:(Order*)order comment:(NSString*)comment result:(OpenrestResult**)result
{
    NSDictionary* json = [OpenrestClient getJsonDictionary:[NSString stringWithFormat:@"%@/orders/?comment=%@", restaurantUri, [comment stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]]
                                                    result:result postData:[order openrest4cocoaJSONRepresentation] putData:NULL delete:FALSE accessToken:[self accessToken] ref:ref];
    if (json == nil)
    {
        return nil;
    }
    return [[[NSClassFromString(@"PostedOrder") alloc] initWithDictionary:[json valueForKey:@"value"]] autorelease];
}

+(void)saveMeInfo:(NSDictionary*)info from:(NSString*)base andAccessToken:(NSString*)accessToken result:(OpenrestResult**)result ref:(NSString *)ref
{
   [OpenrestClient getJsonDictionary:[NSString stringWithFormat:@"%@/me/info", base]
                              result:result postData:NULL putData:[info openrest4cocoaJSONRepresentation] delete:FALSE accessToken:accessToken ref:ref];
}

+(void)deletePayment:(Payment*)payment from:(NSString*)base andAccessToken:(NSString*)accessToken result:(OpenrestResult**)result ref:(NSString *)ref
{
    [OpenrestClient getJsonDictionary:[NSString stringWithFormat:@"%@/me/payments/%@", base, payment.storedId]
                               result:result postData:NULL putData:NULL delete:YES accessToken:accessToken ref:ref];    
}

+(void)addPayment:(Payment*)payment from:(NSString*)base andAccessToken:(NSString*)accessToken result:(OpenrestResult**)result ref:(NSString*)ref
{
    [OpenrestClient getJsonDictionary:[NSString stringWithFormat:@"%@/me/payments/", base]
                               result:result postData:[payment openrest4cocoaJSONRepresentation] putData:NULL delete:NO accessToken:accessToken ref:ref];
}


-(Order*)saveOrder:(Order*)order confirmationCode:(NSString*)confirmationCode comment:(NSString*)comment result:(OpenrestResult**)result
{
    NSMutableString* url = [NSMutableString stringWithFormat:@"%@/orders/%@?comment=%@", restaurantUri, [order orderId], [comment stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
    if (confirmationCode)
    {
        [url appendFormat:@"&confirmationToken=%@", [confirmationCode stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
    }
    NSDictionary* json = [OpenrestClient getJsonDictionary:url result:result postData:nil putData:[order openrest4cocoaJSONRepresentation] delete:FALSE accessToken:[self accessToken] ref:ref];

    if (json == nil)
    {
        return nil;
    }
    return [[[NSClassFromString(@"Order") alloc] initWithDictionary:[json valueForKey:@"value"]] autorelease];
}

+(BOOL)sendFeedback:(Feedback*)feedback toHost:(NSString*)base andAccessToken:(NSString*)accessToken
{
    NSMutableString* url = [NSMutableString stringWithFormat:@"%@/feedback/", base];
    
    OpenrestResult* result;
    NSDictionary* json = [OpenrestClient getJsonDictionary:url 
                                                    result:&result 
                                                  postData:[feedback openrest4cocoaJSONRepresentation] 
                                                   putData:NULL delete:FALSE accessToken:accessToken ref:NULL];
    
    if (json == nil)
    {
        return FALSE;
    }
    
    return YES;    
}

+(NSDictionary*)getJsonDictionary:(NSString*)url result:(OpenrestResult**)result postData:(NSString*)postData putData:(NSString*)putData delete:(BOOL)delete accessToken:(NSString*)accessToken ref:(NSString*)ref
{
    if (accessToken != nil)
    {
        url = [Utils appendQueryToUrl:url query:[NSString stringWithFormat:@"access_token=%@",
                                                 accessToken]];
    }
    if (ref != nil)
    {
        url = [Utils appendQueryToUrl:url query:[NSString stringWithFormat:@"ref=%@", ref]];
    }
    
    NSLog(@"request: %@", url);
    NSMutableURLRequest* request = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:url]];
                                   /* cachePolicy:NSURLRequestReloadIgnoringLocalAndRemoteCacheData 
                                                    timeoutInterval:30];*/
    [request setValue:@"application/json" forHTTPHeaderField:@"Accept"];
    [request setValue:@"application/json" forHTTPHeaderField:@"Content-Type"];
    if (postData != nil)
    {
        [request setHTTPMethod:@"POST"];
        [request setHTTPBody:[postData dataUsingEncoding:NSUTF8StringEncoding]];
        NSLog(@"POST DATA: %@", postData); 
    }    
    if (putData != nil)
    {
        [request setHTTPMethod:@"PUT"];
        [request setHTTPBody:[putData dataUsingEncoding:NSUTF8StringEncoding]];
    }
    if (delete)
    {
        [request setHTTPMethod:@"DELETE"];        
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
    NSLog(@"CONTENT = %@", content);
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

+(NSObject*)createObjectFromUrl:(NSString*)url ofClass:(NSString*)objectClass result:(OpenrestResult**)result postData:(NSString*)postData putData:(NSString*)putData accessToken:(NSString*)accessToken ref:(NSString*)ref
{
    NSDictionary* json = [self getJsonDictionary:url result:result postData:postData putData:putData delete:FALSE accessToken:accessToken ref:ref];
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
