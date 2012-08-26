using System;
using System.Collections.Generic;

namespace com.openrest.v1_1
{
    /**
     * Identifies a club member to the restaurant.
     * @author DL
     */
    public class ClubMember
    {
        public ClubMember(string restaurantId, string memberId, string phone, IList<string> clubIds)
        {
            this.restaurantId = restaurantId;
            this.memberId = memberId;
            this.phone = phone;
            this.clubIds = clubIds;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public ClubMember() { }

        /** The restaurant's id. */
        public string restaurantId;
    
        /** The club member's id (e.g. number on membership card). */
        public string memberId;
    
        /** The club member's phone number. */
        public string phone;
    
        /** The internal club ids of which the client is a member of. */
        public IList<string> clubIds = new List<string>();
    }
}
