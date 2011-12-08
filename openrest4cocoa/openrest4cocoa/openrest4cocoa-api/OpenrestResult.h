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

#define OPENREST_ERROR_NO_PERMISSION        @"no_permission"
/*RESPONSE_ERROR_MSG_INVALID_SAVED_PAYMENT = "Invalid saved payment";
RESPONSE_ERROR_INVALID_DATA = "invalid_data";
RESPONSE_ERROR_EMPTY_ADMIN_LIST = "empty_admin_list";
RESPONSE_ERROR_EMPTY_MANAGERS_LIST = "empty_manager_list";
RESPONSE_ERROR_CC_EXPIRED = "cc_expired";
RESPONSE_ERROR_INVALID_CC_NUMBER = "invalid_cc_number";
RESPONSE_ERROR_INVALID_DATE = "invalid_date";
RESPONSE_ERROR_CANNOT_SUBMIT_ORDER = "cannot_submit_order";
RESPONSE_ERROR_ADDRESS_NOT_IN_RANGE = "address_not_in_range";
RESPONSE_ERROR_NOT_FOUND = "not_found";
RESPONSE_ERROR_FILE_SIZE_EXCEEDS_LIMIT = "file_size_exceeds_limit";
RESPONSE_ERROR_RESTAURANT_ID_EXISTS = "restaurant_id_exists";
RESPONSE_ERROR_INTERNAL = "internal";
RESPONSE_ERROR_UNKNOWN_ADDRESS = "unknown_address";
RESPONSE_ERROR_CANNOT_DELETE_REFERENCED = "cannot_delete_referenced";
RESPONSE_ERROR_PAYMENT_METHOD_UNAVAILABLE = "payment_method_unavailable";
*/

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
