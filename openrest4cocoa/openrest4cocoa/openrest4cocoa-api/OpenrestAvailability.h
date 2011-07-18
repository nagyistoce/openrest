//
//  Availability.h
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface OpenrestAvailability : NSObject {
    NSArray* weekly;
    NSArray* exceptions;
}

@property (nonatomic, retain) NSArray* weekly;
@property (nonatomic, retain) NSArray* exceptions;

-(id)initWithDictionary:(NSDictionary*)data;

@end