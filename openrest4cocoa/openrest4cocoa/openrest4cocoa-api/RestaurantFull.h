//
//  RestaurantFull.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 7/20/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Restaurant.h"
#import "Menu.h"
#import "Charge.h"

@interface RestaurantFull : NSObject {
    Restaurant* restaurant;
    Menu* menu;
    NSArray* charges;
}

@property (nonatomic, retain) Restaurant* restaurant;
@property (nonatomic, retain) Menu* menu;
@property (nonatomic, retain) NSArray* charges;

-(id)initWithDictionary:(NSDictionary*)data;
-(NSDictionary*)proxyForJson;

@end
