angular.module("mgis.lands", ["ui.router", "ui.bootstrap", //
	"mgis.common", //
	"mgis.lands.lands", "mgis.lands.lands.service" //

])
	.config(function ($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.when("/lands", "/lands/lands/").when("/lands/lands", "/lands/lands");
	})
	.controller("LandsController", function ($scope, LandsLandService) {

	})
;
