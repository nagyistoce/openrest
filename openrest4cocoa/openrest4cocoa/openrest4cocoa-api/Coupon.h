//
//  Coupon.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 7/12/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LocalizedDictionary.h"

#define COUPON_TYPE_DISCOUNT        @"discount"
#define COUPON_TYPE_M_PLUS_N        @"m_plus_n"

@interface Coupon : NSObject {
    
    /** The coupon's type. */
    NSString* type;
    
    /** The coupon's user-friendly short name. */
    LocalizedDictionary* title;
    
    /** The coupon's user-friendly description. */
    LocalizedDictionary* description;
    
    /** Maximum number of times this coupon can be used in a single order. */
    NSNumber* maxNumAllowed;
    
    /** Whether or not other coupons can be used with this one. */
    bool othersAllowed;
}

@property (nonatomic, retain) NSString* type;
@property (nonatomic, retain) LocalizedDictionary* title;
@property (nonatomic, retain) LocalizedDictionary* description;
@property (nonatomic, retain) NSNumber* maxNumAllowed;
@property (assign) bool othersAllowed;

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;
-(NSDictionary*)proxyForJson;

@end
