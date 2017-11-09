app.config(function($stateProvider, $urlRouterProvider){
	$stateProvider
	 .state('home', {
		 url: '/',
		 templateUrl : 'index.html'
	 })
	 .state('products', {
		 url : '/products/allProducts',
		 templateUrl : 'view/products.html',
		 controller: 'productsCtrl'
	 })
	 .state('cart', {
		 url : '/cart',
		 templateUrl : 'view/cart.html',
		 controller: 'cartCtrl'
	 });
});
