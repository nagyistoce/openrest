//
//  Item.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "OpenrestAvailability.h"
#import "Status.h"
#import "LocalizedDictionary.h"

@interface Item : NSObject {
    /** The item's unique id. */
    NSString* itemId;
    
    /** The restaurant's id. */
    NSString* restaurantId;
    
    /** The item's name. */
    LocalizedDictionary* title;
    
    /** The item's one line description. */
    LocalizedDictionary* itemDescription;
    
    /** The item's price, in "cents". */
    int price;
    
    /** List of possible variations. */
    NSArray* variations;
    
    /** Time windows in which this item is regularly available. */
    OpenrestAvailability* availability;
    
    /** Human readable availability NSString*. */
    NSString* availabilityStr;
    
    /** Whether the item is deactivated (i.e. suspended or disabled). */
    bool inactive;
    
    /** The current status. */
    Status* status;
    
    /** Item picture URL (direct link). */
    NSString* picture;
}

@property (nonatomic, retain) NSString* itemId;
@property (nonatomic, retain) NSString* restaurantId;
@property (nonatomic, retain) LocalizedDictionary* title;
@property (nonatomic, retain) LocalizedDictionary* itemDescription;
@property (nonatomic) int price;
@property (nonatomic, retain) NSArray* variations;
@property (nonatomic, retain) OpenrestAvailability* availability;
@property (nonatomic, retain) NSString* availabilityStr;
@property (nonatomic) bool inactive;
@property (nonatomic, retain) Status* status;
@property (nonatomic, retain) NSString* picture;

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;

@end
