var productApp = angular.module('productApp', []);

productApp.controller('ProductController', function($scope) {
	$scope.countryMap = countryMap;
	$scope.product = product;
	$scope.add = function() {
		$scope.product.items.push(jQuery.extend(true, {}, blankItem));
	};
	$scope.remove = function(index) {
		$scope.product.items.splice(index, 1);
	};

});
