resourceApp.controller('addpostreqCtrl',["$scope","$rootScope","$state","RAService","masterService",function($scope,$rootScope,$state,RAService,masterService){
	$scope.$on('$viewContentLoaded', function () {
	$scope.jobCategorys();
		$scope.jobTypes();
		$scope.primarySkills();
		 $scope.secondarySkills();
		 $scope.notice();
	})
	 $scope.stage = "";
    // Navigation functions
    $scope.next = function (stage) {
      //$scope.direction = 1;
      //$scope.stage = stage;

      if ($scope.frm.$valid) {
        $scope.direction = 1;
        $scope.stage = stage;
      
      }
    };

    $scope.back = function (stage) {
      $scope.direction = 0;
      $scope.stage = stage;
    };	 
    
    
    
//    master data for jobCategory
    $scope.jobCategorys = function(){
		masterService.jobCategoryget().then(function(data) {
			$scope.jobcategoryies = data.result;
			console.log($scope.jobcategoryies);
			
		})
    }
		
//  master data for jobType
    $scope.jobTypes = function(){
    masterService.jobType().then(function(data) {
		$scope.jobtypelist = data.result;
		console.log($scope.jobtypelist);
		
	})
    }
    
	//  master data for primary skills
	 $scope.primarySkills = function(){
	 masterService.allprimaryskills().then(function(data) {
		$scope.primarySkillslist = data.result;
		console.log($scope.primarySkillslist);
		
	})
    }
	 
	//  master data for secondary skills
	 $scope.secondarySkills = function(){
	 masterService.secondaryget().then(function(data) {
		$scope.secondaryskillslist = data.result;
		console.log($scope.secondaryskillslist);
		
	})
    }
	//  master data for Notice peroid
	 $scope.notice = function(){
	 masterService.joinwithinlist().then(function(data) {
		$scope.noticelist = data.result;
		console.log($scope.noticelist);
		
	})
    }
       
	//$scope.jobcategory = ["Java Developer","UI Developer","IDM Consultant",".Net Developer","Python Developer","Testing"];
	//$scope.jobtype = ["Contract","Full-time","Part-time"];
	 $scope.jobRole = ["Fresher","Intern","Trainee","Junior Developer","Senior Developer","Project Lead"];
	 $scope.joblocation= ["Bangalore","Chennai","Hyderabad","Pune","Itanagar","Dispur","Patna","Raipur","Panaji","Gandhinagar","Punjab","Shimla","Srinagar","Ranchi",
	    	"Thiruvananthapuram","Bhopal","Mumbai","Imphal","Shillong","Aizawl","Kohima","Bhubaneswar","Jaipur","Gangtok","Noida","Amaravathi","Agartala","Lucknow","Dehradun","Kolkata"];
	 $scope.experience = ["1-2 years","2-3 years","3-5 years","5-7 years","7-10 years"]; 
	 $scope.Rate=["Hourly","Per-Day","Per-Week","Per-Month"];
	 $scope.yearsOfExperience = ["0 Year","1 Year", "2 Years" , "3 Years","4 Years", "5 Years", "6 Years","7 Years", "8 Years", "9 Years","10 Years","11 Years"];
     $scope.monthsOfExperience = ["0 Month","1 Month", "2 Months", "3 Months","4 Months", "5 Months", "6 Months","7 Months", "8 Months", "9 Months","10 Months","11 Months"];
//     $scope.primaryskills = ["Big-Data","Hadoop","RPA","Java","JDBC","HTML","HTML5","CSS3","Javascript","JQuery",
//			"Angular JS","Angular5","MySQL","MongoDB","POSTGRESQL","QA Testing","Manual Testing","Selenium",
//			"C","C++","Core Java","IDM","Python","PHP","Salesforce","Data Science","Dot Net","C#.Net",
//			"ASP.NET","DevOps","Block Chain","IOT",	"Android","iOS","Mulesoft","AWS"];
//		$scope.secondaryskills = ["Machine Learning","Cyber Security","Ruby On Rails","Bootstrap","Docker Training","OpenStack","Google Cloud","Microsoft Azure",
//			"TensorFlow","EMC-SAN","NetApp","VMWare","VMWare NSX","Shell Scripting","RHEL Cluster","IBM AIX","Power HACMP","Unity",
//			"Red Hat","CCNA","CCNP","CCIE","MCSE","Exchange Server 2013","Microsoft Private Cloud","Microsoft Azure","Microsoft Office 365","Windows Server 2016",
//			"Advanced Linux","VMware vSphere","CCNA Security","Cisco ASA Firewall","Checkpoint Firewall","Hibernate","Advanced Java","Spring","Oracle",
//			"D2k","Data Structures","AJAX","JSON","SEO","OOPS","Photoshop","SPARK","TABLLEAU","QLIK VIEW","BITCOIN","Digital Marketing","Business Intelligence","SAS BI"];

	//$scope.joining = ["Immediate","10-15 days","15-30 days","30-45 days"];
	$scope.postrequirement = {};
    $scope.addReq = function(){  
    	 
        $scope.postrequirement.primarySkills=$scope.postrequirement.primarySkills.toString();
        $scope.postrequirement.secondarySkills=$scope.postrequirement.secondarySkills.toString();
        console.log($scope.postrequirement.primarySkills);
        console.log($scope.postrequirement.secondarySkills);
        $scope.postrequirement.registrationId = localStorage.getItem('registrationId');       
        console.log($scope.registrationId);
        RAService.adddata($scope.postrequirement).then(function(response){       
        console.log($scope.bbbb);
        $state.go('RA.postarequirementlist');
        },function(err){
			if(err){
				$scope.errorMessage = err;
			}else{
				$scope.errorMessage = err;
           }   
        }
        )
	}
	

    $scope.showBulk = true;
	$scope.hideBulk = true;
    $scope.bulkFunction=function(){
    	 $scope.showBulk = !$scope.showBulk;
    	 $scope.hideBulk =!$scope.hideBulk
    }
   

    
    
}]);