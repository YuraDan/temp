/**
 * Created by donchenko-y on 3/23/16.
 */

angular.module("mgis.analytics", ["ui.bootstrap", "ui.select",
	"mgis.commons",
	"smart-table",
	"mgis.analytics.service"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("analytics", {
				url: "/analytics",
				templateUrl: "app2/analytics/analytics.htm"
			})

	})

	.factory("InputTaxesModule", function ($rootScope, MGISCommonsModalForm, InputTaxesService) {

		function removeItem(id, updateGrid) {
			MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
				InputTaxesService.remove(id).then(function () {
					modalInstance.close();
					updateGrid();
				});
			});
		}

		return {
			remove: removeItem
		}
	})
	.controller("InputTaxesController", function ($scope, InputTaxesModule, InputTaxesService) {

		$scope.currentPage = 1;
		$scope.itemsPerPage = 15;
		$scope.pagerMaxSize = 10;
		$scope.cadastralFind = "";
		$scope.inputTaxesPager = [];
		$scope.inputTaxesPager2 = [];


		function updateGrid() {
			InputTaxesService.get("", ($scope.currentPage - 1) * $scope.itemsPerPage, $scope.itemsPerPage, $scope.cadastralFind).then(function (data) {
				$scope.inputTaxesPager2 = data;
				//$scope.inputTaxesPager = [].concat($scope.inputTaxesPager2);

			});
		}

		$scope.pageChanged = function () {
			updateGrid();
		}


		//$scope.addItem = function () {
		//	AddressModule.add(updateGrid);
		//}
		//
		//$scope.editItem = function (id) {
		//	AddressModule.edit(id, updateGrid);
		//}

		$scope.deleteItem = function (id) {
			InputTaxesModule.remove(id, updateGrid);
		}

		$scope.find = function () {
			updateGrid();
		}

		//$scope.resetSearchClick = function () {
		//	$scope.addressFind = "";
		//	$scope.find();
		//}

		updateGrid();
	})
	.directive('csSelect', function () {
		return {
			require: '^stTable',
			template: '<input type="checkbox"/>',
			scope: {
				row: '=csSelect'
			},
			link: function (scope, element, attr, ctrl) {

				element.bind('change', function (evt) {
					scope.$apply(function () {
						ctrl.select(scope.row, 'multiple');
					});
				});

				scope.$watch('row.isSelected', function (newValue, oldValue) {
					if (newValue === true) {
						element.parent().addClass('st-selected');
					} else {
						element.parent().removeClass('st-selected');
					}
				});
			}
		};
	})
;


