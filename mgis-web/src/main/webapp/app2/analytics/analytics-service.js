/**
 * Created by donchenko-y on 3/23/16.
 */
angular.module("mgis.analytics.service", ["ngResource",
	"mgis.error.service"
])
	.factory("InputTaxesService", function ($resource, $q, MGISErrorService) {
		var inputTaxes = $resource('rest/inputTaxes/:id.json');
		return {
			get: function (id, first, max, cadastralNumber) {
				var deferred = $q.defer();
				inputTaxes.get({
					id: id,
					first: first,
					max: max,
					cadastralNumber: cadastralNumber
				}, {}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			remove: function (id) {
				var deferred = $q.defer();
				inputTaxes.remove({
					id: id
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	})
;
