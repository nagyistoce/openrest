# Openrest .NET library #

Following are code examples for the [openrest .NET library](http://code.google.com/p/openrest/downloads/detail?name=openrest4net-1.1.0.zip).

## Initialize ##
```
OpenrestClient client = new OpenrestClient(new Uri("https://api.openrest.com/v1.1"));
```

## Get restaurant information ##
```
Restaurant restaurant = client.Request<Restaurant>(new GetOrganizationRequest("daves-taco", null));
Console.WriteLine(restaurant.id);
```

See Java documentation for complete description of the Restaurant object.

## Get restaurant menu ##
```
RestaurantFullInfo restaurantFullInfo = client.Request<RestaurantFullInfo>(new GetOrganizationFullRequest("daves-taco"));
Console.WriteLine(restaurantFullInfo.menu.items.Count);
```

See Java documentation for complete description of the RestaurantFullInfo object.

## Submit order ##
```
Order order = new Order();
order.restaurantId = "daves-taco";
order.locale = "en_US";

OrderItem orderItem = new OrderItem();
orderItem.itemId = "2018393";
orderItem.price = 2000;
order.orderItems = new List<OrderItem> { orderItem };

order.contact = new Contact("Barack", "Obama", "barack.obama@mailinator.com", "+12024561111", null);
order.delivery = new Delivery(Delivery.DELIVERY_TYPE_DELIVERY,
    new Address("United States", "Ames", "Wood st", "2921", "4", "2", "main", null, null, null, null), 200);

order.price = 2200;

CreditCard card = new CreditCard();
card.number = "4222222222222";
card.expireMonth = 1;
card.expireYear = 2020;
card.holderName = "Michelle Obama";
order.payments = new List<Payment> { new Payment(Payment.PAYMENT_TYPE_CREDIT, 2200, card) };

OrderConfirmation orderConfirmation = client.Request<OrderConfirmation>(new SubmitOrderRequest(null, order, null));
Console.WriteLine(orderConfirmation.order.id);
```

See Java documentation for complete description of the Order object.