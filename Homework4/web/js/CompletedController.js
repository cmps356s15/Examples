/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module('myApp').controller('CompletedController', function($rootScope, $scope, $http, $location) {
   
 console.log("I am in completed!");

    $scope.getCompletedTasks = function() {
          console.log("I am in completed!");
        $scope.alertMessage = null; //clear alertMessage
        var request = $http({
            method: 'GET',
            dataType: "json",
            url: '/hifz/api/task/' + $rootScope.loggedInUser.id + '/completed'
        });
        
        request.success(function(response) {
            console.log("I am in completed!");
            console.log(response);
            $scope.tasks = response;
           // $scope.loading = false;
        });
        
        request.error(function(response, status, headers, config) {
            //$scope.alertMessage = response;
            console.log(response, status, config);
            //$scope.loading = false;
        });
    };

    //Call the getContacts to initialiaze the contacts array
  
    console.log("YOU HERE?!!!");
    $scope.getCompletedTasks();

    $scope.clearAlert = function() {
        $scope.alertMessage = null;
    };
    
    
});