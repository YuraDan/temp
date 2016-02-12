angular.module("mgis.capital-constructs.construct", ["ui.router", "ui.bootstrap", "ui.mask",
	"mgis.commons",
	"mgis.commons.forms",
	"mgis.capital-constructs.construct.service",
	"mgis.property",
	"mgis.capital-constructs.characteristics",
	"mgis.capital-constructs.constructive-elements",
	"mgis.nc.services",
	"mgis.reports.report",
	"mgis.lands.maps",
	"mgis.geo.spatial.data"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("constructs", {
				url: "/capital-constructs/constructs/",
				templateUrl: "app2/capital-constructs/construct/construct-list.htm"
			});
	})
	.factory("CapitalConstructsConstructCRUDService", function ($rootScope,
																CapitalConstructsConstructService,
																CapitalConstructsConstructTypeService,
																NcOKTMOService,
																MGISCommonsModalForm,
																AddressModule,
																ConstructsConstructConstants) {
		function editItem0(item, updateHandler) {
			var modalScope = $rootScope.$new();
			modalScope.item = item;
			modalScope.CONSTRUCT_CADASTRAL_NUMBER = ConstructsConstructConstants.CONSTRUCT_CADASTRAL_NUMBER;
			modalScope.CONSTRUCT_CADASTRAL_NUMBER_MASK = ConstructsConstructConstants.CONSTRUCT_CADASTRAL_NUMBER_MASK;
			// AddressMunicipalEntities
			modalScope.availableMunicipalEntities = new Array();
			modalScope.refreshAvailableMunicipalEntities = function (name) {
				NcOKTMOService.get("", 0, 15, null, name).then(function (data) {
					modalScope.availableMunicipalEntities = data.list;
				});
			}
			CapitalConstructsConstructTypeService.get().then(function (constructTypePager) {
				modalScope.availableTypes = constructTypePager.list;
				MGISCommonsModalForm.edit("app2/capital-constructs/construct/construct-form.htm", modalScope, function (scope, modalInstance) {
					CapitalConstructsConstructService.save(scope.item).then(function () {
						if (updateHandler) {
							updateHandler();
						}
						modalInstance.close();
					});
				}, {windowClass: "mgis-capital-construct-modal-form"});
			});
		}


		function addItem(updateHandler) {
			editItem0({id: 0}, updateHandler);
		}

		function editItem(id, updateHandler) {
			CapitalConstructsConstructService.get(id).then(function (data) {
				editItem0(data, updateHandler);
			});
		}

		function editAddressItem(id, updateGrid) {
			AddressModule.edit(id, updateGrid);
		}

		function removeItem(id, updateHandler) {
			MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
				CapitalConstructsConstructService.remove(id).then(function () {
					$modalInstance.close();
					if (updateHandler) {
						updateHandler();
					}
				});
			});

		}

		function reloadItemInList(id, list) {
			CapitalConstructsConstructService.get(id).then(function (data) {
				for (var i in list) {
					var item = list[i];
					if (item.id == data.id) {
						list[i] = data;
					}
				}
			});
		}

		return {
			addItem: addItem,
			editItem: editItem,
			removeItem: removeItem,
			reloadItemInList: reloadItemInList,
			editAddressItem: editAddressItem
		}
	})
	.controller("CapitalConstructsConstructListController", function ($scope,
																	  $state,
																	  $rootScope,
																	  $filter,
																	  CapitalConstructsConstructService,
																	  CapitalConstructEconomicCharacteristicsCRUDService,
																	  CapitalConstructTechnicalCharacteristicsCRUDService,
																	  CapitalConstructsConstructTypeService,
																	  MGISCommonsModalForm,
																	  CapitalConstructsConstructiveElementCRUDService,
																	  NcOKTMOService,
																	  CommonsPagerManager,
																	  ConstructsConstructSelectorService,
																	  CapitalConstructsConstructCRUDService) {
		$scope.currentPage = 1;
		$scope.itemsPerPage = CommonsPagerManager.pageSize();
		$scope.pagerMaxSize = CommonsPagerManager.maxSize();
		$scope.cadastralNumber = "";
		$scope.constructName = "";
		$scope.searchText = "";
		$scope.selectedIds = {};

		function updateGrid() {
			var ids = ConstructsConstructSelectorService.ids();
			CapitalConstructsConstructService.get("", CommonsPagerManager.offset($scope.currentPage), $scope.itemsPerPage,
				$scope.cadastralNumber,
				$scope.constructName,
				"",
				ids
			).then(function (data) {
				$scope.constructsPager = data;
				$scope.selectedIds = {};
				for (var i in data.list) {
					var construction = data.list[i];
					if (ids.indexOf(construction.id) > -1) {
						$scope.selectedIds[construction.id] = {checked: true, cadastralNumber: construction.cadastralNumber};
					}
				}
			});
		}

		$scope.addItem = function () {
			CapitalConstructsConstructCRUDService.addItem(updateGrid);
		}

		$scope.editItem = function (id) {
			CapitalConstructsConstructCRUDService.editItem(id, updateGrid);
		}

		$scope.editAddressItem = function (id) {
			CapitalConstructsConstructCRUDService.editAddressItem(id, updateGrid);
		}

		$scope.deleteItem = function (id) {
			CapitalConstructsConstructCRUDService.removeItem(id, updateGrid);
		}

		$scope.find = function () {
			updateGrid();
		}

		$scope.pageChanged = function () {
			updateGrid();
		}

		$scope.deleteSelectedItems = function () {
			MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
				var ids = ConstructsConstructSelectorService.ids();
				CapitalConstructsConstructService.removeSelected(ids).then(function (data) {
					ConstructsConstructSelectorService.removeByIds(data.ids);
					updateGrid();
					modalInstance.close();
				});
			});
		}

		updateGrid();

		$scope.displayOnTheMap = function () {
			//$state.go("^.maps");
			$state.go("lands.maps"); // временная заглушка
		}

		function selectConstruct(item) {
			ConstructsConstructSelectorService.add({id: item.id, cadastralnumber: item.cadastralNumber});
		}

		$scope.checkConstructSelected = function (checked, item) {

			if (checked) {
				selectConstruct(item);
				$scope.selectedIds[item.id] = {checked: true, cadastralNumber: item.cadastralNumber};
			} else {
				ConstructsConstructSelectorService.remove(item);
				delete $scope.selectedIds[item.id];
			}
			var ids = ConstructsConstructSelectorService.ids();
			for (var id in ids) {
				$scope.selectedIds[ids[id]] = {checked: true, cadastralNumber: item.cadastralNumber};
			}
			//updateGrid();
		}
		$scope.selectAll = function () {
			for (var i in $scope.constructsPager.list) {
				var construct = $scope.constructsPager.list[i];
				selectConstruct(construct);
				$scope.selectedIds[construct.id] = {checked: true, cadastralNumber: construct.cadastralNumber};
			}
		}
		$scope.deselectAll = function () {
			ConstructsConstructSelectorService.removeByIds(Object.keys($scope.selectedIds));
			$scope.selectedIds = {};
		}
		$scope.isNotEmpty = function (obj) {
			return Object.keys(obj).length > 0;
		}

	})

	.controller("CapitalConstructsConstructController", function ($scope,
																  CapitalConstructEconomicCharacteristicsCRUDService,
																  CapitalConstructTechnicalCharacteristicsCRUDService,
																  CapitalConstructsConstructiveElementCRUDService) {

		// Economic Characteristics
		$scope.addEconomicCharacteristic = function (item) {
			CapitalConstructEconomicCharacteristicsCRUDService.add(item);
		}
		$scope.editEconomicCharacteristic = function (char) {
			CapitalConstructEconomicCharacteristicsCRUDService.edit(char);
		}
		$scope.removeEconomicCharacteristic = function (item, char) {
			CapitalConstructEconomicCharacteristicsCRUDService.remove(item, char);
		}

		// Technical Characteristics
		$scope.addTechnicalCharacteristic = function (item) {
			CapitalConstructTechnicalCharacteristicsCRUDService.add(item);
		}
		$scope.editTechnicalCharacteristic = function (char) {
			CapitalConstructTechnicalCharacteristicsCRUDService.edit(char);
		}
		$scope.removeTechnicalCharacteristic = function (item, char) {
			CapitalConstructTechnicalCharacteristicsCRUDService.remove(item, char);
		}

		// Constructive Elements
		$scope.addConstructiveElement = function (item) {
			CapitalConstructsConstructiveElementCRUDService.add(item);
		}
		$scope.editConstructiveElement = function (element) {
			CapitalConstructsConstructiveElementCRUDService.edit(element);
		}
		$scope.removeConstructiveElement = function (item, element) {
			CapitalConstructsConstructiveElementCRUDService.remove(item, element);
		}
	})
;
