//
//  Delivery.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Address.h"

/** Delivery to an address of your choice. */
#define DELIVERY_TYPE_DELIVERY @"delivery"

/** Take-out from the restaurant. */
#define DELIVERY_TYPE_TAKEOUT @"takeout"

@interface Delivery : NSObject {
    NSString* type;
    Address* address;
    NSNumber* charge;
}

@property (nonatomic, retain) NSString* type;
@property (nonatomic, retain) Address* address;
@property (nonatomic, retain) NSNumber* charge;

-(id)initWithDictionary:(NSDictionary*)data;
-(NSDictionary*)proxyForJson;

@end
