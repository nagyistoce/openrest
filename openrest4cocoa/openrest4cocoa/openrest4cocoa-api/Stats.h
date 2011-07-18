//
//  Stats.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/31/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "OpenRestDate.h"

#define STATS_GRANULARITY_DAY   @"day"
#define STATS_GRANULARITY_WEEK  @"week"
#define STATS_GRANULARITY_MONTH @"month"
#define STATS_GRANULARITY_YEAR  @"year"

@interface Stats : NSObject {
    OpenRestDate* date;
    int count;
    int total;
}

@property (nonatomic, retain) OpenRestDate* date;
@property (readwrite, assign) int count;
@property (readwrite, assign) int total;

-(id)initWithDictionary:(NSDictionary*)data;
-(NSDictionary*)proxyForJson;

@end
