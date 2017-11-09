app.controller("productsCtrl", function($scope, productsApi, config, $http){
	
	//GET method responsible for get all products data
  $scope.getProductList = function() {
	  productsApi.getList().then(function (data) {
      $scope.productsList = data;
      }, function (error) {
      console.log('Unable to load contacts data: ' + error.message);
    });
  };
  
  $scope.addCart = function (data) {
	  config.cartList.push(data);
	  alert("Adicionado no Carrinho");
  }

  $scope.fatorValor = config.fatorValor;
  
  $scope.addProduct = function () {
	    if($scope.ngName!=null && $scope.ngDescription!=null  && $scope.ngValue!=null){
	    	
	  	  var dataObj = {
			      title : $scope.ngName,
			      description : $scope.ngDescription,
			      unit_price : $scope.ngValue,
			    };
	  	  
		  productsApi.addProduct(dataObj).then(function (data) {
		        $('#modalAdd').modal('hide');
		        alert("Adicionado com Sucesso");
		        location.reload();
		      }, function (error) {
		    	  alert("Valores invalidos");
		      console.log('Unable to load contacts data: ' + error.message);
		    });
	    } else {
	    	alert("Preencher todos campo")
	    }
  };
  
  $scope.removeProduct = function (id) {	
	  	  var dataObj = {
			      id : id
			    };
		  productsApi.removeProduct(dataObj).then(function (data) {
		        $('#modalAdd').modal('hide');
		        alert("Removido com Sucesso");
		        location.reload();
		      }, function (error) {
		    	  alert("Erro ao remover");
		      console.log('Unable to load contacts data: ' + error.message);
		    });
  };
  
});
