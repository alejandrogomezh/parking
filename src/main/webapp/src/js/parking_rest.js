
var global = {input:{},output:{},filter:{}};

var parkingApp = angular.module('ParkingApp', ['ngResource']);
parkingApp.factory('input', function($resource) {
    return $resource('/input', {}, {
	    	save: {
				method : 'PUT',
				isArray : false
	    	}
    });
});
parkingApp.controller('inputCtrl', function($scope, input) {
	global.input = $scope;
	$scope.tipo = "moto";
	$scope.placa = "";
	$scope.cilindraje  = "";
	$scope.buttonInputCar = function() {
		var data = {
				tipo: $scope.tipo,
				placa: $scope.placa,
				cilindraje: $scope.cilindraje
			};
		input.save(data, function(data) {
			$scope.data = data;
			alert(data.msg);
		}, function(err) {
			console.log("Error occured: ", err);
		});
	};
});




parkingApp.factory('output', function($resource) {
    return $resource('/output', {}, {
	    	save: {
				method : 'PUT',
				isArray : false
	    	}
    });
});
parkingApp.controller('outputCtrl', function($scope, output) {
	global.output = $scope;
	$scope.buttonOutputCar = function(placa) {
		var data = {
				tipo: "",
				placa: placa,
				cilindraje: 0
			};
		output.save(data, function(data) {
			$scope.data = data;
			global.filter.load();
			if(data.msg){
				alert(data);
			}else{
				//alert(data.);
			}
			
		}, function(err) {
			console.log("Error occured: ", err);
		});
	};
});


parkingApp.factory('registerFilter', function($resource,$timeout) {
    return $resource('/registerFilter', {}, {
    	query: {
			method : 'GET',
			isArray: true,
			params:{filter:"@filter"}
		}
    });
});
parkingApp.controller("registerFilterCtrl", function($scope, registerFilter, $interval) {
	global.filter = $scope;
	$scope.filterType = "All";
		 
	$scope.load = 	function(){
		registerFilter.query({filter:$scope.filterType}, function(data) {
		    $scope.$admittes = data;
		}, function(err) {
			console.log("Error occured: ", err);
		});
	};
	
	var theInterval = $interval(function(){
		$scope.load();
	}.bind(this), 10000);    
	
	$scope.$on('$destroy', function () {
		$interval.cancel(theInterval)
	});

	$scope.newFilter = function(filter){
		$scope.filterType = filter;
		$scope.load();
	}
	
	$scope.load();
});
