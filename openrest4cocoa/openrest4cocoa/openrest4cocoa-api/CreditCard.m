//
//  CreditCard.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/6/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "CreditCard.h"


@implementation CreditCard

@synthesize number;
@synthesize expireMonth;
@synthesize expireYear;
@synthesize holderId;
@synthesize holderName;
@synthesize anonymized;  

-(id)init
{
    if ((self = [super init]))
    {
        [self setAnonymized:false];
    }
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [self init]))
    {
        [self setNumber:[data valueForKey:@"number"]];
        [self setExpireMonth:[data valueForKey:@"expireMonth"]];
        [self setExpireYear:[data valueForKey:@"expireYear"]];
        [self setHolderId:[data valueForKey:@"holderId"]];
        [self setHolderName:[data valueForKey:@"holderName"]];
        if ([data valueForKey:@"anonymized"] != nil)
        {
            [self setAnonymized:([[data valueForKey:@"anonymized"] intValue] == 1)];
        }
    }
    return self;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];

    if (number != nil) {[ret setValue:number forKey:@"number"];}
    if (expireMonth != nil) {[ret setValue:expireMonth forKey:@"expireMonth"];}
    if (expireYear != nil) {[ret setValue:expireYear forKey:@"expireYear"];}
    if (holderId != nil) {[ret setValue:holderId forKey:@"holderId"];}
    if (holderName != nil) {[ret setValue:holderName forKey:@"holderName"];}
    if (anonymized) 
    {
        [ret setValue:@"true" forKey:@"anonymized"];
    }
    else
    {
        [ret setValue:@"false" forKey:@"anonymized"];        
    }

    return ret;
}

-(NSString*)description
{
    return [NSString stringWithFormat:@"CreditCard[%@ %@/%@ %@ %@]", number, expireMonth, expireYear, holderId, holderName];
}

-(void)dealloc
{
    [number release];
    [expireMonth release];
    [expireYear release];
    [holderId release];
    [holderName release];
    [super dealloc];
}

@end
