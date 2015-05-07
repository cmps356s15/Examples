myApp.controller('LoginController', function ($scope, $rootScope, $location) {
    $scope.credentials = {
        username: '',
        password: ''
    };
    
    $scope.login = function () {
        console.log($scope.credentials);
        //Make service call using $http to authenticate the user
        /*
        var request = $http({
            method: "Post",
            dataType: "json",
            data: $scope.credentials,
            url: '/cms/api/user'
        });
        
        request.success(function(response, status, headers) {
            console.log(response);
            $rootScope.loggedInUser = response;
        });
                
        request.error(function(response, status, headers, config) {
            $scope.message = response;
            console.log(response, status, config);
        });
        */
        //For the demo I am hardcoding the user object
        $rootScope.loggedInUser = {firstName: 'Abbas', lastName: 'Ibn Firnas'};
        $location.path("/");
    };
});