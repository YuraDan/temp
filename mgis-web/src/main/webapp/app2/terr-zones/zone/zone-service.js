angular.module("mgis.terr-zones.zone.service", [
	"mgis.error.service"
])
	.factory("TerrZonesZoneService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/terr-zones/zones/:id.json');
		return {
			get: function (id, first, max, name) {
				var deferred = $q.defer();
				res.get({id: id, name: name}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				res.save({id: item.id}, item, function (data) {
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
	}) //
;
