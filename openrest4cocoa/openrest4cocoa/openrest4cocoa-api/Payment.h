//
//  Payment.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/6/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "CreditCard.h"

#define PAYMENT_TYPE_CASH     @"cash"
#define PAYMENT_TYPE_CREDIT   @"credit"
#define PAYMENT_TYPE_10BIS    @"10bis"

@interface Payment : NSObject {
    NSString* type;
    int amount;
    CreditCard* card;
}

@property (nonatomic, retain) NSString* type;
@property (nonatomic) int amount;
@property (nonatomic, retain) CreditCard* card;

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;
-(NSDictionary*)proxyForJson;

@end
