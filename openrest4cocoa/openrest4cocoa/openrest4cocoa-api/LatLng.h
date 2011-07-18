//
//  LatLng.h
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface LatLng : NSObject {
    double lat;
    double lng;
}

@property (nonatomic) double lat;
@property (nonatomic) double lng;

-(id)initWithDictionary:(NSDictionary*)data;
-(NSDictionary*)proxyForJson;

@end
