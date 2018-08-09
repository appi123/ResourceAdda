resourceApp.factory('masterService',["$http","$q","APIURL",function($http,$q,APIURL){
	return{
		jobCategory: function(){
			var deferred = $q.defer();
			$http.get(APIURL + '/ResourceAdda/rest/jobCategory/listJobCategory/1/10').success(function(response){
				deferred.resolve(response);
			}).error(function(err){
				deferred.reject(err);
			})
			return deferred.promise;
		},
		jobType: function(){
			$http.get(APIURL + '/ResourceAdda/rest/jobType/listJobType/1/10').success(function(response){
				deferred.resolve(response);
			}).error(function(err){
				deferred.reject(err);
			})
			return deferred.promise;
		},
		joinwithin: function(){
			$http.get(APIURL + '/ResourceAdda/rest/joinWithIn/listJoinWithIn/1/10').success(function(response){
				deferred.resolve(response);
			}).error(function(err){
				deferred.reject(err);
			})
			return deferred.promise;
		}
	}
}])