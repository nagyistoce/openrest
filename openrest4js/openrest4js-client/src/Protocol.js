var openrest = openrest || {};

openrest.Protocol = openrest.Protocol || (function() {
	var self = {};
	var timeout = 30000;
	
	self.get = function(params) {
		var client = http.create({
			onload : function(e) {
				if (params.callback) {
					params.callback(JSON.parse(this.responseText));
				}
			},
			onerror : function(e) {
				if (params.callback) {
					params.callback({
						error : "protocol",
						errorMessage : "protocol error"
						});
				}
			},
			timeout : timeout
		});
	
		client.open("GET", params.url);
		client.setRequestHeader("Accept", "application/json");
		client.send();
	};

	self.set = function(params) {
		var client = http.create({
			onload : function(e) {
				if (params.callback) {
					params.callback(JSON.parse(this.responseText));
				}
			},
			onerror : function(e) {
				if (params.callback) {
					params.callback({
						error : "protocol",
						errorMessage : "protocol error"
						});
				}
			},
			timeout : timeout
		});
	
		client.open("PUT", params.url);
		client.setRequestHeader("Accept", "application/json");
		client.setRequestHeader("Content-Type", "application/json");
		client.send(JSON.stringify(params.obj));
	};

	self.add = function(params) {
		var client = http.create({
			onload : function(e) {
				if (params.callback) {
					params.callback(JSON.parse(this.responseText));
				}
			},
			onerror : function(e) {
				if (params.callback) {
					params.callback({
						error : "protocol",
						errorMessage : "protocol error"
						});
				}
			},
			timeout : timeout
		});
	
		client.open("POST", params.url);
		client.setRequestHeader("Accept", "application/json");
		client.setRequestHeader("Content-Type", "application/json");
		client.send(JSON.stringify(params.obj));
	};

	self.remove = function(params) {
		var client = http.create({
			onload : function(e) {
				if (params.callback) {
					params.callback(JSON.parse(this.responseText));
				}
			},
			onerror : function(e) {
				if (params.callback) {
					params.callback({
						error : "protocol",
						errorMessage : "protocol error"
						});
				}
			},
			timeout : timeout
		});
	
		client.open("DELETE", params.url);
		client.setRequestHeader("Accept", "application/json");
		client.send();
	};
	
	return self;
}());
