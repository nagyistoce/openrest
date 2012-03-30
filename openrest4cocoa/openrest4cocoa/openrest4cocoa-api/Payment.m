//
//  Payment.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 5/6/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Payment.h"


@implementation Payment

@synthesize type;
@synthesize amount;
@synthesize card;
@synthesize password;
@synthesize storedId;

-(id)init
{
    if ((self = [super init]))
    {
        [self setAmount:0];
    }
    return self;
}

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [self init]))
    {
        [self setType:[data valueForKey:@"type"]];
        
        if ([data valueForKey:@"amount"] != nil)
        {
            [self setAmount:[[data valueForKey:@"amount"] intValue]];
        }
        
        if ([data valueForKey:@"card"] != nil)
        {
            [self setCard:[[[CreditCard alloc] initWithDictionary:[data valueForKey:@"card"]]
                           autorelease]];
        }
        
        if ([data valueForKey:@"password"] != nil)
        {
            [self setPassword:[data valueForKey:@"password"]];
        }

        if ([data valueForKey:@"id"] != nil)
        {
            [self setStoredId:[data valueForKey:@"id"]];
        }
    }
    return self;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];

    if (type != nil) {[ret setValue:type forKey:@"type"];}
    if (amount != 0) {[ret setValue:[NSNumber numberWithInt:amount] forKey:@"amount"];}
    if (card != nil) {[ret setValue:card forKey:@"card"];}
    if (password != nil) {[ret setValue:password forKey:@"password"];}
    if (storedId != nil) {[ret setValue:storedId forKey:@"id"];}

    return ret;
}

-(BOOL)isEqual:(id)object
{
    if (self == object) return TRUE;

    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[Payment class]]) return FALSE;
    
    Payment* other = (Payment*)object;

    if (![type isEqualToString:other.type]) return FALSE;
    if (amount != other.amount) return FALSE;
    if ((card != other.card) && (![card isEqual:other.card])) return FALSE;
    if ((storedId != other.storedId) && (![storedId isEqualToString:other.storedId])) return FALSE;
    if ((password != other.password) && (![password isEqualToString:other.password])) return FALSE;
    return TRUE;    
}

-(NSUInteger)hash
{
    return [type hash] + amount + [card hash] + [storedId hash];
}

-(NSString*)description
{
    return [NSString stringWithFormat:@"Payment[[%@] %@ %d %@]", storedId, type, amount, card];
}

-(void)dealloc
{
    [type release];
    [card release];
    [super dealloc];
}
@end
