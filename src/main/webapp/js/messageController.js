var app=angular.module('messenger', []);


app.controller('MessageController',function($scope,$http){
	$scope.status ='asdf';
	
	$http.get('/home').
	then(function (response) {
		$scope.status = response.data;
	}, function (response) {
		$scope.error = "Error Loading Data";
	});
	
})
	