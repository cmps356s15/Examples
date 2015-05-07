myApp.controller('ContactsController', function($rootScope, $scope, $http, $location) {
    $scope.loading = true;
      
    var request = $http({
        method: 'GET',
        url: 'js/countries.json'
    });
    
    request.success(function(response) {
        console.log(response);
        $scope.countries = response.countries;
    });
    
    request.error(function(response, status, headers, config) {
        console.log(response, status, config);
    });

    //private function not attached to the scope
    var deleteContact = function(contactId) {
        var foundAt = $scope.contacts.indexOf('id', contactId);
        if (contactId >= 0) {
            $scope.contacts.splice(foundAt, 1);
        };
        $location.path("/");
    };

    Array.prototype.indexOf = function (property, value) {
        for (var i = 0; i < this.length; i++) {
            if (this[i][property] === value)
                return i;
        }
        return -1;
    };

    $scope.getContacts = function() {
        $scope.alertMessage = null; //clear alertMessage
        var request = $http({
            method: 'GET',
            dataType: "json",
            url: '/cms/api/contacts'
        });
        
        request.success(function(response) {
            console.log(response);
            $scope.contacts = response;
            $scope.loading = false;
        });
        
        request.error(function(response, status, headers, config) {
            $scope.alertMessage = response;
            console.log(response, status, config);
            $scope.loading = false;
        });
    };

    //Call the getContacts to initialiaze the contacts array
    $scope.getContacts();

    $scope.deleteContact = function(contactId) {
        console.log('ContactId to delete ' + contactId);
        if (!confirm("Are you sure you want to delete contact# " + contactId + "?")) {
            return;
        }

        $http({
            method: 'DELETE',
            url: '/cms/api/contacts/' + contactId
        }).success(function(response) {
            console.log(response);
            $scope.alertMessage = response;
            //Delete the contact on the client side
            deleteContact(contactId);
            $scope.loading = false;
        }).error(function(response, status, headers, config) {
            $scope.alertMessage = response;
            console.log(response, status, config);
            $scope.loading = false;
        });
    };

    $scope.clearAlert = function() {
        $scope.alertMessage = null;
    };
    
    $scope.logout = function() {
        delete $rootScope.loggedInUser;
        $location.path("/login");
    };
});