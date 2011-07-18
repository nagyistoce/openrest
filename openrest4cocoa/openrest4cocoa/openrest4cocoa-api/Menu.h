//
//  Menu.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Item.h"

@interface Menu : NSObject {
    NSArray* items;
    NSArray* tags;
    NSArray* categories;
}

@property (nonatomic, retain) NSArray* items;
@property (nonatomic, retain) NSArray* tags;
@property (nonatomic, retain) NSArray* categories;

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;
-(Item*)itemById:(NSString*)itemId;
@end
