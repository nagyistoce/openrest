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
@synthesize type;
@synthesize csc;

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
        [self setType:[data valueForKey:@"type"]];
        [self setCsc:[data valueForKey:@"csc"]];
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
    if (type != nil) {[ret setValue:type forKey:@"type"];}
    if (csc != nil) {[ret setValue:csc forKey:@"csc"];}
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

-(BOOL)isEqual:(id)object
{
    if (self == object) return TRUE;
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[self class]]) return FALSE;
    CreditCard* other = (CreditCard*)object;

    if ((number != other.number) && (![number isEqualToString:other.number])) return FALSE;
    if ((expireMonth != other.expireMonth) && (![expireMonth isEqualToNumber:other.expireMonth])) return FALSE;
    if ((expireYear != other.expireYear) && (![expireYear isEqualToNumber:other.expireYear])) return FALSE;
    if ((holderId != other.holderId) && (![holderId isEqualToString:other.holderId])) return FALSE;
    if ((holderName != other.holderName) && (![holderName isEqualToString:other.holderName])) return FALSE;
    if ((type != other.type) && (![type isEqualToString:other.type])) return FALSE;    
    if ((csc != other.csc) && (![csc isEqualToString:other.csc])) return FALSE;    
    if (anonymized != other.anonymized) return FALSE;
    
    return TRUE;
}

-(NSUInteger)hash
{
    return [number hash] + [expireYear hash] + [expireMonth hash];
}

-(NSString*)description
{
    return [NSString stringWithFormat:@"CreditCard[%@ - %@ %@/%@ %@ %@]", type, number, expireMonth, expireYear, holderId, holderName];
}

-(void)dealloc
{
    [number release];
    [expireMonth release];
    [expireYear release];
    [holderId release];
    [holderName release];
    [type release];
    [csc release];
    [super dealloc];
}

@end
