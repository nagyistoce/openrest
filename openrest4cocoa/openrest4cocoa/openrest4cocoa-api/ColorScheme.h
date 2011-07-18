//
//  ColorScheme.h
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface ColorScheme : NSObject {
        NSString* theme;
        NSString* background;
        NSString* font;
        NSString* border;
        NSString* imageBackground;
        NSString* imageBorder;
        NSString* buttonFont;
        NSString* buttonUp;
        NSString* buttonDown;
        NSString* buttonOver;
        NSString* categoryFont;
        NSString* categoryUp;
        NSString* categoryDown;
        NSString* categoryOver;
}

@property (nonatomic, retain) NSString* theme;
@property (nonatomic, retain) NSString* background;
@property (nonatomic, retain) NSString* font;
@property (nonatomic, retain) NSString* border;
@property (nonatomic, retain) NSString* imageBackground;
@property (nonatomic, retain) NSString* imageBorder;
@property (nonatomic, retain) NSString* buttonFont;
@property (nonatomic, retain) NSString* buttonUp;
@property (nonatomic, retain) NSString* buttonDown;
@property (nonatomic, retain) NSString* buttonOver;
@property (nonatomic, retain) NSString* categoryFont;
@property (nonatomic, retain) NSString* categoryUp;
@property (nonatomic, retain) NSString* categoryDown;
@property (nonatomic, retain) NSString* categoryOver;

-(id)initWithDictionary:(NSDictionary*)data;
+(UIColor*)toUIColor:(NSString*)value alpha:(float)alpha;

@end
