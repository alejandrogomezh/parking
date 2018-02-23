
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
			alert(data.message);
		});
	};
});

parkingApp.factory('admitted', function($resource) {
    return $resource('/enter', {}, {
	    	save: {
				method : 'PUT',
				isArray : false
	    	}
    });
});
parkingApp.controller("admittedCtrl", function($scope, admitted) {
	admitted.query(function(data) {
	    $scope.admitted = data;
	}, function(err) {
	    console.error("Error occured: ", err);
	});
});

//parkingApp.factory('parking', function($resource) {
//return $resource('/ingresar', {}, {
//  	save: {
//			method : 'PUT',
//			isArray : false
//  	}
//});
//});


//var app = angular.module('consumeRestApp', ['ngResource']);
//app.factory("artists", function($resource) {
//    return $resource("http://localhost:8090/ingresar");
//});
//
//app.controller("ArtistsCtrl", function($scope, artists) {
//    artists.query(function(data) {
//        $scope.artists = data;
//    }, function(err) {
//        console.error("Error occured: ", err);
//    });
//});

//app.controller("RegistryAdmittedCtrl", function($scope, parking) {
//	parking.query(function(data) {
//        $scope.artists = data;
//    }, function(err) {
//        console.error("Error occured: ", err);
//    });
//});
//
//
//$scope.create = function() {
//	User = $resource("http://localhost:8080/create", {}, {
//		save : {
//			method : 'PUT',
//			isArray : false
//		}
//	});
//
//	var user = {};
//
//	user.id = $scope.userForm.id;
//	user.name = $scope.userForm.name;
//	user.lastname = $scope.userForm.lastname;
//	user.document = $scope.userForm.document;
//	user.user = $scope.userForm.user;
//
//	$scope.Message = User.save(user);
//
//	$scope.userForm.id = "";
//	$scope.userForm.name = "";
//	$scope.userForm.lastname = "";
//	$scope.userForm.document = "";
//	$scope.userForm.user = "";
//};

//var parkingApp = angular.module("ParkingApp", []);
//
//parkingApp.controller("loginCtrl", ['$scope', '$http', function($scope, $http) {
//	$scope.submit = function(data) {
//		
//		//$scope.addRowAsyncAsJSON = function(){		
//			// Writing it to the server
//			//		
////			var dataObj = {
////				tipo : $scope.username,
////				placa : $scope.password
////			};
//			var dataObj = "tipo="+$scope.username+"&placa="+$scope.password;
//			//var res = $http.post('/ingresar', dataObj);
//			var res = $http.get('/ingresar', dataObj);
//			//var res = $http.put('/ingresar', dataObj);
//			res.success(function(data, status, headers, config) {
//				$scope.message = data;
//			});
//			res.error(function(data, status, headers, config) {
//				alert( "failure message: " + JSON.stringify({data: data}));
//			});		
//		//};
//	};
//}]);
//	

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

//var TestApp = angular.module('TestApp', []);
//
//TestApp.factory('Data', function(){
//    var service = {
//        FirstName: '',
//        setFirstName: function(name) {
//            // this is the trick to sync the data
//            // so no need for a $watch function
//            // call this from anywhere when you need to update FirstName
//            angular.copy(name, service.FirstName); 
//        }
//    };
//    return service;
//});

//// Step 1 Controller
//TestApp.controller('FirstCtrl', function( $scope, Data ){
//	Data.FirstName = 'Tridip';
//	$scope.setData = function() {
//		Data.FirstName = $scope.FirstName;
//	};
//});

//// Step 2 Controller
//TestApp.controller('SecondCtrl', function( $scope, Data ){
//	$scope.FirstName = Data.FirstName;
//	
//	$scope.getData = function() {
//		alert('get data '+ Data.FirstName)
//	};
//});
