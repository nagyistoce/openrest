//
//  Category.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LocalizedDictionary.h"

@interface Category : NSObject {
 
    /** The category's unique id. */
    NSString* categoryId;
    
    /** The restaurant's id. */
    NSString* restaurantId;
    
    /** The category's title. */
    LocalizedDictionary* title;
    
    /** The category's description. */
    LocalizedDictionary* description;
    
    /** The parent category's id. */
    NSString* parentCategoryId;
    
    /** The item-ids in this category. */
    NSArray* itemIds;
    
    /** Order priority. Higher means first in the list. */
    double priority;
    
    NSDictionary* properties;
}

@property (nonatomic, retain) NSString* categoryId;
@property (nonatomic, retain) NSString* restaurantId;
@property (nonatomic, retain) LocalizedDictionary* title;
@property (nonatomic, retain) LocalizedDictionary* description;
@property (nonatomic, retain) NSString* parentCategoryId;
@property (nonatomic, retain) NSArray* itemIds;
@property (nonatomic) double priority;
@property (nonatomic, retain) NSDictionary* properties;

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;

@end
