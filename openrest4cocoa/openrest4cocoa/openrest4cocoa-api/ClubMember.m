//
//  ClubMember.m
//  openrest4cocoa
//
//  Created by Yoav Amit on 3/5/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "ClubMember.h"

@implementation ClubMember

@synthesize restaurantId;
@synthesize memberId;
@synthesize phone;
@synthesize clubIds;

-(id)initWithDictionary:(NSDictionary*)data
{
    if ((self = [super init]))
    {
        self.restaurantId = [data objectForKey:@"restaurantId"];
        self.memberId = [data objectForKey:@"memberId"];
        self.phone = [data objectForKey:@"phone"];
        self.clubIds = [data objectForKey:@"clubIds"];
    }
    return self;
}

-(void)dealloc
{
    [restaurantId release];
    [memberId release];
    [phone release];
    [clubIds release];
    
    [super dealloc];
}

-(NSDictionary*)proxyForJson
{
    NSMutableDictionary* ret = [NSMutableDictionary dictionaryWithCapacity:0];
    
    [ret setObject:restaurantId forKey:@"restaurantId"];
    [ret setObject:memberId forKey:@"memberId"];
    [ret setObject:phone forKey:@"phone"];
    [ret setObject:clubIds forKey:@"clubIds"];
    
    return ret;
}

-(NSUInteger)hash
{
    return [restaurantId hash]+[memberId hash]+[phone hash];
}

-(BOOL)isEqual:(id)object
{
    if (self == object) return TRUE;
    if (object == NULL) return FALSE;
    if (![object isKindOfClass:[self class]]) return FALSE;
    ClubMember* other = (ClubMember*)object;
    
    if ((restaurantId != other.restaurantId) && (![restaurantId isEqualToString:other.restaurantId])) return FALSE;
    if ((memberId != other.memberId) && (![memberId isEqualToString:other.memberId])) return FALSE;
    if ((phone != other.phone) && (![phone isEqualToString:other.phone])) return FALSE;
    if ((clubIds != other.clubIds) && (![clubIds isEqualToArray:other.clubIds])) return FALSE;
    
    return TRUE; 
}

@end
