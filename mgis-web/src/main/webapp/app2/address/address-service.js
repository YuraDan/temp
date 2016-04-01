angular.module("mgis.address.service", ["ngResource",
	"mgis.error.service"
])
	.factory("AddressService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/addresses/:id.json');
		return {
			get: function (id, first, max, name, oktmo) {
				var deferred = $q.defer();
				res.get({
					id: id,
					first: first,
					max: max,
					name: name,
					oktmo: oktmo
				}, {}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				var address = {
					id: item.id,
					okato: item.okato ? {id: item.okato.id} : null,
					oktmo: item.oktmo ? {id: item.oktmo.id} : null,
					postalCode: item.postalCode,
					subject: item.subject ? {id: item.subject.id} : null,
					region: item.region ? {id: item.region.id} : null,
					locality: item.locality ? {id: item.locality.id} : null,
					street: item.street ? {id: item.street.id} : null,
					home: item.home,
					housing: item.housing,
					building: item.building,
					apartment: item.apartment,
					other: item.other
				};
				res.save({id: item.id}, address, function (data) {
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
				});
				return deferred.promise;
			}
		}
	})
	.factory("AddressElementSearchService", function ($resource, $q, MGISErrorService) {
		var res = $resource("rest/addresses/elements/:type/:id.json");
		return {
			get: function (id, first, max, filter) {
				var deferred = $q.defer();
				var params = angular.extend({id: id, first: first, max: max}, filter);
				res.get(params, {}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
;
