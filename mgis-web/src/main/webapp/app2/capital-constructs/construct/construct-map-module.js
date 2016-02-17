angular.module("mgis.capital-constructs.construct.map", [
	"mgis.geo.geo-server.service",
	"mgis.error.service"
])
	.factory("CapitalConstructsConstructMapService", function (GEOGeoServerService, MGISErrorService) {
		var container = {
			map: undefined,
			mapElement: undefined,
			landsLayer: undefined,
			drawCreatedEvent: undefined,
			localWfsUrl: undefined
		}

		var reloadLand0 = function (constructId, loadComplete) {
			var map = container.map;
			var landsLayer = container.landsLayer;
			var geoJsonUrl = container.localWfsUrl;
			var defaultParameters = {
				service: 'WFS',
				version: '1.0.0',
				request: 'getFeature',
				typeName: 'mgis2:occ',
				maxFeatures: 3000,
				outputFormat: 'application/json'
			}
			var customParams = {
				cql_filter: "id in (" + constructId + ")"
			}
			var parameters = L.Util.extend(defaultParameters, customParams);
			$.ajax({
				url: geoJsonUrl + L.Util.getParamString(parameters),
				datatype: 'json',
				jsonCallback: 'getJson',
				success: function (data) {
					landsLayer.clearLayers();
					landsLayer.addData(data);
					map.addLayer(landsLayer);
					map.setView(new L.LatLng(0, 0), 1);
					if (loadComplete) {
						loadComplete();
					}
				}
			});
		}

		var reloadLand = function () {
			reloadLand0(container.constructId, function () {
				container.map.fitBounds(container.landsLayer.getBounds());
			});
		}

		function createMap() {
			var map = container.map;
			var osmLayer = L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
				attribution: '&copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors',
				maxZoom: 18,
				zoomControl: false
			}).addTo(map);
			map.attributionControl.setPrefix('');
			container.landsLayer = new L.GeoJSON();
			map.on("load", function () {
				reloadLand();
			});
			var drawnItems = new L.FeatureGroup();
			map.addLayer(drawnItems);
			var drawControl = new L.Control.Draw({
				edit: {
					featureGroup: drawnItems
				}, draw: {
					polyline: false,
					rectangle: false,
					circle: false,
					marker: false
				}
			});
			map.addControl(drawControl);
			map.on("draw:created", function (e) {
				if (e.layerType == "polygon") {
					if (container.drawCreatedEvent) {
						var geoJSONValue = JSON.stringify(e.layer.toGeoJSON());
						var wkt = Terraformer.WKT.convert(JSON.parse(geoJSONValue).geometry);
						container.drawCreatedEvent(container.landId, wkt);
					}
				}
			});
		}

		return {
			checkMap: function (mapContainer, landId, drawCreatedEvent) {
				container.constructId = landId;
				if (container.map == undefined) {
					GEOGeoServerService.get("", 0, 0, "local-wfs").then(function (data) {
						if (data.totalNumberOfItems == 1) {
							container.localWfsUrl = data.list[0].url;
							var mapElement = document.createElement("div");
							var map = L.map(mapElement);
							container.map = map;
							container.mapElement = mapElement;
							mapContainer.appendChild(container.mapElement);
							createMap();
							map.setView(new L.LatLng(0, 0), 1);
							container.drawCreatedEvent = drawCreatedEvent;
							L.control.mousePosition().addTo(map);
						} else {
							MGISErrorService.handleError({
								status: "GIS_SERVER_CONFIGURATION_REQUIRED",
								statusText: "GIS Server <local-wfs> should be configured."
							});
						}
					});
				} else {
					var map = container.map;
					mapContainer.appendChild(container.mapElement);
					map.setView(new L.LatLng(0, 0), 1);
					reloadLand();
					container.drawCreatedEvent = drawCreatedEvent;
				}
			}
		}
	})
	.directive("constructMap", function ($rootScope) {
		return {
			restrict: "E",
			scope: {
				constructId: "@"
			},
			templateUrl: "app2/capital-constructs/construct/construct-map-control.htm",
			controller: function ($scope, $element, CapitalConstructsConstructMapService, CapitalConstructsConstructGeoService) {
				var mapContainer = $element[0].getElementsByClassName("construct-map-container")[0];

				function createMap(constructId) {
					CapitalConstructsConstructMapService.checkMap(mapContainer, constructId, function (constructId2, wktString) {
						if (constructId) {
							CapitalConstructsConstructGeoService.save(constructId2, wktString).then(function () {
								createMap(constructId2);
							});
						}
					});
				}

				createMap($scope.constructId);
			}
		}
	})
;
