var customerApp = angular.module('customerApp', []);

customerApp.controller('CustomerController', CustomerController);

function CustomerController($scope) {
    $scope.isAddMode = true;
    $scope.customer = {
        name: '',
        city: ''
    };

    $scope.customers = JSON.parse(localStorage.getItem('customers'));

    //If no customers in the localStorage then initialize the array with test data
    if ($scope.customers === null || $scope.customers === undefined) {
        $scope.customers = [
            {name: 'Ali Baba', city: 'Doha'},
            {name: 'Abbas Ibn Firnas', city: 'Baghdad'},
            {name: 'Samir Saghir', city: 'Dubai'},
            {name: 'Fatima Batouta', city: 'Fes'}
        ];
        
        localStorage.setItem('customers', JSON.stringify($scope.customers));
    }

    $scope.editCustomer = function(index) {
        console.log('Customer index to edit ' + index);
        $scope.customer = $scope.customers[index];
        $scope.isAddMode = false;
    };

    $scope.newCustomer = function() {
        $scope.customer = {
            name: '',
            city: ''
        };
        $scope.isAddMode = true;
    };

    $scope.isInvalidCustomer = function() {
        console.log($scope.customer.name, $scope.customer.city);
        return ($scope.customer.name.trim() === '' 
                || $scope.customer.city.trim() === '');
    };
    
    $scope.addCustomer = function() {
        //console.log($scope.customer);
        $scope.customers.push($scope.customer);
        $scope.customer = {
            name: '',
            city: ''
        };
    };

    $scope.deleteCustomer = function(index) {
        console.log('Customer index to delete ' + index);
        var customerName = $scope.customers[index].name;
        if (confirm("Are you sure you want to delete " + customerName + "?")) {
            //Delete the element from the array
            $scope.customers.splice(index, 1);
        }
    };

    //changes to customers will be auto saved to the localStorage
    $scope.$watch('customers', function(newValue, oldValue) {
        if (oldValue !== newValue) {
            console.log(newValue, oldValue);
            localStorage.setItem('customers', JSON.stringify(newValue));
        }
    }, true);

}