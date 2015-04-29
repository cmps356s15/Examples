var myApp = angular.module('myApp', []);

myApp.controller('FlickrController', function($scope, $http) {
    $http({
            method: 'JSONP',
            url: 'http://api.flickr.com/services/feeds/photos_public.gne',
            params: {
                tags: 'Qatar University',
                format: 'json',
                jsoncallback: 'JSON_CALLBACK'
            }
        }).success(function(response) {
            $scope.photos = response;
    });
 });