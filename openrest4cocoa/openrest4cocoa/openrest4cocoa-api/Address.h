//
//  Address.h
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LatLng.h"

@interface Address : NSObject {
    NSString* city;
    NSString* street;
    NSString* number;
    NSString* apt;
    NSString* floor;
    NSString* entrance;
    NSString* comment;
    LatLng* latLng;
}

@property (nonatomic, retain) NSString* city;
@property (nonatomic, retain) NSString* street;
@property (nonatomic, retain) NSString* number;
@property (nonatomic, retain) NSString* apt;
@property (nonatomic, retain) NSString* floor;
@property (nonatomic, retain) NSString* entrance;
@property (nonatomic, retain) NSString* comment;
@property (nonatomic, retain) LatLng* latLng;

-(id)initWithDictionary:(NSDictionary*)data;
-(NSDictionary*)proxyForJson;

@end
