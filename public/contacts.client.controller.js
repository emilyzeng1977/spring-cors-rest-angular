angular.module('contactApp', [])
.controller('ContactController', ['$scope', '$http', function($scope, $http) {
  $scope.update = function() {
		$http.get('http://localhost:8080/contact?id='+$scope.contact.id).
		    success(function(data) {
		        $scope.contact = data;
		    });
  };
}]);