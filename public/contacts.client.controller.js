function Contacts($scope, $http) {
	if ($scope.id === undefined){
		$scope.id=1
	};
    $http.get('http://localhost:8080/contact?id='+$scope.id).
        success(function(data) {
            $scope.contact = data;
        });
}
