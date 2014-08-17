angular.module('productApp.services', []).service('AppService', ['$http', function ($http) {

    this.uploadFileWithProgress = function(file, $q){
        var deferred = $q.defer();
        var getProgressListener = function(deferred) {
            return function(event) {
                deferred.notify(event);
            };
        };
        var formData = new FormData();
        formData.append("image", file);

        $.ajax({
            type: 'POST',
            url: "/uploadImage",
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function(response, textStatus, jqXHR) {
                deferred.resolve(response);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                deferred.reject(errorThrown);
            },
            xhr: function() {
                var myXhr = $.ajaxSettings.xhr();
                if (myXhr.upload) {
                    myXhr.upload.addEventListener(
                        'progress', getProgressListener(deferred), false);
                } else {
                    $log.log('Upload progress is not supported.');
                }
                return myXhr;
            }
        });
        return deferred.promise;
    };
    
}]);