package ru.sovzond.mgis2.integration.data_exchange.imp.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.geo.CoordinateSystem;
import ru.sovzond.mgis2.geo.SpatialDataBean;
import ru.sovzond.mgis2.geo.SpatialGroup;
import ru.sovzond.mgis2.geo.SpatialGroupBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.CoordinateSystemDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.LandDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.LandRightDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.LandSourceDecorator;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.LandTargetDecorator;
import ru.sovzond.mgis2.national_classifiers.LandAllowedUsageBean;
import ru.sovzond.mgis2.national_classifiers.LandCategoryBean;
import ru.sovzond.mgis2.national_classifiers.LandRightKindBean;
import ru.sovzond.mgis2.national_classifiers.OKTMOBean;
import ru.sovzond.mgis2.property.model.lands.Land;
import ru.sovzond.mgis2.property.model.lands.LandArea;
import ru.sovzond.mgis2.property.model.lands.TerritorialZone;
import ru.sovzond.mgis2.property.model.lands.characteristics.LandCharacteristics;
import ru.sovzond.mgis2.property.model.rights.PropertyRights;
import ru.sovzond.mgis2.property.model.rights.SubjectRight;
import ru.sovzond.mgis2.property.services.lands.*;
import ru.sovzond.mgis2.property.services.rights.IPropertyRightsService;
import ru.sovzond.mgis2.registers.national_classifiers.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 *
 */
@Service
public class LandResolverBean {

	public static final String CADASTRAL_BLOCK_PATTERN = "(\\d+):(\\d+):(\\d{2})(\\d{2})(\\d+).*";

	@Autowired
	private ILandService landService;

	@Autowired
	private ILandAreaService landAreaService;

	@Autowired
	private LandAllowedUsageBean landAllowedUsageBean;

	@Autowired
	private ILandAreaTypeService landAreaTypeService;

	@Autowired
	private LandRightKindBean landRightKindBean;

	@Autowired
	private LandCategoryBean landCategoryBean;

	@Autowired
	private ITerritorialZoneTypeService territorialZoneTypeService;

	@Autowired
	private OKTMOBean oktmoBean;

	@Autowired
	private ITerritorialZoneService territorialZoneService;

	@Autowired
	private SpatialGroupBean spatialGroupBean;

	@Autowired
	private IPropertyRightsService landRightsService;

	@Autowired
	private AddressResolverBean addressResolverBean;

	@Autowired
	private SpatialDataResolverBean spatialDataResolverBean;

	@Autowired
	private SpatialDataBean spatialDataBean;

	private Pattern cadastralNumberPattern = Pattern.compile(CADASTRAL_BLOCK_PATTERN);

	private LandRightKind resolveLandRightKind(String name, String type) {
		return landRightKindBean.find(type);
	}

	public void updateCoordinateSystem(Long landId, CoordinateSystemDTO coordinateSystemDTO) {
		// TODO:
		Land land = landService.load(landId);
		SpatialGroup spatialData = land.getSpatialData();
		if (spatialData != null) {
			CoordinateSystem coordinateSystem = spatialDataResolverBean.resolveCoordinateSystem(coordinateSystemDTO.getName(), null);
			spatialData.setCoordinateSystem(coordinateSystem);
			spatialGroupBean.save(spatialData);
			land.setGeometry(spatialDataBean.buildGeometry(spatialData));
			landService.save(land);
		}
	}

	public Land resolveLand(LandDTO landDTO) {
		List<Land> lands = landService.find(landDTO.getCadastralNumber());
		Land land;
		switch (lands.size()) {
			case 0:
				land = createLand(landDTO);
				landService.save(land);
				return land;
			case 1:
				land = updateLand(landDTO, lands.get(0));
				landService.save(land);
				return land;
			default:
				throw new UnsupportedOperationException();
		}
	}

	private Land createLand(LandDTO landDTO) {
		Land land = new Land();
		land.setCharacteristics(new LandCharacteristics());
		land.setRights(new PropertyRights());
		landService.save(land);

		updateLand0(landDTO, land);
		return land;
	}

	private void updateLand0(LandDTO landDTO, Land land) {
		land.setCadastralNumber(landDTO.getCadastralNumber());
		land.setStateRealEstateCadastreaStaging(landDTO.getDateCreated());
		land.setAddress(addressResolverBean.resolveAddress(landDTO.getAddress()));
		land.setLandCategory(resolveLandCategory(landDTO.getCategory()));

		if (land.getAddress().getOktmo() != null) {
			land.setAddressOfMunicipalEntity(land.getAddress().getOktmo());
		}

		String locationPlaced = landDTO.getLocationPlaced();
		TerritorialZoneType zoneType;
		if (locationPlaced != null) {
			zoneType = resolveTerritorialZoneType(locationPlaced);
			land.setAllowedUsageByTerritorialZone(resolveTerritorialZone(landDTO.getCadastralNumber(), zoneType));
		} else {
			land.setAllowedUsageByTerritorialZone(null);
		}

		land.setAllowedUsageByDocument(landDTO.getUtilizationByDoc());
		LandAllowedUsage landAllowedUsage = landAllowedUsageBean.findByCode(landDTO.getUtilizationByNc());
		land.setAllowedUsageByDictionary(landAllowedUsage);

		if (landDTO.getCadastralCostValue() != null) {
			land.getCharacteristics().setCadastralCost(landDTO.getCadastralCostValue().floatValue());
		}

		if (landDTO.getArea() != null) {

			List<LandArea> landAreas = land.getLandAreas();
			if (landAreas != null && landAreas.size() != 0) {
				landAreas.get(0).setValue(landDTO.getArea().floatValue());
				landAreas.get(0).setLandAreaType(landAreaTypeService.load(3L));
				land.setLandAreas(landAreas);
			} else {
				LandArea landArea = new LandArea();
				landArea.setValue(landDTO.getArea().floatValue());
				landArea.setLandAreaType(landAreaTypeService.load(3L));
				if (landAreas != null)  landAreas.add(landArea);
				land.setLandAreas(landAreas);
			}

			PropertyRights rights = land.getRights();
			if (rights == null) {
				rights = new PropertyRights();
				landRightsService.save(rights);
				land.setRights(rights);
			}
			for(SubjectRight right: rights.getRights()) {
				if (landDTO.getRights() != null) {
					switch (landDTO.getRights().size()) {
						case 0:
							throw new IllegalArgumentException("No land right found while land.rights node container exists.");
						case 1:
							LandRightDTO landRightDTO = landDTO.getRights().get(0);
							right.setRightKind(resolveLandRightKind(landRightDTO.getName(), landRightDTO.getType()));
							break;
						default:
							throw new IllegalArgumentException("More than one land right found in node land.rights node container exists.");
					}
				}
			}
		}

		fillSpatialData(landDTO, land);
	}

	private void fillSpatialData(LandDTO landDTO, Land land) {
		LandSourceDecorator landSourceDecorator = new LandSourceDecorator();
		landSourceDecorator.wrap(landDTO);

		LandTargetDecorator landTargetDecorator = new LandTargetDecorator();
		landTargetDecorator.wrap(land);

		spatialDataResolverBean.fillSpatialData(landSourceDecorator, landTargetDecorator);
	}

	private TerritorialZone resolveTerritorialZone(String landCadastralNumber, TerritorialZoneType territorialZoneType) {
		Matcher matcher = cadastralNumberPattern.matcher(landCadastralNumber);
		if (matcher.matches()) {
			String cadastralNumber1 = matcher.group(1) + ":" + matcher.group(2) + ":" + matcher.group(3) + matcher.group(4) + matcher.group(5);
			List<TerritorialZone> list = territorialZoneService.findByCadastralNumberAndZoneType(cadastralNumber1, territorialZoneType);
			switch (list.size()) {
				case 1:
					return list.get(0);
				case 0:
					String cadastralNumber2 = matcher.group(1) + ":" + matcher.group(2) + ":" + matcher.group(3) + matcher.group(4);
					list = territorialZoneService.findByCadastralNumberAndZoneType(cadastralNumber2, territorialZoneType);
					switch (list.size()) {
						case 1:
							return list.get(0);
						case 0:
							TerritorialZone zone = new TerritorialZone();
							zone.setAccountNumber(cadastralNumber1);
							zone.setName(cadastralNumber1 + " (" + territorialZoneType.getName() + ")");
							zone.setZoneType(territorialZoneType);
							territorialZoneService.save(zone);
							return zone;
						default:
							throw new IllegalArgumentException("More than one territorial zone found by cadastralNumber: " + cadastralNumber2 + " and territorialZoneType: " + territorialZoneType.getCode() + ".");
					}
				default:
					throw new IllegalArgumentException("More than one territorial zone found by cadastralNumber: " + cadastralNumber1 + " and territorialZoneType: " + territorialZoneType.getCode() + ".");
			}
		}
		throw new IllegalArgumentException("Cadastral number " + landCadastralNumber + " cannot be parsed.");
	}

	private TerritorialZoneType resolveTerritorialZoneType(String territorialZoneType) {
		TerritorialZoneType type = territorialZoneTypeService.findByCode(territorialZoneType);
		if (type == null) {
			List<TerritorialZoneType> list = territorialZoneTypeService.findByNameSubstring("%(" + territorialZoneType + ")%");
			switch (list.size()) {
				case 0:
					list = territorialZoneTypeService.findByNameSubstring(territorialZoneType);
					switch (list.size()) {
						case 0:
							type = new TerritorialZoneType();
							type.setCode(territorialZoneType);
							type.setName(territorialZoneType);
							territorialZoneTypeService.save(type);
							break;
						case 1:
							type = list.get(0);
							break;
						default:
							throw new IllegalArgumentException("More than one TerritorialZoneType found for name '" + territorialZoneType + "'");
					}
					break;
				case 1:
					type = list.get(0);
					break;
				default:
					throw new IllegalArgumentException("More than one TerritorialZoneType found for name '%(" + territorialZoneType + ")%'");
			}
		}
		return type;
	}

	private LandCategory resolveLandCategory(String category) {
		if (category != null) {
			LandCategory landCategory = landCategoryBean.findByCode(category);
			if (landCategory == null) {
				String category2 = category.replaceFirst("^0+", "");
				landCategory = landCategoryBean.findByCode(category2);
			}
			return landCategory;
		}
		return null;
	}

	private OKTMO resolveOKTMO(String code) {
		return oktmoBean.findByCode(code);
	}

	private Land updateLand(LandDTO landDTO, Land land) {
		updateLand0(landDTO, land);
		return land;
	}
}
