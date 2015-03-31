var app = angular.module('bookApp', []);

app.controller('bookController', function($scope, $http) {
	$scope.load = function(text) {
		$http.get("/web-base/books/title/" + text + ".json").success(function(response) {
			$scope.user = response.user;
				$scope.message = response.message;
		});
	}
	
	// init
	$scope.load("A simple message");
});