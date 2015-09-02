angular.module("mgis.lands.maps", ["ui.router", "ui.bootstrap", "ui.select", "openlayers-directive", //
//	"mgis.commons", //
	"mgis.lands.lands",
	"mgis.lands.services",
//	"mgis.nc.services",
//	"mgis.common.executive_person.service",
//	"mgis.terr-zones.zone.service"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("lands.maps", {
				url: "/maps/",
				templateUrl: "app2/lands/land/land-maps.htm"
			});
	})
//
	.controller("LandsMapsController", function ($scope, $state, LandsLandService, $compile, $rootScope, LandsLandCRUDService, $templateRequest,
												 LandsLandSelectorService) {

		$scope.displayAsAList = function () {
			$state.go("^.lands");
		}

		$scope.find = function () {
			LandsLandService.get("", $scope.first, $scope.max, $scope.cadastralNumber).then(function (data) {
				$scope.list = data.list;
				$scope.first = data.first;
				$scope.max = data.max;
			});
		}


		var map = L.map('lands-map');
		var osmLayer = L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
			attribution: '&copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors',
			maxZoom: 18,
			zoomControl: true
		}).addTo(map);

		map.attributionControl.setPrefix(''); // Don't show the 'Powered by Leaflet' text. Attribution overload

		var landsSelectorControl = new L.Control.MGIS2LandsSelector();

		var addLandToSelectorControlHandler = function (land) {
			landsSelectorControl.addLand(land);
		}

		var removeLandFromSelectorControlHandler = function (land) {
			landsSelectorControl.removeLand(land);
		}

		function unselectLandHandler(event, land) {
			LandsLandSelectorService.remove(land);
		}


		function unsubscribeLandSelectorControl() {
			LandsLandSelectorService.unsubscribeFromSelectLand(addLandToSelectorControlHandler);
			LandsLandSelectorService.unsubscribeFromUnselectLand(removeLandFromSelectorControlHandler);
			landsSelectorControl.unsubscribeFromRemoveLand(unselectLandHandler);
		}

		LandsLandSelectorService.subscribeToSelectLand(addLandToSelectorControlHandler);
		LandsLandSelectorService.subscribeToUnselectLand(removeLandFromSelectorControlHandler);
		landsSelectorControl.subscribeToRemoveLand(unselectLandHandler);
		landsSelectorControl.subscribeToRemove(function () {
			unsubscribeLandSelectorControl();
		});

		var landsLayer = new L.GeoJSON();

		var reloadLands = function (loadComplete) {
			var geoJsonUrl = "proxy?http://localhost:8081/geoserver/mgis2/wfs";
			var defaultParameters = {
				service: 'WFS',
				version: '1.0.0',
				request: 'getFeature',
				typeName: 'mgis2:lands_land',
				maxFeatures: 3000,
				outputFormat: 'application/json'
			}


			var customParams = {
				bbox: map.getBounds().toBBoxString(),
			}
			var parameters = L.Util.extend(defaultParameters, customParams);
			console.log(geoJsonUrl + L.Util.getParamString(parameters));

			$.ajax({
				url: geoJsonUrl + L.Util.getParamString(parameters),
				datatype: 'json',
				jsonCallback: 'getJson',
				success: function (data) {
					landsLayer.clearLayers();
					console.log("data: " + JSON.stringify(data));
					landsLayer.addData(data);
					landsLayer.eachLayer(function (layer) {
						var popupScope = $rootScope.$new();
						popupScope.land = {
							id: layer.feature.properties.id,
							cadastralnumber: layer.feature.properties.cadastralnumber,
							staterealestatecadastreastaging: layer.feature.properties.staterealestatecadastreastaging
						}
						popupScope.editItem = function () {
							LandsLandCRUDService.editItem(popupScope.land.id, reloadLands);
						}
						popupScope.addToSelected = function () {
							LandsLandSelectorService.add(popupScope.land);
						}
						popupScope.removeItem = function () {
							LandsLandCRUDService.deleteItem(popupScope.land.id, reloadLands);
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
			reloadLands();
		});
		map.on("load", function () {
			reloadLands(function () {
				console.log("bounds: " + JSON.stringify(landsLayer.getBounds()));
				map.fitBounds(landsLayer.getBounds());
			});
		});
		map.on("unload", function () {
			unsubscribeLandSelectorControl();
		});
		map.setView(new L.LatLng(0, 0), 1);

		//var baseLayers = {
		//    'Mapnik': osmLayer
		//}
		//var overlayLayers = {
		//    'Clouds': landsLayer
		//}
		//var control = L.control.activeLayers(baseLayers, overlayLayers)
		//control.addTo(map)

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
		landsSelectorControl.reloadLands(LandsLandSelectorService.list());
	})
;
