angular.module('hifzApp').controller('CompletedController', function($rootScope, $scope, $http, $location) {

    $scope.getCompletedTasks = function() {
        var userId = $rootScope.getLoggedInUserId();
        $scope.alertMessage = null; //clear alertMessage
        var request = $http({
            method: 'GET',
            dataType: "json",
            url: '/hifz/api/tasks/' + userId + '/completed'
        });
        
        request.success(function(response) {
            $scope.tasks = response;
        });
        
        request.error(function () {
            alert('Submitting task failed');
        });
    };

    $scope.getCompletedTasks();  
});