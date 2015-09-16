angular.module("mgis.isogd.volumes.service", ["ui.router", "ui.bootstrap", "ngResource",
	"mgis.error.service"
]) //
	.factory("ISOGDVolumesService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/isogd/volumes/:id.json');
		return {
			get: function (id, first, max, bookId) {
				var deferred = $q.defer();
				res.get({
					id: id,
					first: first,
					max: max,
					bookId: bookId
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				})
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				res.save({id: item.id}, {
					id: item.id,
					name: item.name,
					book: {
						id: item.book.id
					}
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				})
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
	});
