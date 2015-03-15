# cURL examples #

Following are command-line examples for [cURL](http://curl.haxx.se/).

## Get restaurant information ##
```
curl -X "POST" -H "Content-Type: application/json" -d "{\"type\":\"get_organization\", \"organizationId\":\"daves-taco\"}" "https://api.openrest.com/v1.1"
```

See Java documentation for complete description of the Restaurant object.

## Get restaurant menu ##
```
curl -X "POST" -H "Content-Type: application/json" -d "{\"type\":\"get_organization_full\", \"organizationId\":\"daves-taco\"}" "https://api.openrest.com/v1.1"
```

See Java documentation for complete description of the RestaurantFullInfo object.

## Submit order ##
```
curl -X "POST" -H "Content-Type: application/json" -d "{\"type\":\"submit_order\", \"order\":{\"restaurantId\":\"daves-taco\", \"locale\":\"en_US\", \"orderItems\":[{\"itemId\":\"2018393\", \"price\":2000}], \"contact\":{\"firstName\":\"Barack\", \"lastName\":\"Obama\", \"email\":\"barack.obama@mailinator.com\", \"phone\":\"+12024561111\"}, \"delivery\":{\"type\":\"delivery\", \"address\":{\"country\":\"United States\", \"city\":\"Ames\", \"street\": \"Wood St\", \"number\":\"2921\", \"apt\":\"4\", \"floor\":\"2\", \"entrance\":\"main\"}, \"charge\":200}, \"price\":2200, \"payments\":[{\"type\":\"credit\", \"card\":{\"number\":\"4222222222222\", \"expireMonth\":1, \"expireYear\":2020, \"holderName\":\"Michelle Obama\"}, \"amount\":2200}]}}" "https://api.openrest.com/v1.1"
```

See Java documentation for complete description of the Order object.

## Get open orders ##
```
curl -X "POST" -H "Content-Type: application/json" -d "{\"type\":\"query_orders\", \"accessToken\":\"XXX\", \"restaurantIds\":[\"daves-taco\"], \"status\":\"new\", \"restaurantView\":true}" "https://api.openrest.com/v1.1"
```

## Update order status ##
```
curl -X "POST" -H "Content-Type: application/json" -d "{\"type\":\"set_order_status\", \"accessToken\":\"XXX\", \"orderId\":\"5551234\", \"status\":\"accepted\"}" "https://api.openrest.com/v1.1"
```