//
//  WeeklyTimeWindow.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface WeeklyTimeWindow : NSObject {
    int minuteOfWeek;
    int durationMins;
}

@property (nonatomic) int minuteOfWeek;
@property (nonatomic) int durationMins;

-(id)initWithDictionary:(NSDictionary*)data;

@end
