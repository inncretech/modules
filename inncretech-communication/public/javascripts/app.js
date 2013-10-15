function TemplateCtrl($scope, $http){

    $scope.templateNames = [{name : "SIGNUP"}, {name : "FORGOTPWD"}, {name : "UNSUBSCRIBE"}] ;
    $scope.templateData = templateMap;

    $scope.saveTemplate = function(){
        $http.post("/emailadmin/saveTemplate.json", {subject: $scope.templateData[$scope.template.name].subject,
                                         name : $scope.template.name,
                                         templateText : $("#templateTextId").val()}, function(){
            alert("saved");
        })
    }

}