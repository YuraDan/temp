angular.module("mgis.property", ["ui.router", "ui.bootstrap", "ui.select",
	"mgis.commons",
	"mgis.nc.services"
])
	.directive("mgisPropertyRights", function () {
		return {
			restrict: "E",
			scope: {
				rights: "=",
				form: "=",
				encumbrance: "="
			},
			templateUrl: "app2/property/property-rights.htm",
			controller: function ($scope,
								  NcLandRightKindService,
								  NcLandEncumbranceService,
								  NcOKFSService) {
				NcLandRightKindService.get().then(function (landRightKinds) {
					$scope.availableLandRightKinds = landRightKinds.list;
				});
				NcOKFSService.get().then(function (landOwnershipForms) {
					$scope.availableLandOwnershipForms = landOwnershipForms.list;
				});
				NcLandEncumbranceService.get().then(function (landEncumbrances) {
					$scope.availableLandEncumbrances = landEncumbrances.list;
				});
			}
		}
	})
	.directive("mgisPropertyPayments", function () {
		return {
			restrict: "E",
			scope: {
				payments: "=",
				form: "="
			},
			templateUrl: "app2/property/property-payments.htm",
			controller: function ($scope) {	}
		}
	})
;
