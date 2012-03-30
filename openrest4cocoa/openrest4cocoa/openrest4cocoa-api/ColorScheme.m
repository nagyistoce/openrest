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

-(BOOL)isEqual:(id)object
{
    if (self == object) return TRUE;
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[ColorScheme class]]) return FALSE;
    
    ColorScheme* other = (ColorScheme*)object;
    
    if (![theme isEqualToString:other.theme]) return FALSE;
    if (![background isEqualToString:other.background]) return FALSE;
    if (![font isEqualToString:other.font]) return FALSE;
    if (![border isEqualToString:other.border]) return FALSE;
    if (![imageBackground isEqualToString:other.imageBackground]) return FALSE;
    if (![imageBorder isEqualToString:other.imageBorder]) return FALSE;
    if (![buttonFont isEqualToString:other.buttonFont]) return FALSE;
    if (![buttonUp isEqualToString:other.buttonUp]) return FALSE;
    if (![buttonDown isEqualToString:other.buttonDown]) return FALSE;
    if (![buttonOver isEqualToString:other.buttonOver]) return FALSE;
    if (![categoryFont isEqualToString:other.categoryFont]) return FALSE;
    if (![categoryUp isEqualToString:other.categoryUp]) return FALSE;
    if (![categoryDown isEqualToString:other.categoryDown]) return FALSE;
    if (![categoryOver isEqualToString:other.categoryOver]) return FALSE;

    return TRUE;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    if (theme != nil) {[ret setValue:theme forKey:@"theme"];}
    if (background != nil) {[ret setValue:background forKey:@"background"];}
    if (font != nil) {[ret setValue:font forKey:@"font"];}
    if (border != nil) {[ret setValue:border forKey:@"border"];}
    if (imageBackground != nil) {[ret setValue:imageBackground forKey:@"imageBackground"];}
    if (imageBorder != nil) {[ret setValue:imageBorder forKey:@"imageBorder"];}
    if (buttonFont != nil) {[ret setValue:buttonFont forKey:@"buttonFont"];}
    if (buttonUp != nil) {[ret setValue:buttonUp forKey:@"buttonUp"];}
    if (buttonDown != nil) {[ret setValue:buttonDown forKey:@"buttonDown"];}
    if (buttonOver != nil) {[ret setValue:buttonOver forKey:@"buttonOver"];}
    if (categoryFont != nil) {[ret setValue:categoryFont forKey:@"categoryFont"];}
    if (categoryUp != nil) {[ret setValue:categoryUp forKey:@"categoryUp"];}
    if (categoryDown != nil) {[ret setValue:categoryDown forKey:@"categoryDown"];}
    if (categoryOver != nil) {[ret setValue:categoryOver forKey:@"categoryOver"];}
    
    return ret;
}


-(NSUInteger)hash
{
    return [theme hash] + [background hash] + [font hash] + [border hash] + 
    [imageBackground hash] + [imageBorder hash] + [buttonFont hash] + [buttonUp hash] + 
    [buttonDown hash] + [buttonOver hash] + [categoryFont hash] + [categoryUp hash] + 
    [categoryDown hash] + [categoryOver hash];
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
