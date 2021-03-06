angular.module("mgis.capital-constructs.construct.service", ["ngResource",
	"mgis.error.service",
	"mgis.property.service"
])

	.constant("ConstructsConstructConstants", {
		CONSTRUCT_CADASTRAL_NUMBER: /^\d{2}:\d{2}:\d{7}:\d{1,10}/,
		CONSTRUCT_CADASTRAL_NUMBER_MASK: "99:99:9999999:9?9?9?9?9?9?9?9?9?9"
	})
	.factory("CapitalConstructsConstructService", function ($q, $resource, MGISErrorService, MGISPropertyRightsService) {
		var res = $resource('rest/oks/constructs/:id.json');
		return {
			get: function (id, first, max, cadastralNumber, name, ids) {
				var deferred = $q.defer();
				res.get({
					id: id,
					first: first,
					max: max,
					cadastralNumber: cadastralNumber,
					name: name,
					ids: ids
				}, {}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				var p = {};
				angular.copy(item, p);
				p.rights = MGISPropertyRightsService.buildRights(item.rights);
				res.save({id: item.id}, p, function (data) {
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
				var resRemoveSelected = $resource('rest/oks/constructs/remove-selected.json');
				var deferred = $q.defer();
				resRemoveSelected.save({}, ids, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			getParentLands: function (id) {
				var resLands = $resource('rest/oks/constructs/parent-lands/:id.json');
				var deferred = $q.defer();
				resLands.query({id: id}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			getParentCapitalConstructs: function (id) {
				var resOks = $resource('rest/oks/constructs/parent-oks/:id.json');
				var deferred = $q.defer();
				resOks.query({id: id}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	.factory("CapitalConstructsConstructTypeService", function ($q, $resource, MGISErrorService) {
		var res = $resource('rest/oks/construct-types/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id, first: first, max: max}, {}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	// Selected Constructs Service
	.factory("ConstructsConstructSelectorService", function () {
		var _constructs = [];
		var _addListeners = [];
		var _removeListeners = [];
		return {
			add: function (construct) {
				var found = false;

				for (var i in _constructs) {
					var construct2 = _constructs[i];
					if ((construct.id && construct.id == construct2.id) ||
						(construct.cadastralnumber && construct.cadastralnumber == construct2.cadastralnumber)) {
						construct2 = _constructs[i];
						found |= true;
					}
				}
				if (!found) {
					_constructs.push(construct);
					for (var i in _addListeners) {
						_addListeners[i](construct);
					}
					return true;
				}
				return false;
			},
			remove: function (construct) {
				for (var i in _constructs) {
					var construct2 = _constructs[i];
					if ((construct.id && construct.id == construct2.id) ||
						(construct.cadastralnumber && construct.cadastralnumber == construct2.cadastralnumber)) {
						_constructs.splice(i, 1);
						for (var i in _removeListeners) {
							_removeListeners[i](construct);
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
				var result = [];
				for (var i in _constructs) {
					result.push(_constructs[i]);
				}
				return result;
			},
			ids: function () {
				var result = [];
				for (var i in _constructs) {
					result.push(_constructs[i].id);
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
	.factory("CapitalConstructsConstructGeoService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/oks/constructs/:id/spatial-attribute.json');
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

;
