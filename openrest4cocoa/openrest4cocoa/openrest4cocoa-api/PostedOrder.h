//
//  PostedOrder.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 8/24/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Order.h"
#import "LocalizedDictionary.h"

@interface PostedOrder : NSObject {
    Order* order;
    LocalizedDictionary* message;
}

@property (nonatomic, retain) Order* order;
@property (nonatomic, retain) LocalizedDictionary* message;

-(id)initWithDictionary:(NSDictionary*)data;


@end
