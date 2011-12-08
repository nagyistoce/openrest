//
//  App.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 11/22/11.
//  Copyright (c) 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

#define APP_TYPE_CLIENT     @"client"
#define APP_TYPE_EMPLOYEE   @"employee"


@interface App : NSObject
{
    
}

@property (nonatomic, retain) NSString* type;
@property (nonatomic, retain) NSString* platform;
@property (nonatomic, retain) NSString* appId;
@property (nonatomic, retain) NSString* version;
@property (nonatomic, retain) NSString* link;

-(id)initWithDictionary:(NSDictionary*)data;

@end
