//
//  Area.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LocalizedDictionary.h"

@interface Area : NSObject {
    LocalizedDictionary* title;
    NSArray* polygon;
}

@property (nonatomic, retain) LocalizedDictionary* title;
@property (nonatomic, retain) NSArray* polygon;

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;

@end
