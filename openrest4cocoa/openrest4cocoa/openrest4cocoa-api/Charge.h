//
//  Charge.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 7/12/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Coupon.h"
#import "LocalizedDictionary.h"

#define CHARGE_TYPE_DELIVERY    @"delivery"
#define CHARGE_TYPE_COUPON      @"coupon"
#define CHARGE_TYPE_CLUB_COUPON @"club_coupon"
#define CHARGE_TYPE_TAX         @"tax"

#define CHARGE_AMOUNT_RULE_TYPE_FIXED          @"fixed"
#define CHARGE_AMOUNT_RULE_TYPE_PERCENTAGE     @"percentage"
#define CHARGE_AMOUNT_RULE_TYPE_VARIABLE       @"variable"

#define TAG_MODE_INCLUDE        @"include"
#define TAG_MODE_EXCLUDE        @"exclude"

@interface Charge : NSObject {
    /** Charge id. */
    NSString* chargeId;
    
    /** Restaurant id. */
    NSString* restaurantId;
    
    /** Charge type. */
    NSString* type;
    
    /** Charge priority. Lower numbers appear first in the list. */
    NSNumber* priority;
    
    /** Optional activation code, e.g. GoDaddy-style, member id. */
    NSString* code;
    
    NSString* clubId;
    
    /** Items for which the charge applies, null if applies for every item. */
    NSString* tagId;
    
    /** Tag mode: inclusive or exclusive. */
    NSString* tagMode;
    
    /** Charge amount rule type. */
    NSString* amountRuleType;
    
    /**
     * Charge amount rule (cents for fixed amounts, basis points for percentage
     * amounts, undefined for variable amounts).
     * 
     * Basis points are 1/100th of a percentage, e.g. -500 bp referes to 5% discount. 
     * 
     * Positive numbers are extra charges, negatives are discounts.
     */
    NSNumber* amountRule;
    
    /** Coupon information (valid for CHARGE_TYPE_COUPON and CHARGE_TYPE_CLUB_COUPON). */
    Coupon* coupon;
    
    /** Bottom-line charge amount (in cents). */
    NSNumber* amount;
}

@property (nonatomic, retain) NSString* chargeId;
@property (nonatomic, retain) NSString* restaurantId;
@property (nonatomic, retain) NSString* type;
@property (nonatomic, retain) NSNumber* priority;
@property (nonatomic, retain) NSString* code;
@property (nonatomic, retain) NSString* clubId;
@property (nonatomic, retain) NSString* tagId;
@property (nonatomic, retain) NSString* tagMode;
@property (nonatomic, retain) NSString* amountRuleType;
@property (nonatomic, retain) NSNumber* amountRule;
@property (nonatomic, retain) Coupon* coupon;
@property (nonatomic, retain) NSNumber* amount;

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;
-(NSDictionary*)proxyForJson;

@end
