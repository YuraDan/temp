angular.module("mgis.isogd.sections.service", ["ui.router", 'ngResource',
	"mgis.error.service"
]) //
	.factory("ISOGDSectionsService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/isogd/sections/:id.json');
		return {
			get: function (id, first, max, name) {
				var deferred = $q.defer();
				res.get({
					id: id,
					first: first,
					max: max,
					name: name
				}, {}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (section) {
				var deferred = $q.defer();
				res.save({id: section.id,}, {
					id: section.id,
					name: section.name,
					documentClass: section.documentClass ? {id: section.documentClass.id} : null
				}, function (data) {
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
	});
