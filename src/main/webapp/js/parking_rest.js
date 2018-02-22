
var parkingApp = angular.module('ParkingApp', ['ngResource']);
parkingApp.factory('parking', function($resource) {
    return $resource('/enter', {}, {
	    	save: {
				method : 'PUT',
				isArray : false
	    	}
    });
});

parkingApp.controller('parkingCtrl', function($scope, parking) {
	$scope.tipo = "moto";
	$scope.submit = function() {
		var data = {
				tipo: $scope.tipo,
				placa: $scope.placa,
				cilindraje: $scope.cilindraje
			};
		parking.save(data, function(data) {
			$scope.data = data;
			alert(data.msg);
		}, function(err) {
			alert("Error occured: "+err.data.message);
			console.log("Error occured: ", err);
		});
	};
});

parkingApp.factory('admitted', function($resource) {
    return $resource('/admitted');
});
parkingApp.controller("admittedCtrl", function($scope, admitted) {
	admitted.query(function(data) {
	    $scope.admitted = data;
	}, function(err) {
		alert("Error occured: "+err.data.message);
		console.log("Error occured: ", err);
	});
});