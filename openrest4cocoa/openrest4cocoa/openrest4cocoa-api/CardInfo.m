//
//  CardInfo.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/3/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "CardInfo.h"

@implementation CardInfo

@synthesize active;
@synthesize formId;

-(id)initWithFormId:(NSString*)_formId andActive:(BOOL)_active
{
    self = [super init];
    self.formId = _formId;
    self.active = _active;
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    self = [super init];
    self.formId = [data objectForKey:@"formId"];
    
    self.active = TRUE;
    if ([data objectForKey:@"active"])
    {
        self.active = [[data objectForKey:@"active"] boolValue];
    }
    
    return self;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    if (formId != nil) {[ret setValue:formId forKey:@"formId"];}
    [ret setValue:[NSNumber numberWithBool:self.active] forKey:@"active"];
    
    return ret;
}

-(void)dealloc
{
    self.formId = nil;
    [super dealloc];
}

-(BOOL)isEqual:(id)object
{
    if (self == object) return TRUE;
    
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[CardInfo class]]) return FALSE;
    if (![super isEqual:object]) return FALSE;
    
    CardInfo* other = (CardInfo*)object;
    
    if (other.active != self.active) return FALSE;
    if ((other.formId != self.formId) && (![other.formId isEqual:self.formId])) return FALSE;
        
    return TRUE;
}

@end
