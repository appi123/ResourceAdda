resourceApp.controller('customerCtrl',['$scope','$state','RAService',function($scope,$state,RAService){
		$scope.vvv = localStorage.getItem('registrationType');
		$scope.register =  $scope.vvv.split(',');
		console.log($scope.register);
		$scope.dataregister = function(){
			
			if($scope.registerData == "RA"){
				$state.go('RA.dashboard');
			}
			if($scope.registerData == "vendor"){
				$state.go('vendor.dashboard');
			}
			if($scope.registerData == "customer"){
				$state.go('customer.dashboard');
			}
		}
	
}])