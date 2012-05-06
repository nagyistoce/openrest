//
//  Utils.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface Utils : NSObject {
    
}

+(NSSet*)refactorJsonArrayToSet:(NSArray*)array toClass:(NSString*)classStr;
+(NSArray*)refactorJsonArray:(NSArray*)array toClass:(NSString*)classStr;
+(NSDictionary*)refactorJsonHash:(NSDictionary*)hash toClass:(NSString*)classStr;
+(NSArray*)refactorJsonArrayOfArray:(NSArray*)array toClass:(NSString*)classStr;
+(NSString*)appendQueryToUrl:(NSString*)url query:(NSString*)query;
+(NSString*)getLocale;

@end
