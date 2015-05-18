/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

myApp.controller('CreateController', function ($scope, $routeParams, $http, $rootScope, $location) {

    //var foundAt = $scope.$parent.tasks.indexOf('id', parseInt($routeParams.id)); //get the task id from the parent scope
    //$scope.task = $scope.$parent.tasks[foundAt]; //
    //$scope.task.dueDate = new Date($scope.task.dueDate);
    console.log($scope.task);

    //load the list of surahs automatically
    $http({
        method: 'GET',
        dataType: "json",
        url: '/hifz/api/surahs'

    }).success(function (response) {
        $scope.surahs = response;
        console.log("Surahs loaded!");
    }).error();

    $scope.createTask = function () {
        
        $scope.task.surah = $scope.surahs[$scope.task.surah.id - 1]; //resolve the surah object from the id
        //$scope.$parent.tasks[foundAt].surah = $scope.task.surah; //update the task in the parent scope with the new surah

        $http({
            method: 'POST',
            dataType: "json",
            data: $scope.task,
            url: '/hifz/api/task/pending/' + $rootScope.loggedInUser.id
        }).success(function(response, status, headers) {
            console.log(response);
            
                console.log(response, status, headers());
                var taskId = headers().location.split('/')[8];
                console.log("contactId: " + taskId);
                $scope.task.id = parseInt(taskId);
                $scope.$parent.tasks.push($scope.task);
            
            $scope.$parent.alertMessage = 'task with ID: ' + $scope.task.id + ' has been created!';
            $scope.loading = false;
            $location.path("/");
        }).error(function(response, status, headers, config) {
            $scope.$parent.alertMessage = 'error creating tasks';
            console.log(response, status, config);
            $scope.loading = false;
            $location.path("/");
        });

    };




});
