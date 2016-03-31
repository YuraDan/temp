angular.module("mgis.common.document.selector.list", ["ui.bootstrap", "ui.select", //
	"mgis.commons",
	"mgis.isogd.documents"
])
	.directive("documentSelectorList", function () {
		return {
			restrict: "E",
			scope: {
				selectedDocuments: "="
			},
			templateUrl: "app2/documents/common/document-selector/list/document-selector-list.htm",
			controller: function ($scope) {
				$scope.removeSelectedDocument = function (item) {
					for (var i = 0; i < $scope.selectedDocuments.length; i++) {
						if ($scope.selectedDocuments[i].id == item.id) {
							$scope.selectedDocuments.splice(i, 1);
						}
					}
				};

			}
		}
	});

