resourceApp.controller('vendordashboardCtrl', function ($scope,$http) {
	

	debugger;
	
	$http.get("https://api.myjson.com/bins/aeoqi")
    .then(function(response) {
        $scope.myWelcome = response.data;
        console.log( $scope.myWelcome);
    
        $scope.mydonut($scope.myWelcome);
      
        console.log($scope.myWelcome)
    });
	
    
	    $scope.mydonut = function(object){
	    	debugger;
	        Morris.Donut({
	        element: 'donut-example',
	        data:object,
	        colors:[ '#e8a742', '#e49316', '#285484', '#034a96', '#e8a742', '#285484', '#034a96', '#034a96','#e8a742', '#e49316', '#285484', '#034a96']

	        });
	    };
	    
	    $http.get("https://api.myjson.com/bins/x3jde")
	    .then(function(response) {
	        $scope.linechart = response.data;
	        console.log( $scope.linechart);
	    
	        $scope.line($scope.linechart);
	      
	        console.log($scope.myWelcome)
	    });
		
	    
		    $scope.line = function(object){
		    	debugger;
		    	new Morris.Line({
		        element: 'myfirstchart',
		        data:object,
		       
		        xkey: 'year',
				  
				  ykeys: ['value'],
				 
				  labels: ['Value']
		        });
		    };
	  
	  
	    
	    	
/*	new Morris.Line({
		  // ID of the element in which to draw the chart.
		  element: 'myfirstchart',
		  // Chart data records -- each entry in this array corresponds to a point on
		  // the chart.
		  
			  data: [
		     {
		    	 "year": "2013", "value": "100" },
		     
		    { 
		    		 "year": "2014", "value": "50" 
		    },
		    { year: '2015', value: 75 },
		    { year: '2016', value: 60 },
		    { year: '2017', value: 90 },
		  ]
	,
		
		  xkey: 'year',
		  
		  ykeys: ['value'],
		 
		  labels: ['Value']
	
	
		});*/
	
	/* Morris.Donut({
		        element: 'donut-example',
		         data: [
		           {label: "Ui Developers", value: 600},
		           {label: "Java Developers", value: 800},
		           {label: "Sailpoint", value: 200},
		           {label: "Oracle IDM", value: 250},
		           {label: ".Net Developer", value: 180}
		         ]
		       });*/
	
	 
	/*    Morris.Area({
	    	  element: 'area-example',
	    	  data: [
	    	    { y: '2012', a: 100, b: 90 },
	    	    { y: '2013', a: 75,  b: 65 },
	    	    { y: '2014', a: 50,  b: 40 },
	    	    { y: '2015', a: 75,  b: 65 },
	    	    { y: '2016', a: 50,  b: 40 },
	    	    { y: '2017', a: 75,  b: 65 },
	    	    { y: '2018', a: 100, b: 90 }
	    	  ],
	    	  xkey: 'y',
	    	  ykeys: ['a', 'b'],
	    	  labels: ['Requirement A', 'Resource B']
	    	});*/
	
	 Morris.Bar({
		       element: 'bar-example',
		         data: [
		          { y: 'HYD',a: 100, b: 90,c:25 },
		         { y: 'BLR', a: 50,  b: 20,c:55 },
		           { y: 'MUM', a: 75,  b: 40,c:55 },
		          { y: 'PUN', a: 60,  b: 52,c:55 },
		          { y: 'KOL', a: 90,  b: 75,c:55 }
		          
		         ],
		         
		         xkey: 'y',
		        ykeys: ['a', 'b','c'],
		         labels: ['Java', 'UI','Testing'],
		         colors:[ '#e8a742', '#e49316', '#285484']
		       });
	 
	 //pie chart in dev express
	 
	 $http.get("https://api.myjson.com/bins/15ckfe")
     .then(function(response) { 
         $scope.keyd = response.data;
		 $scope.mydon($scope.keyd);
	 console.log($scope.keyd ) ;
      }); 
	  $scope.mydon = function(object){ 
	
	
	$scope.chartOps = {
        palette:  ['#e8a742', '#e49316', '#285484', '#034a96', '#e8a742', '#285484', '#034a96', '#034a96','#e8a742', '#e49316', '#285484', '#034a96'],
        dataSource: object,
        title: "Technologies vs Resource",
        
        "export": {
            enabled: true
        },
        series: [{
            argumentField: "Tech",
            valueField: "count",
            label: {
                visible: true,
                connector: {
                    visible: true,
                    width: 0.5
                },
                format: "fixedPoint",
                customizeText: function (point) {
                    return point.argumentText + ": " + point.valueText + "";
                }
            },
            smallValuesGrouping: {
                mode: "smallValueThreshold",
                threshold: 4.5
            }
        }]
    };
	}
	  
	  //end of pie chart//
	  
	  //start of doughnut chart//
	  $http.get("https://api.myjson.com/bins/15ckfe")
	     .then(function(response) { 
	         $scope.keyd = response.data;
			 $scope.mydony($scope.keyd);
		 console.log($scope.keyd ) ;
	      }); 
		  $scope.mydony = function(object){
		$scope.chartOpts = {
	        type: "doughnut",
	        palette: ['#e8a742', '#e49316', '#285484', '#034a96', '#747677', '#A7CA74'],
	        title: "Technologies vs Resource",
	        dataSource: object,
	        
	        "export": {
	            enabled: true
	        },
	        series: [{
	            smallValuesGrouping: {
	                mode: "topN",
	                topCount: 3
	            },            
	            argumentField: "Tech",
	            valueField: "count",
	            label: {
	                visible: true,
	                format: "fixedPoint",
	                customizeText: function (point) {
	                    return point.argumentText + ": " + point.valueText + "";
	                },
	                connector: {
	                    visible: true,
	                    width: 1
	                }
	            }
	        }]
	    };
		}
		  // end of doughnut chart//
		  
		  
		  //start of stack bar charts//
		  $http.get("https://api.myjson.com/bins/e7mr2")
		    .then(function(response) {
		        $scope.key10 = response.data;
			
			 console.log($scope.key10 ) ;
			 
			 $scope.myt($scope.key10);
			 
		     });
			 $scope.myt = function(object){
		    $scope.char = {
			
		        dataSource: object.key,
		        commonSeriesSettings: {
		            argumentField: "state",
		            type: "stackedBar"
		        },
		        palette:  ['#e8a742', '#e49316', '#285484', '#034a96', '#e8a742', '#285484', '#034a96', '#034a96','#e8a742', '#e49316', '#285484', '#034a96'],
		        series:object.Value ,
		        
		        valueAxis: {
		            title: {
		                text: "resources"
		            },
		            position: "left"
		        },
		        title: "Location vs Developers",
		        "export": {
		            enabled: true
		        },
		        tooltip: {
		            enabled: true,
		            location: "edge",
		            customizeTooltip: function (arg) {
		                return {
		                    text: arg.seriesName + " resource: " + arg.valueText
		                };
		            }
		        }
		    };
			}
			 //end of stackbar charts//
	 
	 
	
            });