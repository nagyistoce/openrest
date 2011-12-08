//
//  LogEntry.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 8/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "User.h"

@interface LogEntry : NSObject {
    NSNumber* timestamp;
    User* user;
    NSString* comment;
}

@property (nonatomic, retain) NSNumber* timestamp;
@property (nonatomic, retain) User* user;
@property (nonatomic, retain) NSString* comment;

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;
-(NSDictionary*)proxyForJson;

@end
