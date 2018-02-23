var parkingApp = angular.module('ParkingApp', ['ngResource']);
parkingApp.factory('login', function($resource) {
    return $resource('/login', {}, {
	    	save: {
				method : 'PUT',
				isArray : false
	    	}
    });
});

parkingApp.controller('loginCtrl', function($scope, login) {
	$scope.submit = function() {
		var data = {
			username: $scope.username,
			password: $scope.password
		};
		login.save(data, function(data) {
			$scope.data = data;
		});
	};
});
