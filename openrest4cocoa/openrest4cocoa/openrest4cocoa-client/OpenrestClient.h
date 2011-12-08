//
//  OpenrestClient.h
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Restaurant.h"
#import "RestaurantFull.h"
#import "Menu.h"
#import "Order.h"
#import "PostedOrder.h"
#import "OpenrestResult.h"
#import "Stats.h"
#import "Payment.h"
#import "Feedback.h"

@interface OpenrestClient : NSObject {
    NSString* restaurantUri;
    NSString* accessToken;
    NSString* ref;
}

@property (nonatomic, retain) NSString* restaurantUri;
@property (nonatomic, retain) NSString* accessToken;
@property (nonatomic, retain) NSString* ref;

-(Restaurant*)createRestaurant:(OpenrestResult**)result;
-(Menu*)createMenu:(OpenrestResult**)result;
-(Order*)getOrder:(NSString*)orderId result:(OpenrestResult**)result;
-(Order*)getOrderRestaurantView:(NSString*)orderId result:(OpenrestResult**)result;
-(NSArray*)getOrders:(NSString*)parameters result:(OpenrestResult**)result;
-(PostedOrder*)postOrder:(Order*)order comment:(NSString*)comment result:(OpenrestResult**)result;
-(Order*)saveOrder:(Order*)order confirmationCode:(NSString*)confirmationCode comment:(NSString*)comment result:(OpenrestResult**)result;
-(NSArray*)getStats:(NSString*)granularity since:(NSDate*)since result:(OpenrestResult**)result;

-(id)initWithRestaurantUri:(NSString*)_restaurantUri accessToken:(NSString*)_accessToken ref:(NSString*)ref;
-(id)initWithRestaurantUri:(NSString*)_restaurantUri ref:(NSString*)ref;

+(BOOL)sendFeedback:(Feedback*)feedback toHost:(NSString*)base andAccessToken:(NSString*)accessToken;

+(RestaurantFull*)createRestaurantFullNamed:(NSString*)name from:(NSString*)base result:(OpenrestResult**)result accessToken:(NSString*)accessToken ref:(NSString*)ref;

+(void)saveMeInfo:(NSDictionary*)info from:(NSString*)base andAccessToken:(NSString*)accessToken result:(OpenrestResult**)result ref:(NSString*)ref;

+(void)deletePayment:(Payment*)payment from:(NSString*)base andAccessToken:(NSString*)accessToken result:(OpenrestResult**)result ref:(NSString*)ref;

+(void)addPayment:(Payment*)payment from:(NSString*)base andAccessToken:(NSString*)accessToken result:(OpenrestResult**)result ref:(NSString*)ref;

@end
