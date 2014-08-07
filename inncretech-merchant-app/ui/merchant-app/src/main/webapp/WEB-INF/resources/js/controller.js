var productApp = angular.module('productApp', []);

productApp.controller('ProductController', function($scope) {
	$scope.countryMap = countryMap;
	$scope.product = product;
	
	$scope.remove = function(index) {
		$scope.product.items.splice(index, 1);
	};

	$scope.addItem = function(item) {
		$scope.product.items.push(jQuery.extend(true, {}, item));
		$scope.item={};
	};
	
	$scope.editItem = function(index) {
		
		console.log($scope.product.items[index])
		$scope.item=$scope.product.items[index];
	};
	

});



