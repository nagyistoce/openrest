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

+(NSArray*)refactorJsonArray:(NSArray*)array toClass:(NSString*)classStr;
+(NSArray*)refactorJsonArrayOfArray:(NSArray*)array toClass:(NSString*)classStr;
+(NSString*)appendQueryToUrl:(NSString*)url query:(NSString*)query;
+(NSString*)getLocale;

@end
