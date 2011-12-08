//
//  Feedback.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 12/6/11.
//  Copyright (c) 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Contact.h"

#define FEEDBACK_TYPE_FEEDBACK  @"feedback"
#define FEEDBACK_TYPE_ERROR @"error"
#define FEEDBACK_TYPE_LOGDUMP @"log"

@interface Feedback : NSObject

@property (nonatomic, retain) NSString* type;
@property (nonatomic, retain) NSString* comment;
@property (nonatomic, retain) Contact* contact;
@property (nonatomic, retain) NSString* restaurantId;

-(id)initWithDictionary:(NSDictionary*)data;
-(NSDictionary*)proxyForJson;

@end
