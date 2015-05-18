/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var myApp = angular.module('myApp', ['ngRoute']);

myApp.config(function ($routeProvider) {

    $routeProvider.when('/',
            {
                templateUrl: '/hifz/partials/pending.html',
                controller: 'PendingController'
            });
    $routeProvider.when('/login',
            {
                templateUrl: '/hifz/partials/login.html',
                controller: 'LoginController'
            });
    $routeProvider.when('/completed',
            {
                templateUrl: '/hifz/partials/completed.html',
                controller: 'CompletedController'

            });

    $routeProvider.when('/complete/:id',
            {
                templateUrl: '/hifz/partials/complete-task.html',
                controller: 'CompleteTaskController'
            });

    $routeProvider.when('/update/:id',
            {
                templateUrl: '/hifz/partials/update-task.html',
                controller: 'UpdateController'
            });
            
    $routeProvider.when('/create',
    {
        templateUrl: '/hifz/partials/add-task.html',
        controller: 'CreateController'
    });

    $routeProvider.otherwise({redirectTo: '/'});


});

myApp.run(function ($rootScope, $location) {
    $rootScope.isLoggedIn = function () {
        return !angular.isUndefined($rootScope.loggedInUser);
    };

    $rootScope.$on("$routeChangeStart", function (event, next, current) {
        console.log(event);
        console.log(next);
        console.log(current);
        if (!$rootScope.isLoggedIn()) {
            $location.path("/login");
        }
    });
});