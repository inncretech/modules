var appModule = angular.module('productApp', [ 'ngRoute', 'productApp.controllers', 'productApp.services', 'ngSanitize' ]);
var productApp = angular.module('productApp.controllers', []);

productApp.controller('ProductController', [ '$scope', 'AppService', function($scope, AppService) {
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

} ]);



productApp.controller('ImageUploadController', [ '$scope', 'AppService', '$compile', '$q', '$timeout', function($scope, AppService, $compile, $q, $timeout) {
	$scope.imageArray = [];
	$scope.$on('postEvent', function(e) {
		$scope.$parent.imagePreviewData = $scope.imagePreviewData;
	});
	$scope.$on('doneEvent', function(e) {
		$scope.imagePreviewData = [];
		$scope.showImageDiv = false;
	});

	$scope.showImageDiv = false;
	$scope.imagePreviewData = [];

	$scope.addImageToPreview = function(image) {
		var img = new Image()
		img.src = image.imageUrl;
		img.onload = function() {
			console.info("Image loaded !");
			$scope.imagePreviewData.push(image);
			$scope.showImageDiv = true;
			$scope.attachNewImage = true;
			$scope.imageArray.shift();
			$scope.$apply();
		}
	};

	$scope.uploadCreateImage = function(fileObj) {
		$scope.showImageDiv = true;
		var loaded = 0;
		if (fileObj.files && fileObj.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$scope.imageArray.push({
					'imageUrl' : e.target.result
				});
				console.log($scope.imageArray);
			}

			reader.readAsDataURL(fileObj.files[0]);
		}

		var load = function(evt) {
			if (evt.lengthComputable) {
				$scope.progressBarValue = Math.round(evt.loaded * 100 / evt.total);
			}
		};
		var promise = AppService.uploadFileWithProgress(fileObj.files[0], $q);
		promise.then(function(response) {
			$scope.addImageToPreview(response);
		}, null, function(event) {
			load(event);
		});
	};
	$scope.removeFromTemplate = function(media) {
		if ($scope.imagePreviewData.length > 0) {
			var length = $scope.imagePreviewData.length;
			var newMediaList = [];
			for (var count = 0; count < length; count++) {
				if ($scope.imagePreviewData[count].mediaSourceId != media.mediaSourceId) {
					newMediaList.push($scope.imagePreviewData[count]);
				}
			}
			$scope.imagePreviewData = newMediaList;
			if (newMediaList.length == 0) {
				$scope.attachNewImage = false;
			}
		}
	};
} ]);


appModule.directive('imgLoad', function() {
	return {
		link : function(scope, element, attrs) {
			element.bind("load", function(event) {
				var num = scope.$eval(attrs.index);
				console.log("Num " + num);
				scope.imagePreviewData[num].imgCrop = loadImage(element, 'div' + num, '533', '400');
			});
		}
	}
});

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
