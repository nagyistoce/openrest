//
//  LocalizedDictionary.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 10/2/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface LocalizedDictionary : NSObject {
    
}

+(void)setDefaultLocale:(NSString*)locale;
+(id)dictionary;
+(id)dictionaryWithDictionary:(NSDictionary *)otherDictionary;
-(id)initWithDictionary:(NSDictionary *)otherDictionary;
-(NSString*)objectForKey:(NSString*)key;
-(NSString*)description;

@end
