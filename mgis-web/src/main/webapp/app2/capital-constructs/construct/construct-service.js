angular.module("mgis.capital-constructs.construct.service", ["ngResource",
	"mgis.error.service",
	"mgis.property.service"
])

	.constant("ConstructsConstructConstants", {
		CONSTRUCT_CADASTRAL_NUMBER: /^\d{2}:\d{2}:\d{7}:\d{1}/,
		CONSTRUCT_CADASTRAL_NUMBER_MASK: "99:99:9999999:9?9?9?9?9?9?9?9?9?9"
	})
			.factory("CapitalConstructsConstructService", function ($q, $resource, MGISErrorService, MGISPropertyRightsService) {
		var res = $resource('rest/capital-constructs/constructs/:id.json');

		return {
			get: function (id, first, max, cadastralNumber, name) {
				var deferred = $q.defer();
				res.get({
					id: id,
					first: first,
					max: max,
					cadastralNumber: cadastralNumber,
					name: name
				}, {}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				var p = {}
				angular.copy(item, p);
				p.rights = MGISPropertyRightsService.buildRights(item.rights)
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
			}
		}
	})
	.factory("CapitalConstructsConstructTypeService", function ($q, $resource, MGISErrorService) {
		var res = $resource('rest/capital-constructs/construct-types/:id.json');
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
;
