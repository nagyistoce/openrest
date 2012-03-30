//
//  Area.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Area.h"
#import "LatLng.h"
#import "Utils.h"

@implementation Area

@synthesize title;
@synthesize polygon;

-(id)init
{
    if ((self = [super init]))
    {
        [self setPolygon:[NSArray array]];
    }
    return self;
}

-(id)initWithDictionary:(NSDictionary *)data
{
    if ((self = [self init]))
    {
        [self setTitle:[LocalizedDictionary dictionaryWithDictionary:[data valueForKey:@"title"]]];
        
        if ([data valueForKey:@"polygon"] != nil)
        {
            [self setPolygon:[Utils refactorJsonArray:[data valueForKey:@"polygon"]
                                                 toClass:@"LatLng"]];

        }
    }
    return self;
}

-(BOOL)isEqual:(id)object
{
    if (object == self) return TRUE;
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[self class]]) return FALSE;
    Area* other = (Area*)object;

    if ((title != other.title) && (![title isEqual:other.title])) return FALSE;
    if ((polygon != other.polygon) && (![polygon isEqualToArray:other.polygon])) return FALSE;
    
    return TRUE;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    if (title != nil) {[ret setValue:title forKey:@"title"];}
    if (polygon != nil) {[ret setValue:polygon forKey:@"polygon"];}

    return ret;
}

-(NSUInteger)hash
{
    return [title hash] + [polygon hash];
}

-(NSString *)description
{
    return [title objectForKey:[Utils getLocale]];
}

-(void)dealloc
{
    [title release];
    [polygon release];
    [super dealloc];
}
@end
