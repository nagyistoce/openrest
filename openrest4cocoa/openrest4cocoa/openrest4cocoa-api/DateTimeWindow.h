//
//  DateTimeWindow.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "OpenRestDate.h"

@interface DateTimeWindow : NSObject 
{}

@property (nonatomic, retain) OpenRestDate* start;
@property (nonatomic, retain) OpenRestDate* end;
@property (assign) BOOL available;


-(id)initWithDictionary:(NSDictionary*)data;

@end
