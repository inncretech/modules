var a=angular.module('productApp', ['productApp.controllers','productApp.services']);
var productApp = angular.module('productApp.controllers',[]);

productApp.controller('ProductController',['$scope','AppService',function($scope,AppService) {
	$scope.countryMap = countryMap;
	$scope.product = product;

	$scope.remove = function(index) {
		$scope.product.items.splice(index, 1);
	};

	$scope.addItem = function(item) {
		if ($scope.validateItemForm()) {
			$scope.product.items.push(jQuery.extend(true, {}, item));
			$scope.item = {};
		}
	};

	$scope.editItem = function(index) {
		$scope.item = $scope.product.items[index];
	};

	$scope.markActive = function(index) {
		$scope.product.items[index].isActive = true;
	};

	$scope.validateProductForm = function() {
		var form = $("#productBean");
		createAllErrors(form);
	};

	$scope.validateItemForm = function() {
		var form = $("#itemBean");
		return createAllErrors(form);

	};

	$scope.clearErrorMessage = function() {
		var form = $("#itemBean");
		var errorList = $('div.errorMessages', form);
		errorList.empty();

		$('.errorMessages').hide();
	};

}]);

function createAllErrors(form) {
	flag = true;
	var errorList = $('div.errorMessages', form);
	errorList.empty();
	// Find all invalid fields within the form.
	form.find(':invalid').each(function(index, node) {
		var message = node.title || 'Invalid value.';
		errorList.show().append('<span> * ' + message + '</span> </br>');
	});
	if ($('.errorMessages').text() == '') {
		$('.errorMessages').hide();
	} else {
		flag = false;
	}
	return flag;
}
