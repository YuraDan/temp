var mgisApp = angular.module("mgis", //
[ "ui.router", 'pascalprecht.translate', //
"mgis.isogd", "mgis.menu.main", "mgis.oks" ]);

mgisApp.config(function($stateProvider, $urlRouterProvider, $translateProvider) {
	// $urlRouterProvider //
	// .when("isogd", function() {
	// console.log("isogd...");
	// }) //
	// .otherwise("/");

	$stateProvider //
	.state("isogd", {
		url : "/isogd",
		data : {
			displayName : "ISOGD"
		},
		views : {
			"" : {
				templateUrl : "app2/isogd/isogd.htm",
				controller : "ISOGDCtrl"
			}
		}
	}) //
	.state("oks", {
		url : "/oks",
		views : {
			"" : {
				templateUrl : "app2/oks/oks.htm",
				controller : "OKSCtrl"
			}
		}
	}) //
	;

	$translateProvider.preferredLanguage('ru_RU');
	$translateProvider.useStaticFilesLoader({
		prefix : 'app2/i18n/locale-',
		suffix : '.json'
	});

});
