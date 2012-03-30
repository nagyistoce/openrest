//
//  Distributor.h
//  openrest4cocoa
//
//  Created by Yoav Amit on 2/1/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Organization.h"
#import "LocalizedDictionary.h"
#import "ColorScheme.h"
#import "Contact.h"
#import "Address.h"

@interface Distributor : Organization

@property (nonatomic, retain) NSString* facebookAppId;

@end
