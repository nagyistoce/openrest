//
//  OrderItem.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "OrderItem.h"
#import "Utils.h"

@implementation OrderItem

@synthesize itemId;
@synthesize variations;
@synthesize variationsChoices;
@synthesize comment;
@synthesize price;
@synthesize count;

-(id)init
{
    if ((self = [super init]))
    {
        [self setVariations:[NSArray array]];
        [self setVariationsChoices:[NSArray array]];
        [self setPrice:0];
        [self setCount:1];
    }
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [self init]))
    {
        [self setItemId:[data valueForKey:@"itemId"]];
  
        if ([data valueForKey:@"variations"] != nil)
        {
            [self setVariations:[Utils refactorJsonArray:[data valueForKey:@"variations"] 
                                               toClass:@"Variation"]];
        }

        if ([data valueForKey:@"variationsChoices"] != nil)
        {
            [self setVariationsChoices:
             [Utils refactorJsonArrayOfArray:[data valueForKey:@"variationsChoices"] 
                                     toClass:@"OrderItem"]];
        }
        
        [self setComment:[data valueForKey:@"comment"]];

        if ([data valueForKey:@"price"] != nil)
        {
            [self setPrice:[[data valueForKey:@"price"] intValue]];
        }
        if ([data valueForKey:@"count"] != nil)
        {
            [self setCount:[[data valueForKey:@"count"] intValue]];
        }
    }
    return self;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];

    if (itemId != nil) {[ret setValue:itemId forKey:@"itemId"];}
    if (variations != nil) {[ret setValue:variations forKey:@"variations"];}
    if (variationsChoices != nil) {[ret setValue:variationsChoices forKey:@"variationsChoices"];}
    if (comment != nil) {[ret setValue:comment forKey:@"comment"];}
    if (price != 0) {[ret setValue:[NSNumber numberWithInt:price] forKey:@"price"];}
    if (count != 1) {[ret setValue:[NSNumber numberWithInt:count] forKey:@"count"];}
    
    return ret;
}
    
-(int)getTotalPrice
{
    int total = price;
    
    for (int i = 0 ; i < [variationsChoices count] ; i++)
    {
        for (int j = 0 ; j < [[variationsChoices objectAtIndex:i] count] ; j++)
        {
            OrderItem* variation = [[variationsChoices objectAtIndex:i] objectAtIndex:j];
            total += [variation getTotalPrice];
        }
    }
    
    return total*count;
}

-(void)dealloc
{
    [itemId release];
    [variations release];
    [variationsChoices release];
    [comment release];
    [super dealloc];
}

@end
