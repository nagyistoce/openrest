using System;

namespace com.googlecode.openrest
{
    public class COrder
    {
        public COrder() { }

        public COrder(Order order)
        {
            this.order = order;
        }

        public string GetId()
        {
            return order.id;
        }

        public string GetComment()
        {
            return order.comment;
        }

        public int? GetPrice()
        {
            return order.price;
        }

        public CPayments GetPayments()
        {
            return new CPayments(order.payments);
        }

        private readonly Order order;
    }
}
