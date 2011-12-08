//
//  OrderItem.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface OrderItem : NSObject {
    /** Item id. */
    NSString* itemId;
    
    /**
     * The ordered-item's variations.
     * 
     * Submitting an OrderItem with empty variations means the defaults should be assumed
     * for variationsChoices.
     */
    NSArray* variations;
    
    /** The ordered-item's chosen variations. */
    NSArray* variationsChoices;
    
    /** Textual comment regarding the item. */
    NSString* comment;
    
    /** price of the item. */
    int price;
    
    /** Number of times this order-item was ordered. */
    int count;   
}

@property (nonatomic, retain) NSString* itemId;
@property (nonatomic, retain) NSArray* variations;
@property (nonatomic, retain) NSArray* variationsChoices;
@property (nonatomic, retain) NSString* comment;
@property (nonatomic) int price;
@property (nonatomic) int count;

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;

-(int)getTotalPrice;
-(NSDictionary*)proxyForJson;

@end
