var myApp = angular.module('myApp', ['ngRoute']);
myApp.config(function($routeProvider) {
    $routeProvider
            .when('/',
                    {
                        templateUrl: '/cms/partials/contacts.html',
                    })
            .when('/login',
                    {
                        templateUrl: '/cms/partials/login.html',
                        controller: 'LoginController'
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

myApp.run(function($rootScope, $location) {
    $rootScope.isLoggedIn = function() {
        return !angular.isUndefined($rootScope.loggedInUser);
    };
    
    $rootScope.$on( "$routeChangeStart", function(event, next, current) {
//      console.log(event);
//      console.log(next);
//      console.log(current);
      if (!$rootScope.isLoggedIn()) {
          $location.path("/login");
      }
    });
 });
  
myApp.controller('QuController', function($scope, $http) {
    $scope.address = {
        street: 'University St',
        city: 'Doha',
        country: 'Qatar'
    };
});
