//
//  CreditCard.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/6/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface CreditCard : NSObject {
    NSString* number;
    /** 1-based */
    NSNumber* expireMonth;
    NSNumber* expireYear;
    NSString* holderId;
    NSString* holderName;
    NSString* csc;
    NSString* type;
    bool anonymized;   
}
@property (nonatomic, retain) NSString* number;
@property (nonatomic, retain) NSNumber* expireMonth;
@property (nonatomic, retain) NSNumber* expireYear;
@property (nonatomic, retain) NSString* holderId;
@property (nonatomic, retain) NSString* holderName;
@property (nonatomic, retain) NSString* type;
@property (nonatomic, retain) NSString* csc;
@property (nonatomic) bool anonymized;   

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;
-(NSDictionary*)proxyForJson;

@end
