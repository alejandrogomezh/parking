var parkingApp = angular.module("ParkingApp", []);

parkingApp.controller("loginCtrl", ['$scope', '$http', function($scope, $http) {
	$scope.submit = function(data) {
		
		//$scope.addRowAsyncAsJSON = function(){		
			// Writing it to the server
			//		
//			var dataObj = {
//				tipo : $scope.username,
//				placa : $scope.password
//			};
			var dataObj = "tipo="+$scope.username+"&placa="+$scope.password;
			//var res = $http.post('/ingresar', dataObj);
			var res = $http.get('/ingresar', dataObj);
			//var res = $http.put('/ingresar', dataObj);
			res.success(function(data, status, headers, config) {
				$scope.message = data;
			});
			res.error(function(data, status, headers, config) {
				alert( "failure message: " + JSON.stringify({data: data}));
			});		
		//};
	};
}]);
	



var app = angular.module('consumeRestApp', ['ngResource']);
app.factory("artists", function($resource) {
    return $resource("http://localhost:8090/ingresar");
});

app.controller("ArtistsCtrl", function($scope, artists) {
    artists.query(function(data) {
        $scope.artists = data;
    }, function(err) {
        console.error("Error occured: ", err);
    });
});


/*
var parkingApp = angular.module('ParkingApp', ['ngResource']);

parkingApp.factory('parking', function($resource) {
    return $resource('/ingresar');
});

parkingApp.controller('loginCtrl', function($scope, parking) {
	$scope.submit = function(data) {
		var toSend = {};
		toSend.tipo = $scope.username;
		toSend.placa = $scope.password;
		parking.save(toSend);
	};
});
*/


/*
parkingApp.controller('login', function($scope, login) {
	login.query(function(data) {
        $scope.parking = data;
    }, function(err) {
        console.error('Error occured: ', err);
    });
}); 
*/

/*
var otherApp = angular.module('ParkingApp', ['ngResource']);
otherApp.factory('userService',['$rootScope', '$timeout', function($rootScope, $timeout){
	var user = {};
	return {
		getFirstname : function () {
		    return user.firstname;
		},
	
		setFirstname : function (firstname) {
		    user.firstname = firstname;
		    $timeout(function(){
		        $rootScope.$broadcast('updates');
		    }, 1000)
		}
	}
}]);

otherApp.controller('MainCtrl',['userService','$scope','$rootScope', function(userService,$scope,$rootScope) {
	userService.setFirstname('bharat');
	$scope.name = userService.getFirstname();
	$rootScope.$on('updates',function(){
	    $scope.name = userService.getFirstname();
	});
}]);

otherApp.controller('one',['userService','$scope', function(userService,$scope) {
	$scope.updateName=function(){
	    userService.setFirstname($scope.firstname);
	}
}]);
*/

var TestApp = angular.module('TestApp', []);

TestApp.factory('Data', function(){
    var service = {
        FirstName: '',
        setFirstName: function(name) {
            // this is the trick to sync the data
            // so no need for a $watch function
            // call this from anywhere when you need to update FirstName
            angular.copy(name, service.FirstName); 
        }
    };
    return service;
});


// Step 1 Controller
TestApp.controller('FirstCtrl', function( $scope, Data ){
	Data.FirstName = 'Tridip';
	$scope.setData = function() {
		Data.FirstName = $scope.FirstName;
	};
});

// Step 2 Controller
TestApp.controller('SecondCtrl', function( $scope, Data ){
	$scope.FirstName = Data.FirstName;
	
	$scope.getData = function() {
		alert('get data '+ Data.FirstName)
	};
});
