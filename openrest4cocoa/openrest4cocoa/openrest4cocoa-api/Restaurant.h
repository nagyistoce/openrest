//
//  Restaurant.h
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Organization.h"
#import "Contact.h"
#import "Address.h"
#import "ColorScheme.h"
#import "OpenrestAvailability.h"
#import "Status.h"
#import "LocalizedDictionary.h"

#define MESSAGE_TYPE_WELCOME                @"welcome"
#define MESSAGE_TYPE_ORDER_CONFIRMATION     @"order_confirmation"

@interface Restaurant : Organization 

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;

@property (nonatomic, retain) NSDictionary* messages;
@property (nonatomic, retain) OpenrestAvailability* openTimes;
@property (nonatomic, retain) OpenrestAvailability* deliveryTimes;
@property (nonatomic) bool inactive;
@property (nonatomic, retain) NSSet* paymentTypes;
@property (nonatomic, retain) NSDictionary* minPayments;
@property (nonatomic, retain) NSSet* deliveryInfos;
@property (nonatomic, retain) NSString* currency;
@property (nonatomic, retain) NSNumber* rank;
@property (nonatomic, retain) NSString* distributorId;
@property (nonatomic, retain) NSDictionary* cardInfos;

@end
