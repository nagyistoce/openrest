//
//  Tag.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LocalizedDictionary.h"

@interface Tag : NSObject {
    /** The tag's unique id. */
    NSString* tagId;
    
    /** The restaurant's id. */
    NSString* restaurantId;
    
    /** The tag's name, e.g. "drink", "sides". */
    LocalizedDictionary* title;
    
    /** Item ids. */
    NSArray* itemIds;
}

@property (nonatomic, retain) NSString* tagId;
@property (nonatomic, retain) NSString* restaurantId;
@property (nonatomic, retain) LocalizedDictionary* title;
@property (nonatomic, retain) NSArray* itemIds;

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;

@end
