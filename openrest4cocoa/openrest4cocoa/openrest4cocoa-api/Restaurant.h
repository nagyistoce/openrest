//
//  Restaurant.h
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Contact.h"
#import "Address.h"
#import "ColorScheme.h"
#import "OpenrestAvailability.h"
#import "Status.h"
#import "LocalizedDictionary.h"

#define MESSAGE_TYPE_WELCOME                @"welcome"
#define MESSAGE_TYPE_ORDER_CONFIRMATION     @"order_confirmation"

@interface Restaurant : NSObject {

    /** The restaurant's unique id. */
    NSString* restaurantId;
    
    /** The restaurant's name. */
    LocalizedDictionary* title;
    
    /** The restaurant's description or tagline. */
    LocalizedDictionary* restaurantDescription;
    
    /** The restaurant's contact. */
    Contact* contact;
    
    /** The address of this restaurant. */
    Address* address;
    
    NSDictionary* messages;
    
    /** The color scheme. */
    ColorScheme* colorScheme;
    
    /** Restaurant availability. */
    OpenrestAvailability* openTimes;
    
    /** Deliveries availability. */
    OpenrestAvailability* deliveryTimes;
    
    /** Whether the restaurant is deactivated (i.e. suspended or disabled). */
    bool inactive;
    	    
    /** The current status. */
    Status* status;
    
    /** The current delivery status. */
    Status* deliveryStatus;
    
    /**
     * The restaurant's timezone.
     * @see http://en.wikipedia.org/wiki/List_of_tz_database_time_zones
     */
    NSString* timezone;
    
    /** Available payment methods. */
    NSArray* paymentTypes;
    
    /**
     * Maps available payment types to minimal charge allowed per payment, e.g.
     * "credit cards can only be used for paying $5 or more". Non-referenced
     * payment types have zero minimum by default.
     */
    NSDictionary* minPayments;
    
    /** Official restaurant web-site URL. */
    NSString* link;
    
    /** Restaurant picture URL (direct link). */
    NSString* picture;
    
    /** Restaurant icon URL (direct link). */
    NSString* icon;
    
    /** Restaurant delivery areas */
    NSArray* deliveryInfos;
    
    /**
     * Map of user-defined extended properties. Developers should use unique
     * keys, e.g. "com.googlecode.openrestext".
     */
    NSDictionary* properties;
    
    /* Locale attirbutes */
    NSArray* locales;
    NSString* locale;
    
    /* Currency */
    NSString* currency;
    
    NSNumber* rank;
    
    NSArray* apps;
}

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;

@property (nonatomic, retain) NSString* restaurantId;
@property (nonatomic, retain) LocalizedDictionary* title;
@property (nonatomic, retain) LocalizedDictionary* restaurantDescription;
@property (nonatomic, retain) Contact* contact;
@property (nonatomic, retain) Address* address;
@property (nonatomic, retain) NSDictionary* messages;
@property (nonatomic, retain) ColorScheme* colorScheme;
@property (nonatomic, retain) OpenrestAvailability* openTimes;
@property (nonatomic, retain) OpenrestAvailability* deliveryTimes;
@property (nonatomic) bool inactive;
@property (nonatomic, retain) Status* status;
@property (nonatomic, retain) Status* deliveryStatus;
@property (nonatomic, retain) NSString* timezone;
@property (nonatomic, retain) NSArray* paymentTypes;
@property (nonatomic, retain) NSDictionary* minPayments;
@property (nonatomic, retain) NSString* link;
@property (nonatomic, retain) NSString* picture;
@property (nonatomic, retain) NSString* icon;
@property (nonatomic, retain) NSArray* deliveryInfos;
@property (nonatomic, retain) NSDictionary* properties;
@property (nonatomic, retain) NSArray* locales;
@property (nonatomic, retain) NSString* locale;
@property (nonatomic, retain) NSString* currency;
@property (nonatomic, retain) NSNumber* rank;
@property (nonatomic, retain) NSArray* apps;
@end
