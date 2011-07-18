//
//  User.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/6/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface User : NSObject {
    NSString* userId;
    NSString* ipAddress;
    NSString* fwdIpAddresses;
}

@property (nonatomic, retain) NSString* userId;
@property (nonatomic, retain) NSString* ipAddress;
@property (nonatomic, retain) NSString* fwdIpAddresses;

-(id)initWithDictionary:(NSDictionary*)data;

@end
