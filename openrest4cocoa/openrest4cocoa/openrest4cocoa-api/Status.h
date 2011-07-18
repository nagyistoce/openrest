//
//  Status.h
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface Status : NSObject {
    NSString* status;
    NSNumber* until;
}

@property (nonatomic, retain) NSString* status;
@property (nonatomic, retain) NSNumber* until;

-(id)initWithDictionary:(NSDictionary*)data;

@end
