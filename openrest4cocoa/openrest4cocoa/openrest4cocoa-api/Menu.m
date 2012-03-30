//
//  Menu.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Menu.h"
#import "Utils.h"

@implementation Menu

@synthesize items;
@synthesize tags;
@synthesize categories;

-(id)init
{
    if ((self = [super init]))
    {
        [self setItems:[NSArray array]];
        [self setTags:[NSArray array]];
        [self setCategories:[NSArray array]];
    }
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        if ([data valueForKey:@"items"] != nil)
        {
            [self setItems:[Utils refactorJsonArray:[data valueForKey:@"items"]
                                            toClass:@"Item"]];
        }
        if ([data valueForKey:@"tags"] != nil)
        {
            [self setTags:[Utils refactorJsonArray:[data valueForKey:@"tags"]
                                            toClass:@"Tag"]];
        }
        if ([data valueForKey:@"categories"] != nil)
        {
            [self setCategories:[Utils refactorJsonArray:[data valueForKey:@"categories"]
                                            toClass:@"Category"]];
        }
    }
    return self;
}

-(NSUInteger)hash
{
    return [items hash] + [tags hash] + [categories hash];
}

-(BOOL)isEqual:(id)object
{
    if (object == self) return TRUE;
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[self class]]) return FALSE;
    
    Menu* other = (Menu*)object;
    if (![items isEqualToArray:other.items]) return FALSE;
    if (![tags isEqualToArray:other.tags]) return FALSE;
    if (![categories isEqualToArray:other.categories]) return FALSE;
    
    return TRUE;
}

-(Item*)itemById:(NSString*)itemId
{
    for (int i = 0 ; i < [items count] ; i++)
    {
        Item* item = [items objectAtIndex:i];
        if ([[item itemId] compare:itemId] == NSOrderedSame)
        {
            return item;
        }
    }
    return nil;
}

-(NSString*)description
{
    NSMutableString* ret = [NSMutableString stringWithCapacity:0];
    [ret appendString:@"\nMenu\n"];
    [ret appendFormat:@"Items: %@\n", items];
    [ret appendFormat:@"Tags: %@\n", tags];
    [ret appendFormat:@"Categories: %@\n", categories];
    return ret;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    if (items != nil) {[ret setValue:items forKey:@"items"];}
    if (tags != nil) {[ret setValue:tags forKey:@"tags"];}
    if (categories != nil) {[ret setValue:categories forKey:@"categories"];}
    
    return ret;
}


-(void)dealloc
{
    [items release];
    [tags release];
    [categories release];
    [super dealloc];
}

@end
