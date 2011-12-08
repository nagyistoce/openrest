//
//  RestaurantFull.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 7/20/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "RestaurantFull.h"
#import "Utils.h"

@implementation RestaurantFull

@synthesize restaurant;
@synthesize menu;
@synthesize charges;

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [self init]))
    {
        [self setCharges:[NSArray array]];
        if ([data valueForKey:@"charges"] != nil)
        {
            [self setCharges:[Utils refactorJsonArray:[data valueForKey:@"charges"] 
                                                 toClass:@"Charge"]];
        }

        [self setRestaurant:[[[Restaurant alloc] initWithDictionary:[data valueForKey:@"restaurant"]] autorelease]];
        [self setMenu:[[[Menu alloc] initWithDictionary:[data valueForKey:@"menu"]] autorelease]];
    }
    return self;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    [ret setValue:restaurant forKey:@"restaurant"];
    [ret setValue:menu forKey:@"menu"];
    [ret setValue:charges forKey:@"charges"];
    
    return ret;
}

-(NSString*)description
{
    return [NSString stringWithFormat:@"\n\tRestaurant: %@\n\tMenu: %@\n\tCharges: %@:", restaurant, menu, charges];
}

@end
