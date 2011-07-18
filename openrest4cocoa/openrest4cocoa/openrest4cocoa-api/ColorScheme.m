//
//  ColorScheme.m
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "ColorScheme.h"


@implementation ColorScheme

@synthesize theme;
@synthesize background;
@synthesize font;
@synthesize border;
@synthesize imageBackground;
@synthesize imageBorder;
@synthesize buttonFont;
@synthesize buttonUp;
@synthesize buttonDown;
@synthesize buttonOver;
@synthesize categoryFont;
@synthesize categoryUp;
@synthesize categoryDown;
@synthesize categoryOver;

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        [self setTheme:[data valueForKey:@"theme"]];
        [self setBackground:[data valueForKey:@"background"]];
        [self setFont:[data valueForKey:@"font"]];
        [self setBorder:[data valueForKey:@"border"]];
        [self setImageBackground:[data valueForKey:@"imageBackground"]];
        [self setImageBorder:[data valueForKey:@"imageBorder"]];
        [self setButtonFont:[data valueForKey:@"buttonFont"]];
        [self setButtonUp:[data valueForKey:@"buttonUp"]];
        [self setButtonDown:[data valueForKey:@"buttonDown"]];
        [self setButtonOver:[data valueForKey:@"buttonOver"]];
        [self setCategoryFont:[data valueForKey:@"categoryFont"]];
        [self setCategoryUp:[data valueForKey:@"categoryUp"]];
        [self setCategoryDown:[data valueForKey:@"categoryDown"]];
        [self setCategoryOver:[data valueForKey:@"categoryOver"]];
    }
    return self;
    
}

+(UIColor*)toUIColor:(NSString*)value alpha:(float)alpha
{   
    // strip # if it appears  
    if ([value hasPrefix:@"#"]) 
		value = [value substringFromIndex:1];  
    
    // if the value isn't 6 characters at this point return 
    // the color black	
    if ([value length] != 6) 
		return [UIColor blackColor];  
    
    // Separate into r, g, b substrings  
    NSString *rString = [value substringWithRange:NSMakeRange(0, 2)];  
    NSString *gString = [value substringWithRange:NSMakeRange(2, 2)];  
    NSString *bString = [value substringWithRange:NSMakeRange(4,2)];  
    
    // Scan values  
    unsigned int r, g, b;  
    [[NSScanner scannerWithString:rString] scanHexInt:&r];  
    [[NSScanner scannerWithString:gString] scanHexInt:&g];  
    [[NSScanner scannerWithString:bString] scanHexInt:&b];  
    
    return [UIColor colorWithRed:((float) r / 255.0f)  
                           green:((float) g / 255.0f)  
                            blue:((float) b / 255.0f)  
                           alpha:alpha];  
}

-(void)dealloc
{
    [theme release];
    [background release];
    [font release];
    [border release];
    [imageBackground release];
    [imageBorder release];
    [buttonFont release];
    [buttonUp release];
    [buttonDown release];
    [buttonOver release];
    [categoryFont release];
    [categoryUp release];
    [categoryDown release];
    [categoryOver release];
    [super dealloc];
}

@end
