var jarvisApp = angular.module('HackathonJarvis', ['restangular']);

jarvisApp.config(function(RestangularProvider) {
      //RestangularProvider.setBaseUrl("https://ec2-54-173-75-155.compute-1.amazonaws.com:8000/");
	  RestangularProvider.setBaseUrl("http://localhost:8080/");
});

jarvisApp.controller('NotificationsController', function(Restangular, $rootScope) {
	var vm = this;
	vm.offers = [];
	//vm.offers = [{"category":"Health","description":"Hello this is health description"}, {"category":"Movie","description":"Hello this is movie description"}];
	
 vm.offersMethod = function(username, city, age) {
	    body = {
			userId:username,
            city:city,
            age:age
		}
	
		Restangular.one("/transaction/offer/predict").post("", body).then(function(response) {
			vm.offers = [];
			$rootScope.offers = [];

			var json = {description: response.offer, category:response.category};
			vm.offers.push(json);
			$rootScope.offers.push(json);
		});
	}

	//vm.img = "http://s3-us-west-2.amazonaws.com/s.cdpn.io/142996/profile/profile-80_5.jpg";
	
	vm.imageCategoryMap = {
		"HDFC":"https://lh5.ggpht.com/U1nq0meixaVEOoofwfG0Tj2_7Y0YGMDGdqnF0-N9aZhSZCe_EJ_qbx1FZPPZpGXIzes=w300",
		"ICICI":"http://a2.mzstatic.com/au/r30/Purple62/v4/ef/00/bb/ef00bb8e-8667-6d68-c534-65516909a066/icon175x175.png",
		"HSBC":"http://a5.mzstatic.com/us/r30/Purple71/v4/f2/11/ec/f211ec9f-9e51-36c4-8d9b-66218f8bdb3a/icon175x175.jpeg",
		"SBI":"http://a5.mzstatic.com/us/r30/Purple49/v4/9b/49/e6/9b49e6ed-bd09-8a08-3137-459851f14ce4/icon175x175.png",
		"BARCLAYS":"http://a4.mzstatic.com/us/r30/Purple60/v4/f4/d2/e8/f4d2e871-cb81-1c7b-0f02-43f0c631ea5e/icon175x175.png",
		"CITI":"https://lh3.googleusercontent.com/fFEI-IpI2R-BkaObKWVqUWkmWi0KDzHCjHRr4dDUGeUn8vankpWpdx0DO705A4N8dmpw=w300"
	};
	
	vm.getImage = function(category) {
		return vm.imageCategoryMap[category];
	}
});