resourceApp.controller('postresourceCtrl',['$scope',"$state","RAService",function($scope,$state,RAService){
	$scope.$on('$viewContentLoaded', function () {
		$scope.postresourcelist();
	})
	
	$scope.postresourcelist = function(){
		RAService.postareqList().then(function(data){
			$scope.postresource = data.result;
			console.log($scope.postresource);
		},function(err){
			if(err){
				$scope.errorMessage = err;
			}
		})
	}
	

}]);

resourceApp.controller('postresourceByIdCtrl',['$scope','$state','$stateParams','RAService',function($scope,$state,$stateParams,RAService){
	$scope.$on('$viewContentLoaded', function(){
		$scope.resourcepostById();
	})
	
	$scope.resourcepostById = function(){
		RAService.postresourceById($stateParams.resourceById).then(function(data){
			$scope.resourceById = data.result;
			console.log($scope.resourceById);
		},function(err){
			if(err){
				$scope.errorMessage = err;
			}
		})
	}
}])
