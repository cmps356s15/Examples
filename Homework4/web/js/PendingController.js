hifzApp.controller('PendingController', function ($rootScope, $scope, $http, $location) {
    
    $scope.getPendingTasks = function () {
        var userId = $rootScope.getLoggedInUserId();
        $scope.loading = true;
        $rootScope.alertMessage = null; //clear alertMessage
        var request = $http({
            method: 'GET',
            dataType: "json",
            url: '/hifz/api/tasks/' + userId + '/pending'
        });

        request.success(function (response) {
            $rootScope.tasks = response;
            $scope.loading = false;
        });

        request.error(function (response, status, headers, config) {
            $rootScope.alertMessage = response;
            console.log(response, status, config);
            $scope.loading = false;
        });
    };
    
    if (angular.isUndefined($rootScope.tasks)) {
        $scope.getPendingTasks();
    }
    
    var deleteTask = function (taskId) {
        var foundAt = $rootScope.tasks.indexOf('id', taskId);
        if (taskId >= 0) {
            $rootScope.tasks.splice(foundAt, 1);
        };
        $location.path("/");
    };

    $scope.deleteTask = function (taskId) {
        console.log('TaskId to delete ' + taskId);
        if (!confirm("Are you sure you want to delete task# " + taskId + "?")) {
            return;
        }
        var userId = $rootScope.getLoggedInUserId();
        console.log($rootScope.loggedInUser);
        $http({
            method: 'delete',
            url: '/hifz/api/tasks/' + userId + '/' + taskId
        }).success(function (response) {
            console.log(response);
            $rootScope.alertMessage = response;
            deleteTask(taskId);
            $scope.loading = false;
        }).error(function (response) {
            $rootScope.alertMessage = response;
            $scope.loading = false;
        });
    };
});