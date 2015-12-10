angular.module("mgis.commons.forms", ["ui.bootstrap",
	"mgis.commons"
])
	.constant("NUMBER_PATTERN", /^\d+$/)
	.constant("INTEGER_PATTERN", /^[+-]?\d+?$/)
	.constant("FLOAT_PATTERN", /^[+-]\d+(\.\d+)?$/)
	.directive("commonsFormsForm", function () {
		return {
			restrict: "E",
			transclude: true,
			scope: {
				title: "@",
				name: "@"
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
	.directive("commonsFormsField", function () {
		return {
			restrict: "E",
			transclude: true,
			scope: {
				title: "@"
			},
			templateUrl: "app2/commons/forms/field.htm"
		}
	})
	.directive("commonsFormsCheck", function () {
		return {
			restrict: "E",
			transclude: true,
			scope: {
				title: "@",
				property: "=",
				form: "=",
				name: "@"
			},
			templateUrl: "app2/commons/forms/check.htm"
		}
	})
	.directive("commonsFormsText", function () {
		return {
			restrict: "E",
			scope: {
				title: "@",
				property: "=",
				textChange: "&",
				form: "=",
				name: "@",
				required: "=",
				pattern: "="
			},
			templateUrl: "app2/commons/forms/text.htm",
			controller: function ($scope) {
				$scope.valueChanged = function () {
					if ($scope.textChange) {
						$scope.textChange({event: event});
					}
				}
			}
		}
	})
	.directive("commonsFormsDate", function () {
		return {
			restrict: "E",
			scope: {
				title: "@",
				property: "=",
				form: "=",
				name: "@",
				required: "=",
				pattern: "="
			},
			templateUrl: "app2/commons/forms/date.htm",
			controller: function ($scope) {
				$scope.item = {
					value: $scope.property
				}
				$scope.dateChanged = function () {
					$scope.property = $scope.item.value;
				}
			}
		}
	})
	.directive("commonsFormsDropDown", function () {
		return {
			restrict: "E",
			scope: {
				title: "@",
				property: "=",
				availableElements: "=",
				form: "=",
				name: "@",
				required: "=",
				elementLabelExpression: "@"
			},
			templateUrl: "app2/commons/forms/drop-down.htm",
			controller: function ($scope) {
				$scope.property__flag = undefined;
				$scope.options = ($scope.elementLabelExpression ? $scope.elementLabelExpression : "element.name") + " for element in availableElements";
				$scope.$watch("property", function (value) {
					$scope.property__flag = !$scope.property || angular.equals({}, $scope.property) ? null : true;
				});
			}
		}
	})
	.directive("commonsFormsDropDownAjax", function () {
		return {
			restrict: "E",
			scope: {
				title: "@",
				property: "=",
				availableElements: "=",
				availableElementsLoader: "&",
				form: "=",
				name: "@",
				required: "=",
				elementFilter: "@",
				elementLabelExpression: "@"
			},
			templateUrl: "app2/commons/forms/drop-down-ajax.htm",
			controller: function ($scope) {
				$scope.item = {
					value: $scope.property
				}
				$scope.choices = "elem in availableElements | filter: {" + ($scope.elementFilter ? $scope.elementFilter : "name") + ": $select.search}"
				var labelExpression = ($scope.elementLabelExpression ? $scope.elementLabelExpression :
						($scope.elementFilter ? $scope.elementFilter : "name")
				);
				$scope.displayLabel = "elem." + labelExpression;
				$scope.matchLabel = "item.value." + labelExpression;
				$scope.refreshAvailableElements = function (name) {
					if ($scope.availableElementsLoader) {
						$scope.availableElementsLoader({search: name});
					}
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
				property: "=",
				availableItems: "&"
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
