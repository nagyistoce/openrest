//
//  CardInfo.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/3/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CardInfo : NSObject

@property (nonatomic, retain) NSString* formId;
@property (assign) BOOL active;

-(id)initWithDictionary:(NSDictionary*)data;
-(NSDictionary*)proxyForJson;

@end
