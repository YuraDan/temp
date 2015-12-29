/**
 * Created by asd on 31.07.15.
 */
angular.module("mgis.commons.executive_person.service", ["ngResource",
	"mgis.error.service"
])
	.factory("CommonLegalPersonService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/oks/legal-persons/:id.json');
		return {
			get: function (id, first, max, name) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max,
					name: name
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (data) {

			},
			remove: function (id) {

			}
		}
	})
;
