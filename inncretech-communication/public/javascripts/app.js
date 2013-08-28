function TemplateCtrl($scope, $http){

    $scope.templateNames = [{name : "SIGNUP"}, {name : "FORGOTPWD"}] ;
    $scope.templateData = templateMap;

    $scope.saveTemplate = function(){
        $http.post("saveTemplate.json", {subject: $scope.templateData[$scope.template.name].subject,
                                         name : $scope.template.name,
                                         templateText : $("#templateTextId").val()}, function(){
            alert("saved");
        })
    }

}