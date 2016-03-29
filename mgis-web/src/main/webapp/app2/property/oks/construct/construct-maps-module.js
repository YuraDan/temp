angular.module("mgis.capital-constructs.maps", ["ui.router", "ui.bootstrap", "ui.select", //
	"mgis.capital-constructs.construct",
	"mgis.capital-constructs.construct.service",
	"mgis.geo.geo-server.service",
	"mgis.error.service"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("oks.maps", {
				url: "/maps/",
				templateUrl: "app2/property/oks/construct/construct-maps.htm"
			});
	})
//
	.controller("CapitalConstructsMapsController", function ($scope, $state, $compile,
															 $rootScope,
															 $templateRequest,
															 CapitalConstructsConstructService,
															 CapitalConstructsConstructCRUDService,
															 ConstructsConstructSelectorService,
															 GEOGeoServerService,
															 MGISErrorService) {

		$scope.displayAsAList = function () {
			$state.go("^.constructs");
		}

		$scope.find = function () {
			CapitalConstructsConstructService.get("", $scope.first, $scope.max, $scope.cadastralNumber, $scope.constructName, "")
				.then(function (data) {
				$scope.list = data.list;
				$scope.first = data.first;
				$scope.max = data.max;
			});
		}

		GEOGeoServerService.get("", 0, 0, "local-wfs").then(function (data) {
			if (data.totalNumberOfItems == 1) {
				var geoJsonUrl = data.list[0].url;
				var map = L.map('constructs-map');
				var osmLayer = L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
					attribution: '&copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors',
					maxZoom: 18,
					zoomControl: true
				}).addTo(map);

				map.attributionControl.setPrefix(''); // Don't show the 'Powered by Leaflet' text. Attribution overload
				L.control.mousePosition().addTo(map);

				var landsSelectorControl = new L.Control.MGIS2ConstructsSelector();

				var addLandToSelectorControlHandler = function (construct) {
					landsSelectorControl.addConstruct(construct);
					reloadConstructs(function () {
						map.fitBounds(landsLayer.getBounds());
					});
				}

				var removeLandFromSelectorControlHandler = function (construct) {
					landsSelectorControl.removeConstruct(construct);
				}

				function unselectLandHandler(event, construct) {
					ConstructsConstructSelectorService.remove(construct);
					reloadConstructs(function () {
						map.fitBounds(landsLayer.getBounds());
					});
				}


				function unsubscribeLandSelectorControl() {
					ConstructsConstructSelectorService.unsubscribeFromSelectLand(addLandToSelectorControlHandler);
					ConstructsConstructSelectorService.unsubscribeFromUnselectLand(removeLandFromSelectorControlHandler);
					landsSelectorControl.unsubscribeFromRemoveConstruct(unselectLandHandler);
				}

				ConstructsConstructSelectorService.subscribeToSelectLand(addLandToSelectorControlHandler);
				ConstructsConstructSelectorService.subscribeToUnselectLand(removeLandFromSelectorControlHandler);
				landsSelectorControl.subscribeToRemoveConstruct(unselectLandHandler);
				landsSelectorControl.subscribeToRemove(function () {
					unsubscribeLandSelectorControl();
				});

				var landsLayer = new L.GeoJSON();

				var reloadConstructs = function (loadComplete) {
					var defaultParameters = {
						service: 'WFS',
						version: '1.0.0',
						request: 'getFeature',
						typeName: 'mgis2:occ',
						maxFeatures: 3000,
						outputFormat: 'application/json'
					}


					var customParams = {}
					var ids = ConstructsConstructSelectorService.ids();
					var idsExist = ids && ids.length;
					if (idsExist) {
						var idsString = "";
						for (var i in ids) {
							var id = ids[i];
							if (id) {
								if (idsString) {
									idsString += ",";
								}
								idsString += id;
							}
						}
						customParams.cql_filter = "id in (" + idsString + ")";
					} else {
						customParams.bbox = map.getBounds().toBBoxString();
					}
					var parameters = L.Util.extend(defaultParameters, customParams);

					$.ajax({
						url: geoJsonUrl + L.Util.getParamString(parameters),
						datatype: 'json',
						jsonCallback: 'getJson',
						success: function (data) {
							landsLayer.clearLayers();
							landsLayer.addData(data);
							landsLayer.eachLayer(function (layer) {
								var popupScope = $rootScope.$new();
								popupScope.land = {
									id: layer.feature.properties.id,
									cadastralnumber: layer.feature.properties.cadastralnumber,
									staterealestatecadastreastaging: layer.feature.properties.staterealestatecadastreastaging
								}
								popupScope.editItem = function () {
									CapitalConstructsConstructCRUDService.editItem(popupScope.land.id, reloadConstructs);
								}
								popupScope.addToSelected = function () {
									ConstructsConstructSelectorService.add(popupScope.land);
								}
								popupScope.removeItem = function () {
									CapitalConstructsConstructCRUDService.deleteItem(popupScope.land.id, reloadConstructs);
								}
								$templateRequest("app2/lands/land/land-maps-popup.htm").then(function (content) {
									var linkFunction = $compile(angular.element(content));
									layer.bindPopup(linkFunction(popupScope)[0]);
								});
							});
							map.addLayer(landsLayer);
							if (loadComplete) {
								loadComplete();
							}
						}
					});
				};
				map.on("moveend", function () {
					reloadConstructs();
				});
				map.on("load", function () {
					reloadConstructs(function () {
						map.fitBounds(landsLayer.getBounds());
					});
				});
				map.on("unload", function () {
					unsubscribeLandSelectorControl();
				});
				map.setView(new L.LatLng(0, 0), 1);

				var baseLayers = {
					"Base Layers": {
						"OpenStreetMap": osmLayer
					}
				}
				var groupedOverlays = {
					"Lands": {
						"Restaurants": landsLayer
					}
				};

				var options = {exclusiveGroups: ["Landmarks"]};

				L.control.groupedLayers(baseLayers, groupedOverlays, options).addTo(map);
				map.addControl(landsSelectorControl);
				landsSelectorControl.reloadConstructs(ConstructsConstructSelectorService.list());
			} else {
				MGISErrorService.handleError({
					status: "GIS_SERVER_CONFIGURATION_REQUIRED",
					statusText: "GIS Server <local-wfs> should be configured."
				});
			}
		});
	})
;
