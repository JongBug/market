app.service("productsApi", function($http, config, $window){
	return{
		getList: function(){
			return $http.get( config.baseUrl + "/allProducts")
			.then(function(result){
				return result.data;
			},
       function(result){
        console.log(result);
       });
		},
		addProduct: function(product){
			return $http.post( config.baseUrl + "/addProduct", product)
			.then(function(result){
				return result.status;
			}, function (error) {
	    	  alert("Valores invalidos");
		      console.log('Unable to load contacts data: ' + error.message);
		    });
		},
		removeProduct: function(product){
			return $http.post( config.baseUrl + "/removeProduct", product)
			.then(function(result){
				return result.status;
			}, function (error) {
	    	  alert("Valores invalidos");
		      console.log('Unable to load contacts data: ' + error.message);
		    });
		}
	};
});
