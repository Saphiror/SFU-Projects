'use strict';


// Declare app level module which depends on filters, and services
angular.module('myApp', [
	'ngRoute',
	'myApp.filters',
	'myApp.services',
	'myApp.directives',
	'myApp.controllers'
]).
config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
	$locationProvider.html5Mode(true);
	$routeProvider.when('/course/list', {templateUrl: '/partials/courselist.html', controller: 'CourseListCtrl'});
	$routeProvider.when('/courses/:id', {templateUrl: '/partials/course.html', controller: 'CourseCtrl'});
	$routeProvider.when('/cart', {templateUrl: '/partials/cart.html', controller: 'CartCtrl'});
	$routeProvider.when('/', {templateUrl: '/partials/root.html'});
	$routeProvider.otherwise({redirectTo: '/'});
}]);




