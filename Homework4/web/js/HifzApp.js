var hifzApp = angular.module('hifzApp', ['ngRoute']);

hifzApp.filter("asDate", function () {
    return function (input) {
        return new Date(input);
    };
});

hifzApp.config(function ($routeProvider) {
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
                templateUrl: '/hifz/partials/task.html',
                controller: 'TaskController'
            });

    $routeProvider.when('/addtask',
            {
                templateUrl: '/hifz/partials/task.html',
                controller: 'TaskController'
            });

    $routeProvider.otherwise({redirectTo: '/'});
});

hifzApp.run(function ($rootScope, $location) {
    $rootScope.isLoggedIn = function () {
        return !angular.isUndefined($rootScope.loggedInUser);
    };

    $rootScope.getLoggedInUserId = function () {
        if ($rootScope.isLoggedIn()) {
            return $rootScope.loggedInUser.id;
        } else {
            $location.path("/login");
        }
    };

    $rootScope.$on("$routeChangeStart", function (event, next, current) {
        if (!$rootScope.isLoggedIn()) {
            $location.path("/login");
        }
    });
});

hifzApp.controller('HomeController', function ($scope, $routeParams, $http, $rootScope, $location) {
    //load the list of surahs
    $http({
        method: 'GET',
        dataType: "json",
        url: '/hifz/api/surahs'
    }).success(function (response) {
        $rootScope.surahs = response;
        console.log("Surahs loaded!");
    }).error(function () {
        alert("Calling /hifz/api/surahs failed!");
    });

    $scope.clearAlert = function () {
        $rootScope.alertMessage = null;
    };

    $scope.logout = function () {
        delete $rootScope.loggedInUser;
        $location.path("/login");
    };

});

Array.prototype.indexOf = function (property, value) {
    for (var i = 0; i < this.length; i++) {
        if (this[i][property] === value)
            return i;
    }
    return -1;
};