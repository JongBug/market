app.controller("cartCtrl", function($scope, config, $window) {

	$scope.cartListView = config.cartList

	$scope.removerCarrinho = function(id) {
		config.cartList.splice(id, 1);
	};

	$scope.valorTotal = function() {
		var valor = 0;
		$scope.valorFinal = 0;
		for(i = 0; i < config.cartList.length; i++){
			valor += config.cartList[i].unit_price
		}
		$scope.valorFinal = valor.toFixed(2);
	}
});