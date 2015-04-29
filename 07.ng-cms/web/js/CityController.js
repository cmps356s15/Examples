myApp.controller('CityController', function($scope, $http) {
    $scope.loading = true;
    $http({
        method: 'GET',
        url: 'js/countries.json'
    }).success(function(response) {
        console.log(response);
        $scope.countries = response.countries;
        $scope.loading = false;
    }).error(function(response, status, headers, config) {
        console.log(response, status, config);
        $scope.loading = false;
    });

    $scope.fillCityDropdown = function(country) {
        var requestUrl = '/cms/api/contacts/cities/' + country;
        $scope.loading = true;
        $scope.city = '';
        $http({
            method: 'GET',
            url: requestUrl
        }).success(function(response) {
            console.log(response);
            $scope.cities = response;
            $scope.loading = false;
        }).error(function(response, status, headers, config) {
            console.log(response, status, config);
            $scope.loading = false;
        });
    };
});

