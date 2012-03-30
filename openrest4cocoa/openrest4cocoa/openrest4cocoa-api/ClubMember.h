//
//  ClubMember.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 3/5/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ClubMember : NSObject

@property (nonatomic, retain) NSString* restaurantId;
@property (nonatomic, retain) NSString* memberId;
@property (nonatomic, retain) NSString* phone;
@property (nonatomic, retain) NSArray* clubIds;

-(id)initWithDictionary:(NSDictionary*)data;
-(NSDictionary*)proxyForJson;

@end
