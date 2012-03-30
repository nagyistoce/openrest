//
//  Restaurant.h
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Contact.h"
#import "Address.h"
#import "ColorScheme.h"
#import "LocalizedDictionary.h"

@interface Organization : NSObject

-(id)init;
-(id)initWithDictionary:(NSDictionary*)data;
-(NSMutableDictionary*)proxyForJson;

@property (nonatomic, retain) NSString* organizationId;
@property (nonatomic, retain) NSNumber* modified;
@property (nonatomic, retain) NSNumber* created;
@property (nonatomic, retain) LocalizedDictionary* title;
@property (nonatomic, retain) LocalizedDictionary* organizationDescription;
@property (nonatomic, retain) Contact* contact;
@property (nonatomic, retain) Address* address;
@property (nonatomic, retain) ColorScheme* colorScheme;
@property (nonatomic, retain) NSString* timezone;
@property (nonatomic, retain) NSString* link;
@property (nonatomic, retain) NSDictionary* properties;
@property (nonatomic, retain) NSSet* locales;
@property (nonatomic, retain) NSString* locale;
@property (nonatomic, retain) NSString* picture;
@property (nonatomic, retain) NSString* icon;
@property (nonatomic, retain) NSString* noImagePicture;
@property (nonatomic, retain) NSSet* apps;

@end
