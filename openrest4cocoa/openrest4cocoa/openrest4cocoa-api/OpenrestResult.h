//
//  OpenrestError.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/6/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

#define OPENREST_ERROR_CODE_SUCCESS  (-1)
#define OPENREST_ERROR_CODE_INTERNAL (0)
#define OPENREST_ERROR_CODE_SERVER   (1)

@interface OpenrestResult : NSObject {
    NSNumber* timestamp;
    int code;
    NSString* error;
    NSString* errorMessage;
}

@property (nonatomic, retain) NSNumber* timestamp;
@property (nonatomic) int code;
@property (nonatomic, retain) NSString* error;
@property (nonatomic, retain) NSString* errorMessage;

-(id)initSuccess:(NSNumber*)_timestamp;
-(id)initError:(NSNumber*)_timestamp withCode:(int)_code andError:(NSString*)_error andMessage:(NSString*)_message;
-(id)initWithDictionary:(NSDictionary*)data;
-(bool)isError;

@end
