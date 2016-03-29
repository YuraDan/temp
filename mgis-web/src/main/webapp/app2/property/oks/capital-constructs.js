angular.module("mgis.capital-constructs", ["ui.router", "ui.bootstrap",
	"mgis.capital-constructs.construct",
	"mgis.capital-constructs.maps"
]) //
	.config(function ($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.when("/oks", "/oks/constructs/")
			.when("/oks/", "/oks/constructs/")
			.when("/oks/constructs", "/oks/constructs/")
			.when("/oks/maps", "/oks/maps/");
		$stateProvider //
			.state("oks", {
				url: "/oks",
				views: {
					"": {
						templateUrl: "app2/property/oks/capital-constructs.htm"
					}
				}
			}) //
		;
	}) //
;
