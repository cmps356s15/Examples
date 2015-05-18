/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


myApp.controller('PendingController', function($rootScope, $scope, $http, $location) {
   
   var deletePendingTask = function(taskID) {
        var foundAt = $scope.tasks.indexOf('id', taskID);
        if (taskID >= 0) {
            $scope.tasks.splice(foundAt, 1);
        };
        $location.path("/");
    };
   
    Array.prototype.indexOf = function (property, value) {
        for (var i = 0; i < this.length; i++) {
            if (this[i][property] === value)
                return i;
        }
        return -1;
    };

    $scope.getPendingTasks = function() {
        $scope.loading = true;
        $scope.alertMessage = null; //clear alertMessage
        var request = $http({
            method: 'GET',
            dataType: "json",
            url: '/hifz/api/task/' + $rootScope.loggedInUser.id + '/pending'
        });
        
        request.success(function(response) {
            console.log(response);
            console.log("Type of Date: " + typeof response[0].dueDate);
            $scope.tasks = response;
            $scope.loading = false;
        });
        
        request.error(function(response, status, headers, config) {
            $scope.alertMessage = response;
            console.log(response, status, config);
            $scope.loading = false;
        });
    };

    //Call the getContacts to initialiaze the contacts array
  
       
    
    if(!typeof $rootScope.loggedInUser === 'undefined'){
   // your code here.
   
        $scope.getPendingTasks();
     };
     
     $scope.$on('loginEvent', function(event, args) {
         $scope.getPendingTasks();
     });
    
    $scope.deletePendingTask = function(taskID) {
        console.log('ContactId to delete ' + taskID);
        if (!confirm("Are you sure you want to delete contact# " + taskID + "?")) {
            $location.path("/");
            return;
        }
        console.log($rootScope.loggedInUser);
        $http({
            method: 'DELETE',
            url: '/hifz/api/task/' + $rootScope.loggedInUser.id + '/' + taskID 
        }).success(function(response) {
            console.log(response);
            $scope.alertMessage = response;
            //Delete the contact on the client side
            deletePendingTask(taskID);
            $scope.loading = false;
        }).error(function(response, status, headers, config) {
            $scope.alertMessage = response;
            console.log(response, status, config);
            $scope.loading = false;
        });
    };

    $scope.clearAlert = function() {
        $scope.alertMessage = null;
    };
    
    $scope.logout = function() {
        delete $rootScope.loggedInUser;
        $location.path("/login");
    };
});