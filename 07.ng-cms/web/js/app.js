var myApp = angular.module('myApp', ['ngRoute']);
myApp.config(function($routeProvider) {
    $routeProvider
            .when('/',
                    {
                        templateUrl: '/cms/partials/contacts.html',
                    })
            .when('/contact',
                    {
                        templateUrl: '/cms/partials/contact.html',
                        controller: 'ContactController'
                    })
            .when('/contact/:id',
                    {
                        templateUrl: '/cms/partials/contact.html',
                        controller: 'ContactController'
                    })
            .when('/cities',
                    {
                        templateUrl: '/cms/partials/cities.html',
                        controller: 'CityController'
                    })
            .otherwise({redirectTo: '/'});
});

myApp.controller('QuController', function($scope, $http) {
    $scope.address = {
        street: 'University St',
        city: 'Doha',
        country: 'Qatar'
    };
});
