myApp.controller('LoginController', function ($scope, $rootScope, $location, $http) {
    $scope.credentials = {
        username: '',
        password: ''
    };
    $scope.login = function () {
        console.log($scope.credentials);
        
        var request = $http({
            
            method: "Post",
            dataType: "json",
            data: $scope.credentials.username + "-" + $scope.credentials.password,
            url: '/hifz/api/user',            
            headers: {
                "Content-Type": "text/plain"
            }
            
        });
        $scope.loading = true;
        request.success(function(response, status, headers) {
            console.log(response);
           
            if (response.firstName != null){
                $rootScope.loggedInUser = response;
                $scope.loading = false;
                $location.path("/");
                $scope.$emit('loginEvent');
            }else{
                $scope.message = response;
            }
        });
                
        request.error(function(response, status, headers, config) {
            $scope.message = response;
            console.log(response, status, config);
        });
        
        $scope.clearAlert = function() {
            $scope.message = null;
        };
    };
});