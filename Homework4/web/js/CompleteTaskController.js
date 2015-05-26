hifzApp.controller('CompleteTaskController', function ($scope, $routeParams, $http, $rootScope, $location) {

    var foundAt = $rootScope.tasks.indexOf('id', parseInt($routeParams.id)); //get the task id from the parent scope
    $scope.task = $rootScope.tasks[foundAt];
    $scope.task.completedDate = new Date();

    $scope.completeTask = function () {
        var userId = $rootScope.getLoggedInUserId();
        console.log($scope.task);
        $http({
            method: 'put',
            dataType: 'json',
            data: $scope.task,
            url: '/hifz/api/tasks/' + userId + '/complete'

        }).success(function (response) {
            $rootScope.alertMessage = 'Task #' + $scope.task.id + ' completed';
            $location.path("/");
            deleteTaskFromParent($scope.task.id);
        }).error(function () {
            $scope.loading = false;
            alert('Submitting task failed');
        });
    };


    var deleteTaskFromParent = function (taskID) {
        var foundAt = $rootScope.tasks.indexOf('id', taskID);
        if (foundAt >= 0) {
            $rootScope.tasks.splice(foundAt, 1);
        }
    };

    $scope.onCompletedDateChange = function (completedDate, dueDate) {
        var completedDate = new Date(completedDate);
        completedDate.setHours(0, 0, 0, 0); //remove time
        console.log("completedDate: " + completedDate);

        var dueDate = new Date(dueDate);
        dueDate.setHours(0, 0, 0, 0); //remove time
        console.log("dueDate: " + dueDate);

        var completedDateElement = document.getElementById("completedDate");
        if (completedDate < dueDate) {
            completedDateElement.setCustomValidity('Completed date should be >= due date');
        } else {
            completedDateElement.setCustomValidity('');
        }
    };
});
