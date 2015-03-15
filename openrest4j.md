# Openrest Java library #

Following are code examples for the [openrest Java library](http://code.google.com/p/openrest/downloads/detail?name=openrest4j-1.1.5.zip). For source code, see [The Openrest SDK for Java on GitHub](https://github.com/openrest/openrest4j).

## Initialize ##
```
// @see http://code.google.com/p/google-http-java-client/
final HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
final OpenrestClient client = new OpenrestClient(requestFactory, "https://api.openrest.com/v1.1");
```

## Get restaurant information ##
```
final Restaurant restaurant = client.request(
   new GetOrganizationRequest("daves-taco", null),
   new TypeReference<Response<Restaurant>>() {});
System.out.println(restaurant.id);
```

See Java documentation for complete description of the Restaurant object.

## Get restaurant menu ##
```
final RestaurantFullInfo restaurantFullInfo = client.request(
   new GetOrganizationFullRequest("daves-taco"),
   new TypeReference<Response<RestaurantFullInfo>>() {});
System.out.println(restaurantFullInfo.menu.items.size());
```

See Java documentation for complete description of the RestaurantFullInfo object.

## Submit order ##
```
final Order order = new Order();
order.restaurantId = "daves-taco";
order.locale = "en_US";

final OrderItem orderItem = new OrderItem();
orderItem.itemId = "2018393";
orderItem.price = 2000;
order.orderItems = Collections.singletonList(orderItem);

order.contact = new Contact("Barack", "Obama", "barack.obama@mailinator.com", "+12024561111", null);
order.delivery = new Delivery(Delivery.DELIVERY_TYPE_DELIVERY,
   new Address("United States", "Ames", "Wood st", "2921", "4", "2", "main", null, null, null, null), 200);

order.price = 2200;
order.payments = Collections.singletonList(new Payment(Payment.PAYMENT_TYPE_CREDIT, 2200,
   new CreditCard(null, "4222222222222", 1, 2020, null, "Michelle Obama",
   null, null, null, null, null, null, Boolean.FALSE)));

final OrderConfirmation orderConfirmation = client.request(
   new SubmitOrderRequest(null, order, null),
   new TypeReference<Response<OrderConfirmation>>() {});
System.out.println(orderConfirmation.order.id);
```

See Java documentation for complete description of the Order object.