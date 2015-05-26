hifzApp.controller('TaskController', function ($scope, $routeParams, $http, $rootScope, $location) {

    //if case of update get the task to be updated update initialize an empty task object
    if (!angular.isUndefined($routeParams.id)) {
        var taskId = parseInt($routeParams.id);
        $scope.task = getElementById($rootScope.tasks, taskId);
        $scope.task.dueDate = new Date($scope.task.dueDate);
        console.log(JSON.stringify($scope.task));
        //work around to get the Sura to display in the Dropdown
        $scope.task.surah = getElementById($rootScope.surahs, $scope.task.surah.id);
    } else {
        //Initilize the task
        $scope.task = {};
        var tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        $scope.task.dueDate = tomorrow;
        $scope.task.fromAya = 1;
        $scope.task.toAya = 1;
    }

    $scope.onFormSubmit = function () {
        var userId = $rootScope.getLoggedInUserId();
        //if no task.id then add otherwise update
        var httpVerb = angular.isUndefined($scope.task.id) ? 'post' : 'put';
        $http({
            method: httpVerb,
            dataType: "json",
            data: $scope.task,
            url: '/hifz/api/tasks/' + userId
        }).success(function (response, status, headers) {
            console.log(response);

            //in case of add then get the taskId from the location header
            if (httpVerb === 'post') {
                console.log(response, status, headers());
                var taskId = headers().location.split('/')[7];
                console.log("TaskId: " + taskId);
                $scope.task.id = parseInt(taskId);
                $rootScope.tasks.push($scope.task);
            }

            $rootScope.alertMessage = 'Task with Id #' + $scope.task.id + ' has been added!';
            $scope.loading = false;
            $location.path("/");
        }).error(function () {
            $scope.loading = false;
            alert('Submitting task failed');
        });
    };

    $scope.onSuraChange = function () {
        $scope.task.fromAya = 1;
        $scope.task.toAya = 1;
    };

    $scope.onFromAyaChange = function () {
        if (angular.isUndefined($scope.task.surah)) {
            return;
        }
        var fromAya = parseInt($scope.task.fromAya);
        var toAya = parseInt($scope.task.toAya);

        console.log("fromAya: " + fromAya);
        console.log("toAya: " + toAya);
        if (toAya < fromAya) {
            var ayaCount = $scope.task.surah.ayaCount;
            var toAya = (fromAya === ayaCount ? ayaCount : fromAya + 1);
            $scope.task.toAya = toAya;
        }
    };

    $scope.onToAyaChange = function () {
        var fromAya = parseInt($scope.task.fromAya);
        var toAya = parseInt($scope.task.toAya);
        if (toAya < fromAya) {
            $scope.task.toAya = fromAya;
        }
    };

    $scope.onDueDateChange = function (dueDate) {
        var dueDate = new Date(dueDate);
        dueDate.setHours(0, 0, 0, 0);
        console.log("dueDate: " + dueDate);

        var today = new Date();
        today.setHours(0, 0, 0, 0);
        console.log("today: " + today);

        var dueDateElement = document.getElementById("dueDate");
        if (dueDate < today) {
            dueDateElement.setCustomValidity('Due date should be >= today');
        } else {
            dueDateElement.setCustomValidity('');
        }
    };

    function getElementById(array, id) {
        for (var i = 0; i < array.length; i++) {
            if (array[i].id == id) {
                return array[i];
            }
        }
    }
});
