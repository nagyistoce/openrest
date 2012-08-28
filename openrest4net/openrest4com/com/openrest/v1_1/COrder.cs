using System;
using System.Runtime.InteropServices;

namespace com.openrest.v1_1
{
    [ComVisible(true)]
    public class COrder : IOrder
    {
        public COrder(Order order)
        {
            this.order = order;
        }

        public string GetId()
        {
            return order.id;
        }

        public IStringMap GetExternalIds()
        {
            return new CStringMap(order.externalIds);
        }

        public string GetRestaurantId()
        {
            return order.restaurantId;
        }

        public string GetLocale()
        {
            return order.locale;
        }

        public IOrderItems GetOrderItems()
        {
            return new COrderItems(order.orderItems);
        }

        public string GetComment()
        {
            return order.comment;
        }

        public int GetPrice()
        {
            return order.price.Value;
        }

        public IDelivery GetDelivery()
        {
            return new CDelivery(order.delivery);
        }

        public IContact GetContact()
        {
            return new CContact(order.contact);
        }

        public IPayments GetPayments()
        {
            return new CPayments(order.payments);
        }

        public INullableInt GetTakeoutPacks()
        {
            return new CNullableInt(order.takeoutPacks);
        }

        public ICharges GetCharges()
        {
            return new CCharges(order.charges);
        }

        public long GetCreated()
        {
            return order.created.Value;
        }

        public long GetModified()
        {
            return order.modified.Value;
        }

        public IUser GetUser()
        {
            return new CUser(order.user);
        }

        public string GetStatus()
        {
            return order.status;
        }

        public string GetShareToken()
        {
            return order.shareToken;
        }

        public string GetAffiliate()
        {
            return order.affiliate;
        }

        public string GetRef()
        {
            return null; // TODO: order.ref
        }

        private readonly Order order;
    }
}
