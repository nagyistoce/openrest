//
//  LatLng.h
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface LatLng : NSObject {
    NSNumber* lat;
    NSNumber* lng;
}

@property (nonatomic, retain) NSNumber* lat;
@property (nonatomic, retain) NSNumber* lng;

-(id)initWithDictionary:(NSDictionary*)data;
-(NSDictionary*)proxyForJson;

@end
