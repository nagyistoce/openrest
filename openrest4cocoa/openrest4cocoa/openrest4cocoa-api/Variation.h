//
//  Variation.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LocalizedDictionary.h"

#define VARIATION_DISPLAY_TYPE_DIFF @"diff"
#define VARIATION_DISPLAY_TYPE_CHOICE @"choice"

@interface Variation : NSObject {
    /** The variations's name, e.g. "sides", "degree of cooking". */
    LocalizedDictionary* title;
    
    /** The set's name, e.g. "drink", "sides". */
    NSString* tagId;
    
    /** Minimum number of items to select. */
    int minNumAllowed;
    
    /** Maximum number of items to select. */
    int maxNumAllowed;
    
    /** Items' base prices. Non-referenced items are free by default. */
    NSDictionary* prices;
    
    /** Default selected item ids. */
    NSSet* defaults;
    
    /** Display type for human-readable printing. */
    NSString* displayType;
}

@property (nonatomic, retain) LocalizedDictionary* title;
@property (nonatomic, retain) NSString* tagId;
@property (nonatomic) int minNumAllowed;
@property (nonatomic) int maxNumAllowed;
@property (nonatomic, retain) NSDictionary* prices;
@property (nonatomic, retain) NSSet* defaults;
@property (nonatomic, retain) NSString* displayType;

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;

-(NSDictionary*)proxyForJson;

@end
