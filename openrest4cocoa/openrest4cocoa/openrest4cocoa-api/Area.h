//
//  Area.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface Area : NSObject {
    NSString* title;
    NSArray* polygon;
}

@property (nonatomic, retain) NSString* title;
@property (nonatomic, retain) NSArray* polygon;

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;

@end
