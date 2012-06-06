var openrest = openrest || {};

openrest.Client = openrest.Client || function(params) { return (function(params) {
	params = params || {};
	var apiUrl = params.apiUrl || "https://api.openrest.com/v1.1";
	var accessToken = params.accessToken || null;
	
	var self = {};
	
	self.getAccessToken = function() {
		return accessToken;
	};
	
	self.setAccessToken = function(_accessToken) {
		accessToken = _accessToken;
	};

	self.getMyRoles = function(params) {
		var query = new openrest.QueryStringBuilder(params.params);
		query.append("access_token", accessToken);
		
		openrest.Protocol.get({
			url : apiUrl + "/me/roles/" + query.toString(),
			callback : params.callback
		});
	};

    self.getMeInfo = function(params) {
		var query = new openrest.QueryStringBuilder(params.params);
		query.append("access_token", accessToken);
		
		openrest.Protocol.get({
			url : apiUrl + "/me/info" + query.toString(),
			callback : params.callback
		});
	};

    self.setMeInfo = function(params) {
		var query = new openrest.QueryStringBuilder(params.params);
		query.append("access_token", accessToken);
		
		openrest.Protocol.set({
			url : apiUrl + "/me/info" + query.toString(),
            obj: params.obj,
			callback : params.callback
		});
	};

    self.getMePayments = function(params) {
		var query = new openrest.QueryStringBuilder(params.params);
		query.append("access_token", accessToken);
		
		openrest.Protocol.get({
			url : apiUrl + "/me/payments/" + query.toString(),
			callback : params.callback
		});
	};

    self.deletePayment = function(params)
    {
		var query = new openrest.QueryStringBuilder(params.params);
		query.append("access_token", accessToken);
		
		openrest.Protocol.remove({
			url : apiUrl + "/me/payments/"+params.id + query.toString(),
			callback : params.callback
		});
    }
	
	self.getOrders = function(params) {
		var query = new openrest.QueryStringBuilder(params.params);
		query.append("access_token", accessToken);
		
		openrest.Protocol.get({
			url : apiUrl + "/orders/" + query.toString(),
			callback : params.callback
		});
	};
	
	self.getOrderHtmlUrl = function(params) {
		var query = new openrest.QueryStringBuilder(params.params);
		query.append("access_token", accessToken);
	
		return apiUrl + "/orders/" + params.id + query.toString();
	};
	
	self.getRestaurants = function(params) {
		var query = new openrest.QueryStringBuilder(params.params);
		
		openrest.Protocol.get({
			url : apiUrl + "/restaurants/" + query.toString(),
			callback : params.callback
		});
	};
	
	self.search = function(params) {
		var query = new openrest.QueryStringBuilder(params.params);
		
		openrest.Protocol.get({
			url : apiUrl + "/search" + query.toString(),
			callback : params.callback
		});
	};
	
	self.getRestaurantsFull = function(params) {
		var query = new openrest.QueryStringBuilder(params.params);
		
		openrest.Protocol.get({
			url : apiUrl + "/restaurants.full/" + query.toString(),
			callback : params.callback
		});
	};
	
	self.request = function(params) {
		var params = params || {};
		var request = params.request || {};
		var callback = params.callback || function(e){};
		
		openrest.Protocol.add({
			url : apiUrl,
			obj : request,
			callback : callback
		});
	};
	
	return self;
}(params))};
