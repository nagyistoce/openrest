# Openrest JavaScript library #
Following are code examples for the [openrest JavaScript library](https://code.google.com/p/openrest/downloads/detail?name=openrest4js-client-1.1.1.zip).

## Initialize ##
```
var client = new openrest.Client();
```

## Get restaurant information ##
```
client.request({
   request : {
      type : "get_organization",
      organizationId : "daves-taco"
   },
   callback : function(response) {
      if (response.error) {
         alert(response.error + ": " + response.errorMessage);
         return;
      }
      var restaurant = response.value;
      alert(restaurant.id);
   }
});
```

See Java documentation for complete description of the Restaurant object.

## Get restaurant menu ##
```
client.request({
   request : {
      type : "get_organization_full",
      organizationId : "daves-taco"
   },
   callback : function(response) {
      if (response.error) {
         alert(response.error + ": " + response.errorMessage);
         return;
      }
      var restaurantFullInfo = response.value;
      alert(restaurantFullInfo.menu.items.length);
   }
});
```

See Java documentation for complete description of the RestaurantFullInfo object.

## Submit order ##
```
var order = {
   restaurantId : "daves-taco",
   locale : "en_US",
   contact : {
      firstName : "Barack",
      lastName : "Obama",
      email : "barack.obama@mailinator.com",
      phone : "+12024561111"
   },
   delivery : {
      type : "delivery",
      address : {
         country : "United States",
         city : "Ames",
         street : "Wood st",
         number : "2921",
         apt : "4",
         floor : "2",
         entrance : "main"
      },
      charge : 200
   },
   orderItems : [{
      itemId : "2018393",
      price : 2000
   }],
   price : 2200,
   payments : [{
      type : "credit",
      card : {
         number : "4222222222222",
         expireMonth : 1,
         expireYear : 2020,
         holderName : "Michelle Obama"
      },
      amount : 2200
   }]
};

client.request({
   request : {
      type : "submit_order",
      order : order
   },
   callback : function(response) {
      if (response.error) {
         alert(response.error + ": " + response.errorMessage);
         return;
      }
      var orderConfirmation = response.value;
      alert(orderConfirmation.order.id);
   }
});
```

See Java documentation for complete description of the Order object.

## Get open orders ##
```
client.request({
   request : {
      type : "query_orders",
      accessToken: "XXX",
      restaurantIds : ["daves-taco"],
      status : "new",
      restaurantView : true
   },
   callback : function(response) {
      if (response.error) {
         alert(response.error + ": " + response.errorMessage);
         return;
      }
      var ordersResponse = response.value;
      var orders = ordersResponse.results || [];
      alert(orders.length);
   }
});
```

## Update order status ##
```
client.request({
   request : {
      type : "set_order_status",
      accessToken: "XXX",
      orderId : "5551234",
      status : "accepted"
   },
   callback : function(response) {
      if (response.error) {
         alert(response.error + ": " + response.errorMessage);
         return;
      }
      var order = response.value
      alert(order.id);
   }
});
```