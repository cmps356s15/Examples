/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

myApp.controller('CompleteTaskController', function($scope, $routeParams, $http, $rootScope, $location){
    
    var foundAt = $scope.$parent.tasks.indexOf('id', parseInt($routeParams.id)); //get the task id from the parent scope
    $scope.task = $scope.$parent.tasks[foundAt]; //
    
    $scope.completeTask = function(){
        
        console.log($scope.task);
        
        $http({
            
            method: 'PUT',
            dataType: "json",
            data: $scope.task,
            url: '/hifz/api/task/' + $rootScope.loggedInUser.id + '/complete'
            
        }).success(function(response){
            $scope.$parent.alertMessage = 'Task with ID:' + $scope.task.id + ' has been completed!';
            $location.path("/");
            deleteTaskFromParent($scope.task.id);
            
        }).error();
    };
    
    
    var deleteTaskFromParent = function(taskID) {
        var foundAt = $scope.$parent.tasks.indexOf('id', taskID);
        if (taskID >= 0) {
            $scope.$parent.tasks.splice(foundAt, 1);
        };
        
    };
    
    
});
