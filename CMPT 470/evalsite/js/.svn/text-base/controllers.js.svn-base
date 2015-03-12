'use strict';

/* Controllers */

angular.module('myApp.controllers', []).
controller('CourseListCtrl', ['$http', '$scope', function($http, $scope) {
	$http.get('/data/alldata.json').success(function(data) {
		$scope.courses = data;
	});
}])
.controller('CourseCtrl', ['$http', '$scope', '$routeParams', function($http, $scope, $routeParams) {
	var courseID = $routeParams.id;
	$http.get('/data/alldata.json').success(function(data) {
		for (var key in data) {
			if (data[key].id == courseID) {
				$scope.data = data[key];
				break;
			}
		}
		
	});
}])
.controller('CartCtrl', [function() {

}]);