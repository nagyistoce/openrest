//
//  OpenRestDate.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/31/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface OpenRestDate : NSObject {
    int year;
    int month;
    int day;
    int hour;
    int minute;
}

@property (readwrite, assign) int year;
@property (readwrite, assign) int month;
@property (readwrite, assign) int day;
@property (readwrite, assign) int hour;
@property (readwrite, assign) int minute;

-(id)initWithDictionary:(NSDictionary*)data;
-(NSDictionary*)proxyForJson;

@end
