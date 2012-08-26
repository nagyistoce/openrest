using System;

namespace com.openrest.v1_1
{
    public class CardInfo
    {
        public CardInfo(string formId, bool active)
        {
            this.formId = formId;
            this.active = active;
        }

        /** Empty constructor required for initialization from JSON-encoded string. */
        public CardInfo() { }

        /*
         * The credit card form-id (one of CreditCard.ALL_FORMS) specifying the required
         * details to clear cards of that specific network.
         */
        public string formId;

        /** Whether or not cards of that specific network are supported. */
        public bool? active = true;
    }
}
