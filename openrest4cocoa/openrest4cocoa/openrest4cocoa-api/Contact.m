//
//  Contact.m
//  openrest4ios
//
//  Created by Yoav Amit on 4/19/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Contact.h"


@implementation Contact

@synthesize firstName;
@synthesize lastName;
@synthesize email;
@synthesize phone;

-(void)dealloc
{
    [firstName release];
    [lastName release];
    [email release];
    [phone release];
    [super dealloc];
}

-(id)initWithDictionary:(NSDictionary *)data
{
    if ((self = [super init]))
    {
        [self setFirstName:[data valueForKey:@"firstName"]];
        [self setLastName:[data valueForKey:@"lastName"]];
        [self setEmail:[data valueForKey:@"email"]];
        [self setPhone:[data valueForKey:@"phone"]];
    }
    return self;
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    if (firstName != nil) {[ret setValue:firstName forKey:@"firstName"];}
    if (lastName != nil) {[ret setValue:lastName forKey:@"lastName"];}
    if (email != nil) {[ret setValue:email forKey:@"email"];}
    if (phone != nil) {[ret setValue:phone forKey:@"phone"];}

    return ret;
}

-(NSUInteger)hash
{
    return [firstName hash] + [lastName hash];
}

-(BOOL)isEqual:(id)object
{
    if (self == object) return FALSE;
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[self class]]) return FALSE;
    Contact* other = (Contact*)object;
    
    if ([firstName isEqualToString:other.firstName])
    {
        if ([lastName isEqualToString:other.lastName])
        {
            if ([phone isEqualToString:other.phone])
            {
                if (([email length] + [other.email length] == 0) || ([email isEqualToString:other.email]))
                {
                    return TRUE;
                }
            }
        }
    }
    
    return FALSE;    
}

@end
