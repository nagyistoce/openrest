//
//  DeliveryInfo.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Area.h"

#define DELIVERY_TYPE_DELIVERY   @"delivery"
#define DELIVERY_TYPE_TAKEOUT    @"takeout"

@interface DeliveryInfo : NSObject {
    NSString* type;
    Area* area;
    int minOrderPrice;
    int charge;
    int delayMins;
    bool inactive;
}

@property (nonatomic, retain) NSString* type;
@property (nonatomic, retain) Area* area;
@property (nonatomic) int minOrderPrice;
@property (nonatomic) int charge;
@property (nonatomic) int delayMins;
@property (nonatomic) bool inactive;

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;

@end
