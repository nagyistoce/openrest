//
//  OpenrestClient.h
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Restaurant.h"
#import "Menu.h"
#import "Order.h"
#import "OpenrestResult.h"
#import "Stats.h"

@interface OpenrestClient : NSObject {
    NSString* restaurantUri;
    NSString* accessToken; // TODO!
}

@property (nonatomic, retain) NSString* restaurantUri;
@property (nonatomic, retain) NSString* accessToken;

-(Restaurant*)createRestaurant:(OpenrestResult**)result;
-(Menu*)createMenu:(OpenrestResult**)result;
-(Order*)getOrder:(NSString*)orderId result:(OpenrestResult**)result;
-(NSArray*)getOrders:(NSString*)parameters result:(OpenrestResult**)result;
-(void)saveOrder:(Order*)order result:(OpenrestResult**)result;
-(NSArray*)getStats:(NSString*)granularity result:(OpenrestResult**)result;

-(id)initWithRestaurantUri:(NSString*)_restaurantUri accessToken:(NSString*)_accessToken;
-(id)initWithRestaurantUri:(NSString*)_restaurantUri;

@end
