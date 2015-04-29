myApp.controller('ContactController', function($scope, $http, $routeParams) {
    console.log($routeParams.id);

    $scope.fillCityDropdown = function(country) {
        if (country === undefined || country === '') {
            return;
        }
        var requestUrl = '/cms/api/contacts/cities/' + country;
        $scope.loading = true;
        $scope.contact.address.city = '';
        $http({
            method: 'GET',
            url: requestUrl
        }).success(function(response) {
            $scope.cities = response;
            console.log($scope.cities);
            $scope.loading = false;
            console.log($scope.loading);
        }).error(function(response, status, headers, config) {
            console.log(response, status, config);
            $scope.loading = false;
        });
    };

    //In edit mode get the contact to be edited
    if ($routeParams.id !== undefined) {
        var foundAt = $scope.$parent.contacts.indexOf('id', parseInt($routeParams.id));
        $scope.contact = $scope.$parent.contacts[foundAt];
        console.log($routeParams.id, foundAt, $scope.contact);
        var city = $scope.contact.address.city;
        $scope.fillCityDropdown($scope.contact.address.country);
        //Reset the city of its value because fillCityDropdown empties it
        $scope.contact.address.city = city;
    }

    $scope.updateContact = function() {
        //For new contact use Post otherwise use put
        var httpMethod = ($scope.contact.id === undefined ? 'POST' : 'PUT');
        console.log(httpMethod);
        $scope.loading = true;
        $http({
            method: httpMethod,
            dataType: "json",
            data: $scope.contact,
            url: '/cms/api/contacts'
        }).success(function(response, status, headers) {
            console.log(response);
            if (httpMethod === 'POST') {
                console.log(response, status, headers());
                var contactId = headers().location.split('/')[2];
                console.log("contactId: " + contactId);
                $scope.contact.id = parseInt(contactId);
                $scope.$parent.contacts.push($scope.contact);
            }
            $scope.$parent.alertMessage = response;
            $scope.loading = false;
            $scope.goBack();
        }).error(function(response, status, headers, config) {
            $scope.$parent.alertMessage = response;
            console.log(response, status, config);
            $scope.loading = false;
            $scope.goBack();
        });
    };

    $scope.goBack = function() {
        window.history.back();
    };

});

