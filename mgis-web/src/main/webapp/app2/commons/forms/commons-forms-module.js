angular.module("mgis.commons.forms", ["ui.bootstrap",
	"mgis.commons"
])
	.directive("commonsFormsForm", function () {
		return {
			restrict: "E",
			transclude: true,
			scope: {
				title: "@"
			},
			templateUrl: "app2/commons/forms/form.htm"
		}
	})
	.directive("commonsFormsPanel", function () {
		return {
			restrict: "E",
			transclude: true,
			scope: {
				title: "@"
			},
			templateUrl: "app2/commons/forms/panel.htm"
		}
	})
	.directive("commonsFormsDate", function () {
		return {
			restrict: "E",
			scope: {
				title: "@",
				property: "="
			},
			templateUrl: "app2/commons/forms/date.htm",
			controller: function ($scope) {
				$scope.item = {
					value: $scope.property
				}
				$scope.dateChanged = function () {
					//console.log($scope.item.value);
					$scope.property = $scope.item.value;
				}
			}
		}
	})
	.directive("commonsFormsText", function () {
		return {
			restrict: "E",
			scope: {
				title: "@",
				property: "="
			},
			templateUrl: "app2/commons/forms/text.htm"
		}
	})
	.directive("commonsFormsDropDown", function () {
		return {
			restrict: "E",
			scope: {
				title: "@",
				property: "=",
				availableElements: "="
			},
			templateUrl: "app2/commons/forms/drop-down.htm"
		}
	})
	.directive("commonsFormsDropDownAjax", function () {
		return {
			restrict: "E",
			scope: {
				title: "@",
				property: "=",
				availableElements: "=",
				refresh: "&"
			},
			templateUrl: "app2/commons/forms/drop-down-ajax.htm",
			controller: function ($scope) {
				$scope.item = {
					value: $scope.property
				}
				$scope.refreshAvailableElements = function (name) {
					$scope.refresh({name: name});
				}
				$scope.elementSelected = function ($item) {
					var val = {}
					angular.copy($item, val);
					$scope.property = val;
				}
			}
		}
	})
	.directive("commonsFormsChooseOne", function () {
		return {
			restrict: "E",
			transclude: true,
			scope: {
				title: "@",
				property: "="
			},
			templateUrl: "app2/commons/forms/choose-one.htm"
		}
	})
	.directive("commonsFormsChooseMany", function () {
		return {
			restrict: "E",
			transclude: true,
			scope: {
				title: "@",
				property: "="
			},
			templateUrl: "app2/commons/forms/choose-many.htm"
		}
	})
;
