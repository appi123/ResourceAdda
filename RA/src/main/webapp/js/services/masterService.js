
resourceApp.factory('masterService',["$http","$q","APIURL",function($http,$q,APIURL){

	return{
		
		joinwithinlist: function(){
			var deferred = $q.defer();
			$http.get('rest/joinWithIn/listJoinWithIn/1/10').success(function(response){
				deferred.resolve(response);
			}).error(function(err){
				deferred.reject(err);
			})
			return deferred.promise;
		},
		
		createnewJoin: function(newJoin1){
			var deferred = $q.defer();
			$http.post('rest/joinWithIn/createjoinWithIn', newJoin1).success(function(response){
				deferred.resolve(response);
			}).error(function(err){
				deferred.reject(err);
			})
			return deferred.promise;
		},
		
		joinwithingetById: function(id){
			var deferred = $q.defer();
			$http.get('rest/joinWithIn/findOneByPrimaryId/'+ id).success(function(response){
				deferred.resolve(response);
			}).error(function(err){
				deferred.reject(err);
			})
			return deferred.promise;
		},
		
		updateJoin: function(joinWithIn1){
			var deferred = $q.defer();
			$http.put('rest/joinWithIn/updatejoinWithIn/'+ joinWithIn1._id, joinWithIn1).success(function(response){
				deferred.resolve(response);
			}).error(function(err){
				deferred.reject(err);
			})
			return deferred.promise;
		},
		
		jobCategoryget:function(){
	          var deferred = $q.defer();
	          $http.get('rest/jobCategory/listJobCategory').success(function(response){
	          	  deferred.resolve(response);
	          }).error(function(err){
	          	  deferred.reject(err);
	          })
	          return deferred.promise;
	       },
	    jobCategorypost:function(postcategory1){
	         var deferred = $q.defer();
	         $http.post('rest/jobCategory/createJobCategory', postcategory1).success(function(response){
	         	deferred.resolve(response);
	         }).error(function(err){
	         	deferred.reject(err);
	         })
	         return deferred.promise;
	       },
	    jobCategoryById:function(id){
	        var deferred = $q.defer();
	        $http.get('rest/jobCategory/findOneByPrimaryId/' + id).success(function(response){
	          deferred.resolve(response);
	        }).error(function(err){
	          deferred.reject(err);
	        })
	        return deferred.promise;
	       },
	    jobCategoryPut:function(putdata){
	        var deferred = $q.defer();
	        $http.put('rest/jobCategory/updateJobCategory/'+ putdata._id, putdata).success(function(response){
	          deferred.resolve(response);
	        }).error(function(err){
	            deferred.reject(err);
	        })
	        return deferred.promise;
	       },    		
		jobType: function(){
			var deferred = $q.defer();
			
			$http.get('rest/jobType/listJobType/1/10').success(function(response){
				deferred.resolve(response);
			}).error(function(err){
				deferred.reject(err);
			})
			return deferred.promise;
		},
		newjobtype: function(){
			var deferred = $q.defer();
			$http.get('rest/jobType/listJobType/1/10').success(function(response){
				deferred.resolve(response);
			}).error(function(err){
				deferred.reject(err);
			})
			return deferred.promise;
		},
		
		saveNewJobtype: function(alluser){
			var deferred = $q.defer();
			$http.post('rest/jobType/createJobType', alluser).success(function(response){
				deferred.resolve(response);
			}).error(function(err){
				deferred.reject(err);
			})
			return deferred.promise;
		},
		
		getJobtypeById: function(id){
			var deferred = $q.defer();
			$http.get('rest/jobType/findOneByPrimaryId/' + id).success(function(response){
					deferred.resolve(response);
			}).error(function(err){
				deferred.reject(err);
			});
			return deferred.promise;
   		},
   		updateJobtype: function(putjobtype){
			var deferred = $q.defer();
			$http.put('rest/jobType/updateJobType/' + putjobtype._id, putjobtype).success(function(response){
				deferred.resolve(response);
			}).error(function(err){
				deferred.reject(err);
			})
			return deferred.promise;
		},
		allprimaryskills:function(){
			var deferred=$q.defer();
			$http.get('rest/primarySkills/listPrimarySkills/1/10').success(function(response){
				deferred.resolve(response);
			}).error(function(err){
				deferred.reject(err);
			})
			return deferred.promise;
		},
		saveprimaryskills: function(alluser){
			var deferred = $q.defer();
			$http.post('rest/primarySkills/createPrimarySkills', alluser).success(function(response){
				deferred.resolve(response);
			}).error(function(err){
				deferred.reject(err);
			})
			return deferred.promise;
		},
		
		getPrimaryskillsById: function(id){
			var deferred = $q.defer();
			$http.get('rest/primarySkills/findOneByPrimaryId/' + id).success(function(response){
					deferred.resolve(response);
			}).error(function(err){
				deferred.reject(err);
			});
			return deferred.promise;
   		},
		
   		updatePrimaryskills: function(primary){
			var deferred = $q.defer();
			$http.put('rest/primarySkills/updatePrimarySkills/' + primary._id, primary).success(function(response){
				deferred.resolve(response);
			}).error(function(err){
				deferred.reject(err);
			})
			return deferred.promise;
		},
	   secondaryget:function(){
		   var deferred = $q.defer();
		   $http.get('rest/secondarySkills/listSecondarySkills/1/10').success(function(response){
			   deferred.resolve(response);
		   }).error(function(err){
			   deferred.reject(err);
		   })
		   return deferred.promise;
	   },
	   secondaryadd:function(addskill){
		   var deferred = $q.defer();
		   $http.post('rest/secondarySkills/createSecondarySkills', addskill).success(function(response){
			   deferred.resolve(response);
		   }).error(function(err){
			   deferred.reject(err);
		   })
		   return deferred.promise;
	   },
	   secondaryById:function(id){
		   var deferred = $q.defer();
		   $http.get('rest/secondarySkills/findOneByPrimaryId/' + id).success(function(response){
			   deferred.resolve(response);
		   }).error(function(err){
			   deferred.reject(err);
		   })
		   return deferred.promise;
	   },
	   secondaryedit:function(dataput1){
		   var deferred = $q.defer();
		   $http.put('rest/secondarySkills/updateSecondarySkill/' + dataput1._id,dataput1).success(function(response){
			   deferred.resolve(response);
		   }).error(function(err){
			   deferred.reject(err);
		   })
		   return deferred.promise;
	   }
	   
	}
}])


