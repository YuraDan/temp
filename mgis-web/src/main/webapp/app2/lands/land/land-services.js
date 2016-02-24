angular.module("mgis.lands.services", ["ui.router", 'ngResource',
	"mgis.error.service",
	"mgis.property.service"
])
	.constant("LandsLandConstants", {
		LAND_CADASTRAL_NUMBER: /^\d{2}:\d{2}:\d{7}:\d{1}/,
		LAND_CADASTRAL_NUMBER_MASK: "99:99:9999999:9?9?9?9?9?9?9?9?9?9"
	})
	.factory("LandsLandService", function ($resource, $q, MGISErrorService, MGISPropertyRightsService) {
		var res = $resource('rest/lands/land/:id.json');
		var resRemoveSelected = $resource('rest/lands/land/remove-selected.json');

		function buildLandAreas(landAreas) {
			var landAreas2 = [];
			for (var i in landAreas) {
				var area = landAreas[i];
				landAreas2.push({id: area.id, value: area.value, landAreaType: {id: area.landAreaType.id}});
			}
			return landAreas2;
		}


		function buildInspectionResultDocuments(control) {
			if (control.inspectionResultDocuments && control.inspectionResultDocuments.length) {
				return new Array().concat(control.inspectionResultDocuments);
			}
			return null;
		}

		function copyArray(arr) {
			var result = new Array();
			for (var i in arr) {
				var land = arr[i];
				result.push({id: land.id});
			}
			return result;
		}

		function buildIncludedLands(includedObjects) {
			if (includedObjects && includedObjects.includedLands) {
				return copyArray(includedObjects.includedLands);
			}
			return null;
		}

		function buildIncludedCapitalConstructions(includedObjects) {
			if (includedObjects && includedObjects.includedCapitalConstructions) {
				return copyArray(includedObjects.includedCapitalConstructions);
			}
			return null;
		}

		return {
			get: function (id, first, max, cadastralNumber, ids) {
				var deferred = $q.defer();
				res.get({
					id: id,
					cadastralNumber: cadastralNumber,
					ids: ids ? ids.join() : new Array(),
					first: first,
					max: max
				}, {}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				var characteristics = item.characteristics;
				var control = item.control ? item.control : {};
				var landAreas = item.landAreas;
				var landAreas2 = buildLandAreas(landAreas);
				var includedObjects = item.includedObjects;
				//var registrationDocuments2 = buildRegistrationDocuments(rights);
				//var documentsCertifyingRights2 = buildDocumentsCertifyingRights(rights);
				var includedObjects2;
				if (includedObjects) {
					includedObjects2 = {
						landDealDocument: includedObjects.landDealDocument ? {id: includedObjects.landDealDocument.id} : null,
						inventoryDealDocument: includedObjects.inventoryDealDocument ? {id: includedObjects.inventoryDealDocument.id} : null,
						includedLands: buildIncludedLands(includedObjects),
						includedCapitalConstructions: buildIncludedCapitalConstructions(includedObjects),
					};
				}
				var land = {
					id: item.id,
					cadastralNumber: item.cadastralNumber,
					stateRealEstateCadastreaStaging: item.stateRealEstateCadastreaStaging,
					landAreas: landAreas2,
					landCategory: item.landCategory ? {id: item.landCategory.id} : null,
					allowedUsageByDictionary: item.allowedUsageByDictionary ? {id: item.allowedUsageByDictionary.id} : null,
					allowedUsageByDocument: item.allowedUsageByDocument,
					allowedUsageByTerritorialZone: item.allowedUsageByTerritorialZone ? {id: item.allowedUsageByTerritorialZone.id} : null,
					addressOfMunicipalEntity: item.addressOfMunicipalEntity ? {id: item.addressOfMunicipalEntity.id} : null,
					addressOfPlacementType: item.addressOfPlacementType ? {id: item.addressOfPlacementType.id} : null,
					addressPlacement: item.addressPlacement,
					address: item.address ? {id: item.address.id} : null,
					rights: MGISPropertyRightsService.buildRights(item.rights),
					characteristics: {
						cadastralCost: characteristics.cadastralCost,
						specificIndexOfCadastralCost: characteristics.specificIndexOfCadastralCost,
						marketCost: characteristics.marketCost,
						mortgageValue: characteristics.mortgageValue,
						cadastralCostImplementationDate: characteristics.cadastralCostImplementationDate,
						marketCostImplementationDate: characteristics.marketCostImplementationDate,
						standardCost: characteristics.standardCost,
						typeOfEngineeringSupportArea: characteristics.typeOfEngineeringSupportArea ? {id: characteristics.typeOfEngineeringSupportArea.id} : null,
						distanceToTheConnectionPoint: characteristics.distanceToTheConnectionPoint,
						distanceToTheConnectionPointLength: characteristics.distanceToTheConnectionPointLength,
						distanceToTheObjectsOfTransportInfrastructure: characteristics.distanceToTheObjectsOfTransportInfrastructure,
						nearestMunicipalEntity: characteristics.nearestMunicipalEntity ? {id: characteristics.nearestMunicipalEntity.id} : null,
						distanceToTheNearestMunicipalEntity: characteristics.distanceToTheNearestMunicipalEntity,
						countrySubject: characteristics.countrySubject ? {id: characteristics.countrySubject.id} : null,
						distanceToTheCountrySubjectCenter: characteristics.distanceToTheCountrySubjectCenter
					},
					control: {
						executivePerson: control.executivePerson ? {id: control.executivePerson.id} : null,
						inspectedPerson: control.inspectedPerson ? {id: control.inspectedPerson.id} : null,
						inspectionDate: control.inspectionDate,
						inspectionKind: control.inspectionKind ? {id: control.inspectionKind.id} : null,
						inspectionReason: control.inspectionReason ? {id: control.inspectionReason.id} : null,
						inspectionReasonDescription: control.inspectionReasonDescription,
						inspectionResultAvailabilityOfViolations: control.inspectionResultAvailabilityOfViolations ? {id: control.inspectionResultAvailabilityOfViolations.id} : null,
						inspectionResultDescription: control.inspectionResultDescription,
						inspectionResultDocuments: control ? buildInspectionResultDocuments(control) : null,
						inspectionSubject: control.inspectionSubject ? {id: control.inspectionSubject.id} : null,
						inspectionType: control.inspectionType ? {id: control.inspectionType.id} : null,
						penaltyAmount: control.penaltyAmount,
						timelineForViolations: control.timelineForViolations
					},
					includedObjects: includedObjects2,
					spatialData: item.spatialData
				};
				res.save({id: item.id}, land, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			remove: function (id) {
				var deferred = $q.defer();
				res.remove({
					id: id
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			removeSelected: function (ids) {
				var deferred = $q.defer();
				resRemoveSelected.save({}, ids, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}

	})
	.factory("LandsInspectionKindService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/lands/inspection_kinds/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	.factory("LandsInspectionTypeService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/lands/inspection_types/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	.factory("LandsInspectionReasonService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/lands/inspection_reasons/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	.factory("LandsInspectionSubjectService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/lands/inspection_subjects/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	.factory("LandsAvailabilityOfViolationsService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/lands/availability_of_violations/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	.factory("LandsLandAreaTypeService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/lands/area_types/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	// Selected Lands Service
	.factory("LandsLandSelectorService", function () {
		var _lands = new Array();
		var _addListeners = new Array();
		var _removeListeners = new Array();
		return {
			add: function (land) {
				var found = false;
				for (var i in _lands) {
					var land2 = _lands[i];
					if ((land.id && land.id == land2.id) ||
						(land.cadastralnumber && land.cadastralnumber == land2.cadastralnumber)) {
						var land2 = _lands[i];
						found |= true;
					}
				}
				if (!found) {
					_lands.push(land);
					for (var i in _addListeners) {
						_addListeners[i](land);
					}
					return true;
				}
				return false;
			},
			remove: function (land) {
				for (var i in _lands) {
					var land2 = _lands[i];
					if ((land.id && land.id == land2.id) ||
						(land.cadastralnumber && land.cadastralnumber == land2.cadastralnumber)) {
						_lands.splice(i, 1);
						for (var i in _removeListeners) {
							_removeListeners[i](land);
						}
						return true;
					}
				}
				return false;
			},
			removeByIds: function (ids) {
				for (var i in ids) {
					var id = ids[i];
					this.remove({id: id});
				}
			},
			list: function () {
				var result = new Array();
				for (var i in _lands) {
					result.push(_lands[i]);
				}
				return result;
			},
			ids: function () {
				var result = new Array();
				for (var i in _lands) {
					result.push(_lands[i].id);
				}
				return result;
			},
			subscribeToSelectLand: function (listener) {
				_addListeners.push(listener);
			},
			unsubscribeFromSelectLand: function (listener) {
				for (var i in _addListeners) {
					if (_addListeners[i] == listener) {
						_addListeners.splice(i, 1);
					}
				}
			},
			subscribeToUnselectLand: function (listener) {
				_removeListeners.push(listener);
			},
			unsubscribeFromUnselectLand: function (listener) {
				for (var i in _removeListeners) {
					if (_removeListeners[i] == listener) {
						_removeListeners.splice(i, 1);
					}
				}
			}
		}
	})
	.factory("LandsLandGeoService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/lands/land/:id/spatial-attribute.json');
		return {
			save: function (idAttribute, geoAttribute) {
				var deferred = $q.defer();
				res.save({id: idAttribute}, geoAttribute, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	.factory("LandsLandItemService", function ($resource, $q, MGISErrorService) {
		return {
			getParentLands: function (id) {
				var resLands = $resource('rest/lands/land/parent-lands/:id.json');
				var deferred = $q.defer();
				resLands.query({id: id}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})

;
