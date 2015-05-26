hifzApp.controller('LoginController', function ($scope, $rootScope, $location, $http) {
    $scope.credentials = {
        username: '',
        password: ''
    };
    $scope.login = function () {
        //console.log($scope.credentials);

        var request = $http({
            method: "Post",
            datatype: "json",
            data: $scope.credentials,
            url: '/hifz/api/login'
        });
        $scope.loading = true;
        request.success(function (response, status, headers) {
            $rootScope.loggedInUser = response;
            $scope.loading = false;
            $location.path("/");
        });

        request.error(function (response, status, headers, config) {
            $scope.message = response;
            $scope.loading = false;
            //console.log(response, status, config);
            $location.path("/login");
        });

        $scope.clearAlert = function () {
            $scope.message = null;
        };
    };
});