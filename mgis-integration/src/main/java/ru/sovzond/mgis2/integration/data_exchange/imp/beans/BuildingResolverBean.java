package ru.sovzond.mgis2.integration.data_exchange.imp.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.property.services.oks.CapitalConstructBean;
import ru.sovzond.mgis2.property.services.oks.ConstructTypeBean;
import ru.sovzond.mgis2.property.services.oks.EconomicCharacteristicBean;
import ru.sovzond.mgis2.property.services.oks.TechnicalCharacteristicBean;
import ru.sovzond.mgis2.property.model.oks.CapitalConstruction;
import ru.sovzond.mgis2.property.model.oks.ConstructionType;
import ru.sovzond.mgis2.property.model.oks.characteristics.ConstructionCharacteristics;
import ru.sovzond.mgis2.property.model.oks.characteristics.economical.EconomicCharacteristic;
import ru.sovzond.mgis2.property.model.oks.characteristics.technical.TechnicalCharacteristic;
import ru.sovzond.mgis2.property.model.oks.rights.ConstructionRights;
import ru.sovzond.mgis2.geo.CoordinateSystem;
import ru.sovzond.mgis2.geo.SpatialDataBean;
import ru.sovzond.mgis2.geo.SpatialGroup;
import ru.sovzond.mgis2.geo.SpatialGroupBean;
import ru.sovzond.mgis2.indicators.PriceIndicator;
import ru.sovzond.mgis2.indicators.PriceIndicatorBean;
import ru.sovzond.mgis2.indicators.TechnicalIndicator;
import ru.sovzond.mgis2.indicators.TechnicalIndicatorBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.BuildingDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.ConstructDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.CoordinateSystemDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.IncompleteDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.ConstructSourceDecorator;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.ConstructionTargetDecorator;
import ru.sovzond.mgis2.national_classifiers.OKEIBean;
import ru.sovzond.mgis2.national_classifiers.ObjectsPurposeBean;
import ru.sovzond.mgis2.national_classifiers.OkatoToOktmoBean;
import ru.sovzond.mgis2.registers.national_classifiers.OKEI;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 25/12/15.
 *
 */
@Service
public class BuildingResolverBean {

	private static String KADASTRAL_COST = "Кадастровая стоимость";
	private static String EXTENT = "Протяженность";
	private static String DEPTH = "Глубина";
	private static String CAPACITY = "Объем";
	private static String HEIGHT = "Высота";
	private static String AREA_OF_BUILDING = "Площадь застройки";
	private static String DEPTH_OF_OCC = "Глубина залегания";
	@SuppressWarnings("FieldCanBeLocal")
	private static Integer METR = 6;
	@SuppressWarnings("FieldCanBeLocal")
	private static Integer METER3 = 113;
	@SuppressWarnings("FieldCanBeLocal")
	private static Integer METER2 = 55;

	@Autowired
	private CapitalConstructBean capitalConstructBean;

	@Autowired
	private AddressResolverBean addressResolverBean;

	@Autowired
	private ConstructTypeBean constructTypeBean;

	@Autowired
	private SpatialGroupBean spatialGroupBean;

	@Autowired
	private PriceIndicatorBean priceIndicatorBean;

	@Autowired
	private TechnicalIndicatorBean technicalIndicatorBean;

	@Autowired
	private SpatialDataBean spatialDataBean;

	@Autowired
	private ObjectsPurposeBean objectsPurposeBean;

	@Autowired
	private SpatialDataResolverBean spatialDataResolverBean;

	@Autowired
	private OkatoToOktmoBean okatoToOktmoBean;

	@Autowired
	private OKEIBean okeiBean;

	@Autowired
	private EconomicCharacteristicBean economicCharacteristicBean;

	@Autowired
	private TechnicalCharacteristicBean technicalCharacteristicBean;

	public CapitalConstruction resolve(ConstructDTO obj) {
		String cadastralNumber = obj.getCadastralNumber();
		CapitalConstruction capitalConstruction = capitalConstructBean.findByCadastralNumber(cadastralNumber);
		if (capitalConstruction == null) {
			capitalConstruction = new CapitalConstruction();
			updateConstruct(capitalConstruction, obj);
			capitalConstructBean.save(capitalConstruction);
		} else {
			updateConstruct(capitalConstruction, obj);
			capitalConstructBean.save(capitalConstruction);
		}
		return capitalConstruction;
	}

	private void updateConstruct(CapitalConstruction capitalConstruction, ConstructDTO constructDTO) {
		boolean buildingType = false;
		boolean incompleteType = false;
		boolean constructType = false;

		String cadastralNumber = constructDTO.getCadastralNumber();
		capitalConstruction.setCadastralNumber(cadastralNumber);
		ConstructionType type;
		if (constructDTO instanceof BuildingDTO) {
			type = resolveType("BUILDING");
			buildingType = true;
		} else if (constructDTO instanceof IncompleteDTO) {
			type = resolveType("INCOMPLETE_CONSTRUCT");
			incompleteType = true;
		} else {
			type = resolveType("CONSTRUCT");
			constructType = true;
		}
		capitalConstruction.setType(type);
		capitalConstruction.setName(type.getName() + " " + cadastralNumber);
		capitalConstruction.setAddress(addressResolverBean.resolveAddress(constructDTO.getAddress()));
		OKTMO oktmo = okatoToOktmoBean.findByOkato(constructDTO.getAddress().getOkato());
		capitalConstruction.setMunicipalEntity(oktmo);

		if (buildingType) {
			resolveBuilding(capitalConstruction, constructDTO);
			setEconomicsCharacteristics(capitalConstruction, constructDTO);
		}
		if (incompleteType || constructType) {
			resolveConstruction(capitalConstruction, constructDTO);
		}

		ConstructionRights rights = capitalConstruction.getRights();
		if (rights == null) {
			rights = new ConstructionRights();
			capitalConstruction.setRights(rights);
		}


		fillSpatialData(constructDTO, capitalConstruction);
	}

	private void setEconomicsCharacteristics(CapitalConstruction capitalConstruction, ConstructDTO constructDTO) {
		ConstructionCharacteristics constructionCharacteristics = capitalConstruction.getCharacteristics();
		if (constructionCharacteristics == null) {
			constructionCharacteristics = new ConstructionCharacteristics();
			setEconomicsToCharacteristic(resolveEconomics(constructDTO), constructionCharacteristics, capitalConstruction);
		} else {
			if (!setEconomicsWithPriceIndicator(constructionCharacteristics.getEconomicCharacteristics(), constructDTO)) {
				List<EconomicCharacteristic> economicCharacteristics = constructionCharacteristics.getEconomicCharacteristics();
				if (economicCharacteristics.size() > 0) {
					economicCharacteristics.add(resolveEconomics(constructDTO));
					constructionCharacteristics.setEconomicCharacteristics(economicCharacteristics);
					capitalConstruction.setCharacteristics(constructionCharacteristics);
				} else {
					setEconomicsToCharacteristic(resolveEconomics(constructDTO), constructionCharacteristics, capitalConstruction);
				}
			}

		}
	}

	private void setTechnicalCharacteristics(CapitalConstruction capitalConstruction, ConstructDTO constructDTO) {
		ConstructionCharacteristics constructionCharacteristics = capitalConstruction.getCharacteristics();
		if (constructionCharacteristics == null) {
			constructionCharacteristics = new ConstructionCharacteristics();

			setTechnicsToCharacteristic(resolveTechnic(constructDTO), constructionCharacteristics, capitalConstruction);
		} else {
			if (!setTechnicsWithTechnicIndicator(constructionCharacteristics.getTechnicalCharacteristics(), constructDTO)) {
				List<TechnicalCharacteristic> technicalCharacteristics = constructionCharacteristics.getTechnicalCharacteristics();
				if (technicalCharacteristics.size() > 0) {
					technicalCharacteristics.add(resolveTechnic(constructDTO));
					constructionCharacteristics.setTechnicalCharacteristics(technicalCharacteristics);
					capitalConstruction.setCharacteristics(constructionCharacteristics);
				} else {
					setTechnicsToCharacteristic(resolveTechnic(constructDTO), constructionCharacteristics, capitalConstruction);
				}
			}

		}
	}

	private void setEconomicsToCharacteristic(EconomicCharacteristic economicCharacteristic, ConstructionCharacteristics constructionCharacteristics, CapitalConstruction capitalConstruction) {
		@SuppressWarnings("ArraysAsListWithZeroOrOneArgument") List<EconomicCharacteristic> economicCharacteristics = Arrays.asList(economicCharacteristic);
		constructionCharacteristics.setEconomicCharacteristics(economicCharacteristics);
		capitalConstruction.setCharacteristics(constructionCharacteristics);
	}

	private void setTechnicsToCharacteristic(TechnicalCharacteristic technicalCharacteristic, ConstructionCharacteristics constructionCharacteristics, CapitalConstruction capitalConstruction) {
		@SuppressWarnings("ArraysAsListWithZeroOrOneArgument") List<TechnicalCharacteristic> technicalCharacteristics = Arrays.asList(technicalCharacteristic);
		constructionCharacteristics.setTechnicalCharacteristics(technicalCharacteristics);
		capitalConstruction.setCharacteristics(constructionCharacteristics);
	}

	private EconomicCharacteristic resolveEconomics(ConstructDTO constructDTO) {
		EconomicCharacteristic economicCharacteristic = new EconomicCharacteristic();
		economicCharacteristic.setValue(constructDTO.getCadastralCostValue());

		PriceIndicator priceIndicator = priceIndicatorBean.findByName(KADASTRAL_COST);
		if (priceIndicator != null) {
			economicCharacteristic.setPriceIndicator(priceIndicator);
		} else {
			priceIndicator = new PriceIndicator();
			priceIndicator.setName(KADASTRAL_COST);
			OKEI okei = okeiBean.findByCode(constructDTO.getCadastralCostUnit());
			priceIndicator.setUnitOfMeasure(okei);
			economicCharacteristic.setPriceIndicator(priceIndicatorBean.save(priceIndicator));
		}
		return economicCharacteristicBean.save(economicCharacteristic);
	}

	private TechnicalCharacteristic resolveTechnic(ConstructDTO constructDTO) {
		TechnicalCharacteristic technicalCharacteristic = new TechnicalCharacteristic();
		technicalCharacteristic.setValue(constructDTO.getKeyParameterValue());

		switch (constructDTO.getKeyParameterType()) {
			case 1:
				setTechnicalIndicator(technicalCharacteristic, METR, EXTENT);
				break;
			case 2:
				setTechnicalIndicator(technicalCharacteristic, METR, DEPTH);
				break;
			case 3:
				setTechnicalIndicator(technicalCharacteristic, METER3, CAPACITY);
				break;
			case 4:
				setTechnicalIndicator(technicalCharacteristic, METR, HEIGHT);
				break;
			case 6:
				setTechnicalIndicator(technicalCharacteristic, METER2, AREA_OF_BUILDING);
				break;
			case 7:
				setTechnicalIndicator(technicalCharacteristic, METR, DEPTH_OF_OCC);
				break;
		}
		return technicalCharacteristicBean.save(technicalCharacteristic);
	}

	private void setTechnicalIndicator(TechnicalCharacteristic technicalCharacteristic, Integer code_Okei, String characteristicType) {
		TechnicalIndicator technicalIndicator;
		technicalIndicator = technicalIndicatorBean.findByName(characteristicType);
		if (technicalIndicator != null) {
			technicalCharacteristic.setTechnicalIndicator(technicalIndicator);
		} else {
			technicalIndicator = new TechnicalIndicator();
			technicalIndicator.setName(characteristicType);
			OKEI okei = okeiBean.findByCode(code_Okei);
			technicalIndicator.setUnitOfMeasure(okei);
			technicalCharacteristic.setTechnicalIndicator(technicalIndicatorBean.save(technicalIndicator));
		}
	}

	private boolean setEconomicsWithPriceIndicator(List<EconomicCharacteristic> economicCharacteristics, ConstructDTO constructDTO) {
		if (economicCharacteristics != null && economicCharacteristics.size() != 0) {
			for (EconomicCharacteristic buffer : economicCharacteristics) {
				if (buffer.getPriceIndicator().getName().equals(KADASTRAL_COST)) {
					buffer.setValue(constructDTO.getCadastralCostValue());
					return true;
				}
			}
			return false;
		}
		return false;
	}

	private boolean setTechnicsWithTechnicIndicator(List<TechnicalCharacteristic> technicalCharacteristics, ConstructDTO constructDTO) {
		if (technicalCharacteristics != null && technicalCharacteristics.size() != 0) {
			switch (constructDTO.getKeyParameterType()) {
				case 1:
					for (TechnicalCharacteristic buffer : technicalCharacteristics) {
						if (buffer.getTechnicalIndicator().getName().equals(EXTENT)) {
							buffer.setValue(constructDTO.getCadastralCostValue());
							return true;
						}
					}
				case 2:
					for (TechnicalCharacteristic buffer : technicalCharacteristics) {
						if (buffer.getTechnicalIndicator().getName().equals(DEPTH)) {
							buffer.setValue(constructDTO.getCadastralCostValue());
							return true;
						}
					}
				case 3:
					for (TechnicalCharacteristic buffer : technicalCharacteristics) {
						if (buffer.getTechnicalIndicator().getName().equals(CAPACITY)) {
							buffer.setValue(constructDTO.getCadastralCostValue());
							return true;
						}
					}
				case 4:
					for (TechnicalCharacteristic buffer : technicalCharacteristics) {
						if (buffer.getTechnicalIndicator().getName().equals(HEIGHT)) {
							buffer.setValue(constructDTO.getCadastralCostValue());
							return true;
						}
					}
				case 6:
					for (TechnicalCharacteristic buffer : technicalCharacteristics) {
						if (buffer.getTechnicalIndicator().getName().equals(AREA_OF_BUILDING)) {
							buffer.setValue(constructDTO.getCadastralCostValue());
							return true;
						}
					}
				case 7:
					for (TechnicalCharacteristic buffer : technicalCharacteristics) {
						if (buffer.getTechnicalIndicator().getName().equals(DEPTH_OF_OCC)) {
							buffer.setValue(constructDTO.getCadastralCostValue());
							return true;
						}
					}

			}
			return false;
		}
		return false;
	}

	private CapitalConstruction resolveBuilding(CapitalConstruction capitalConstruction, ConstructDTO constructDTO) {
		capitalConstruction.setObjectPurpose(objectsPurposeBean.findNameByCode(constructDTO.getAssignationBuilding()));
		capitalConstruction.setOverallArea(constructDTO.getArea());
		return capitalConstruction;
	}

	private CapitalConstruction resolveConstruction(CapitalConstruction capitalConstruction, ConstructDTO constructDTO) {

		if (constructDTO.getKeyParameterType() != null && constructDTO.getKeyParameterType() == 5) {
			capitalConstruction.setOverallArea(constructDTO.getKeyParameterValue());
		} else {
			if (constructDTO.getKeyParameterType() != null) {
				setTechnicalCharacteristics(capitalConstruction, constructDTO);
			}
		}

		return capitalConstruction;
	}


	private void fillSpatialData(ConstructDTO constructDTO, CapitalConstruction capitalConstruction) {
		ConstructSourceDecorator sourceDecorator = new ConstructSourceDecorator();
		sourceDecorator.wrap(constructDTO);

		ConstructionTargetDecorator targetDecorator = new ConstructionTargetDecorator();
		targetDecorator.wrap(capitalConstruction);

		spatialDataResolverBean.fillSpatialData(sourceDecorator, targetDecorator);
	}

	private ConstructionType resolveType(String code) {
		return constructTypeBean.findByCode(code);
	}

	public void updateCoordinateSystem(Long id, CoordinateSystemDTO coordinateSystemDTO) {
		CapitalConstruction construct = capitalConstructBean.load(id);
		SpatialGroup spatialData = construct.getSpatialData();
		if (spatialData != null) {
			CoordinateSystem coordinateSystem = spatialDataResolverBean.resolveCoordinateSystem(coordinateSystemDTO.getName(), null);
			spatialData.setCoordinateSystem(coordinateSystem);
			spatialGroupBean.save(spatialData);
			construct.setGeometry(spatialDataBean.buildGeometry(spatialData));
			capitalConstructBean.save(construct);
		}
	}
}
