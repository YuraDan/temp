/**
 * Created by Alexander Arakelyan on 24.06.15.
 */
angular.module("mgis.commons", ["ui.bootstrap"])
	.factory("MGISCommonsModalForm", function ($modal) {
		return {
			confirmRemoval: function (acceptMethod) {
				var modalInstance = $modal.open({
					animation: false,
					templateUrl: "app2/commons/confirm-deletion.htm",
					controller: function ($scope, $modalInstance) {
						$scope.ok = function () {
							acceptMethod($modalInstance);
						};
						$scope.cancel = function () {
							$modalInstance.dismiss('cancel');
						}
					}
				});
			},
			edit: function (templateUrl, modalScope, applyMethod, params) {

				var params2 = {
					animation: false,
					scope: modalScope,
					templateUrl: templateUrl,
					controller: function ($scope, $modalInstance) {
						$scope.ok = function () {
							applyMethod($scope, $modalInstance);
						}
						$scope.cancel = function () {
							$modalInstance.dismiss("cancel");
						}
					}
				}
				if (params) {
					angular.extend(params2, params);
				}
				return $modal.open(params2);
			}
		}
	})
	.controller("MGISDateTimeController", function ($scope) {

		$scope.showWeeks = true;
		$scope.toggleWeeks = function () {
			$scope.showWeeks = !$scope.showWeeks;
		};

		$scope.clear = function () {
			$scope.dt = null;
		};

		// Disable weekend selection
		$scope.disabled = function (date, mode) {
			return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
		};


		$scope.open = function ($event) {
			$event.preventDefault();
			$event.stopPropagation();

			$scope.opened = true;
		};

		$scope.dateOptions = {
			'year-format': "'yy'",
			'starting-day': 1
		};

		$scope.format = "dd.MM.yyyy";
	})
	.controller("MGISUploadFileController", function ($scope, Upload) {
		$scope.init = function (uploadUrl, uploadFields, uploadProgress, uploadComplete) {
			$scope.uploadUrl = uploadUrl;
			$scope.uploadFields = uploadFields;
			$scope.uploadProgress = uploadProgress;
			$scope.uploadComplete = uploadComplete;
		}

		$scope.$watch('files', function () {
			$scope.upload($scope.files);
		});

		$scope.upload = function (files) {
			if (files && files.length) {
				for (var i = 0; i < files.length; i++) {
					var file = files[i];
					Upload.upload({
						url: $scope.uploadUrl,
						headers: {
							'Content-Type': 'multipart/form-data; charset=utf-8'
						},
						file: file
					}).progress(function (event) {
						var progressPercentage = parseInt(100.0 * event.loaded / event.total);
						if ($scope.uploadProgress) {
							$scope.uploadProgress(event);
						}
					}).success(function (data, status, headers, config) {
						if ($scope.uploadComplete) {
							$scope.uploadComplete(data);
						}
					});
				}
			}
		};
	})
;