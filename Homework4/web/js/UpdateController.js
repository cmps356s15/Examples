myApp.controller('UpdateController', function ($scope, $routeParams, $http, $rootScope, $location) {

    var foundAt = $scope.$parent.tasks.indexOf('id', parseInt($routeParams.id)); //get the task id from the parent scope
    $scope.task = $scope.$parent.tasks[foundAt]; //
    $scope.task.dueDate = new Date($scope.task.dueDate);
    console.log($scope.task);

    //load the list of surahs automatically
    $http({
        method: 'GET',
        dataType: "json",
        data: $scope.task,
        url: '/hifz/api/surahs'

    }).success(function (response) {
        $scope.surahs = response;
        console.log("Surahs loaded!");
    }).error();

    $scope.updateTask = function () {
        
        $scope.task.surah = $scope.surahs[$scope.task.surah.id - 1]; //resolve the surah object from the id

        $http({
            method: 'PUT',
            dataType: "json",
            data: $scope.task,
            url: '/hifz/api/task/' + $rootScope.loggedInUser.id + '/update'

        }).success(function (response) {
            console.log("Task: " + $scope.task);
            console.log("Task.surah.ID: " + $scope.task.surah.id);
            //var found = $scope.surahs[$scope.task.surah.id-1];
            //console.log("Found at: " + found);
            
            $scope.$parent.alertMessage = 'Task with ID:' + $scope.task.id + ' has been updated!';
            $location.path("/");
           

        }).error();

    };




});
