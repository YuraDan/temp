package ru.sovzond.mgis2.web.capital_constructs;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.address.AddressBean;
import ru.sovzond.mgis2.capital_construct.*;
import ru.sovzond.mgis2.capital_constructs.CapitalConstruction;
import ru.sovzond.mgis2.capital_constructs.characteristics.ConstructionCharacteristics;
import ru.sovzond.mgis2.capital_constructs.characteristics.economical.EconomicCharacteristic;
import ru.sovzond.mgis2.capital_constructs.characteristics.technical.TechnicalCharacteristic;
import ru.sovzond.mgis2.capital_constructs.constructive_elements.ConstructiveElement;
import ru.sovzond.mgis2.capital_constructs.rights.ConstructionRight;
import ru.sovzond.mgis2.capital_constructs.rights.ConstructionRights;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.geo.SpatialDataBean;
import ru.sovzond.mgis2.geo.SpatialGroup;
import ru.sovzond.mgis2.indicators.PriceIndicatorBean;
import ru.sovzond.mgis2.indicators.TechnicalIndicatorBean;
import ru.sovzond.mgis2.isogd.business.DocumentBean;
import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.lands.Land;
import ru.sovzond.mgis2.lands.LandBean;
import ru.sovzond.mgis2.lands.LandIncludedObjectsBean;
import ru.sovzond.mgis2.lands.includes.LandIncludedObjects;
import ru.sovzond.mgis2.national_classifiers.*;
import ru.sovzond.mgis2.persons.PersonBean;
import ru.sovzond.mgis2.web.lands.ResultIds;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 05.11.15.
 *
 *
 */
@RestController
@RequestMapping("/oks/constructs")
@Scope("session")
public class CapitalConstructRESTService {

	@Autowired
	private CapitalConstructBean capitalConstructBean;

	@Autowired
	private ConstructTypeBean constructTypeBean;

	@Autowired
	private OKTMOBean oktmoBean;

	@Autowired
	private AddressBean addressBean;

	@Autowired
	private ConstructionRightsBean constructionRightsBean;

	@Autowired
	private ConstructionRightBean constructionRightBean;

	@Autowired
	private DocumentBean documentBean;

	@Autowired
	private OKFSBean okfsBean;

	@Autowired
	private OKOFBean okofBean;

	@Autowired
	private LandRightKindBean landRightKindBean;

	@Autowired
	private PersonBean personBean;

	@Autowired
	private EconomicCharacteristicBean economicCharacteristicBean;

	@Autowired
	private TechnicalCharacteristicBean technicalCharacteristicBean;

	@Autowired
	private PriceIndicatorBean priceIndicatorBean;

	@Autowired
	private TechnicalIndicatorBean technicalIndicatorBean;

	@Autowired
	private ConstructiveElementBean constructiveElementBean;

	@Autowired
	private ConstructiveElementTypeBean constructiveElementTypeBean;

	@Autowired
	private OKEIBean okeiBean;

	@Autowired
	private SpatialDataBean spatialDataBean;

	@Autowired
	private LandBean landBean;

	@Autowired
	private LandIncludedObjectsBean landIncludedObjectsBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<CapitalConstruction> list(@RequestParam(value = "orderBy", defaultValue = "id DESC") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max,
													   @RequestParam(value = "cadastralNumber", defaultValue = "") String cadastralNumber,
													   @RequestParam(value = "name", defaultValue = "") String name
	) {
		return capitalConstructBean.list(cadastralNumber, name, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public CapitalConstruction save(@PathVariable Long id, @RequestBody CapitalConstruction capitalConstruct) {
		CapitalConstruction capitalConstruct2;
		if (id == 0) {
			capitalConstruct2 = new CapitalConstruction();
		} else {
			capitalConstruct2 = capitalConstructBean.load(id);
		}
		BeanUtils.copyProperties(capitalConstruct, capitalConstruct2,
				"id",
				"constructType",
				"municipalEntity",
				"address",
				"rights",
				"characteristics",
				"constructiveElements",
				"landIncludedObjects",
				"spatialData"
		);
		capitalConstruct2.setType(capitalConstruct.getType() != null ? constructTypeBean.load(capitalConstruct.getType().getId()) : null);
		capitalConstruct2.setMunicipalEntity(capitalConstruct.getMunicipalEntity() != null ? oktmoBean.load(capitalConstruct.getMunicipalEntity().getId()) : null);
		capitalConstruct2.setAddress(capitalConstruct.getAddress() != null ? addressBean.load(capitalConstruct.getAddress().getId()) : null);

		// Rights
		ConstructionRights rights = capitalConstruct.getRights();
		ConstructionRights rights2;
		if (rights == null || rights.getId() == null || rights.getId() == 0) {
			rights2 = new ConstructionRights();
		} else {
			rights2 = constructionRightsBean.load(rights.getId());
		}
		if (rights != null) {
			if(syncConstructionRights(rights2.getRights(), rights.getRights())) {
				constructionRightsBean.save(rights2);
				capitalConstruct2.setRights(rights2);
			}
		} else {
			if(rights2.getId() != null && rights2.getId() != 0) {
				constructionRightsBean.remove(rights2);
			}
		}

		// Characteristics
		ConstructionCharacteristics characteristics = capitalConstruct.getCharacteristics();
		if (characteristics != null) {
			ConstructionCharacteristics characteristics2 = capitalConstruct2.getCharacteristics();
			if (characteristics2 == null || characteristics2.getId() == null || characteristics2.getId() == 0) {
				characteristics2 = new ConstructionCharacteristics();
				capitalConstruct2.setCharacteristics(characteristics2);
//			} else {
//				characteristics2 = constructCharacteristicsBean.load(characteristics.getId());
			}

			// Economic Characteristics
			if (characteristics.getEconomicCharacteristics() != null && characteristics.getEconomicCharacteristics().size() > 0) {
				syncEconomicCharacteristics(characteristics2.getEconomicCharacteristics(), characteristics.getEconomicCharacteristics());
			} else {
				characteristics2.getEconomicCharacteristics().forEach(economicCharacteristicBean::remove);
				characteristics2.getEconomicCharacteristics().clear();
			}
//			constructCharacteristicsBean.save(characteristics2);
			// Technical Characteristics
			if (characteristics.getTechnicalCharacteristics() != null && characteristics.getTechnicalCharacteristics().size() > 0) {
				syncTechnicalCharacteristics(characteristics2.getTechnicalCharacteristics(), characteristics.getTechnicalCharacteristics());
			} else {
				characteristics2.getTechnicalCharacteristics().forEach(technicalCharacteristicBean::remove);
				characteristics2.getTechnicalCharacteristics().clear();
			}
//			constructCharacteristicsBean.save(characteristics2);
		}
		// Constructive Elements
		syncConstructiveElements(capitalConstruct2.getConstructiveElements(), capitalConstruct.getConstructiveElements());

		// Included Objects
		LandIncludedObjects landIncludedObjects = capitalConstruct.getLandIncludedObjects();
		if (landIncludedObjects != null) {
			LandIncludedObjects landIncludedObjects2 = capitalConstruct2.getLandIncludedObjects();
			if (landIncludedObjects2 == null) {
				landIncludedObjects2 = new LandIncludedObjects();
				capitalConstruct2.setLandIncludedObjects(landIncludedObjects2);
				landIncludedObjectsBean.save(landIncludedObjects2);
			}
			landIncludedObjects2.setLandDealDocument(landIncludedObjects.getLandDealDocument() != null ? documentBean.load(landIncludedObjects.getLandDealDocument().getId()) : null);
			landIncludedObjects2.setInventoryDealDocument(landIncludedObjects.getInventoryDealDocument() != null ? documentBean.load(landIncludedObjects.getInventoryDealDocument().getId()) : null);
			// Urban planning documents
			landIncludedObjects2.getUrbanPlanningDocuments().clear();
			if (landIncludedObjects.getUrbanPlanningDocuments().size() > 0) {
				landIncludedObjects2.getUrbanPlanningDocuments().addAll(documentBean.load(landIncludedObjects.getUrbanPlanningDocuments().stream().map(Document::getId).collect(Collectors.toList())));
			}
			// Included lands
			landIncludedObjects2.getIncludedLands().clear();
			if (landIncludedObjects.getIncludedLands().size() > 0) {
				landIncludedObjects2.getIncludedLands().addAll(landBean.load(landIncludedObjects.getIncludedLands().stream().map(Land::getId).collect(Collectors.toList())));
			}
			// Included constructs
			landIncludedObjects2.getIncludedCapitalConstructions().clear();
			if (landIncludedObjects.getIncludedCapitalConstructions().size() > 0) {
				landIncludedObjects2.getIncludedCapitalConstructions().addAll(capitalConstructBean.load(landIncludedObjects.getIncludedCapitalConstructions().stream().map(CapitalConstruction::getId).collect(Collectors.toList())));
			}
			capitalConstructBean.save(capitalConstruct2);
		} else {
			if (capitalConstruct2.getLandIncludedObjects() == null) {
				landIncludedObjects = new LandIncludedObjects();
				capitalConstruct2.setLandIncludedObjects(landIncludedObjects);
				landIncludedObjectsBean.save(landIncludedObjects);
			}
			capitalConstructBean.save(capitalConstruct2);
		}

		// Spatial Data
		SpatialGroup spatialGroup = capitalConstruct.getSpatialData();
		if (spatialGroup != null) {
			SpatialGroup spatialGroup2 = capitalConstruct2.getSpatialData();
			if (spatialGroup2 == null) {
				spatialGroup2 = new SpatialGroup();
			}
			spatialGroup2 = spatialDataBean.save(spatialGroup, spatialGroup2);
			capitalConstruct2.setSpatialData(spatialGroup2);
			capitalConstruct2.setGeometry(spatialDataBean.buildGeometry(spatialGroup2));
		}

		capitalConstructBean.save(capitalConstruct2);
		return capitalConstruct2.clone();
	}

	private void syncEconomicCharacteristics(List<EconomicCharacteristic> persistentList, List<EconomicCharacteristic> newList) {
		Map<Long, EconomicCharacteristic> persistentMap = new HashMap<>();
		for (EconomicCharacteristic characteristic : persistentList) {
			persistentMap.put(characteristic.getId(), characteristic);
		}
		Set<Long> newIds = new HashSet<>();
		for (EconomicCharacteristic characteristic : newList) {
			EconomicCharacteristic persistent;
			if (characteristic.getId() == null || characteristic.getId() == 0) {
				persistent = new EconomicCharacteristic();
				persistentList.add(persistent);
			} else {
				persistent = persistentMap.get(characteristic.getId());
				newIds.add(persistent.getId());
			}
			BeanUtils.copyProperties(characteristic, persistent, "id", "priceIndicator", "okof");
			persistent.setPriceIndicator(characteristic.getPriceIndicator() != null ? priceIndicatorBean.load(characteristic.getPriceIndicator().getId()) : null);
			persistent.setOkof(characteristic.getOkof() != null ? okofBean.load(characteristic.getOkof().getId()) : null);
			economicCharacteristicBean.save(persistent);
		}
		List<EconomicCharacteristic> toBeRemoved = persistentMap.entrySet().stream().filter(entry -> !newIds.contains(entry.getKey())).map(Map.Entry::getValue).collect(Collectors.toList());
		for (EconomicCharacteristic entity : toBeRemoved) {
			economicCharacteristicBean.remove(entity);
			persistentList.remove(entity);
		}
	}

	private void syncTechnicalCharacteristics(List<TechnicalCharacteristic> persistentList, List<TechnicalCharacteristic> newList) {
		Map<Long, TechnicalCharacteristic> persistentMap = new HashMap<>();
		for (TechnicalCharacteristic characteristic : persistentList) {
			persistentMap.put(characteristic.getId(), characteristic);
		}
		Set<Long> newIds = new HashSet<>();
		for (TechnicalCharacteristic characteristic : newList) {
			TechnicalCharacteristic persistent;
			if (characteristic.getId() == null || characteristic.getId() == 0) {
				persistent = new TechnicalCharacteristic();
				persistentList.add(persistent);
			} else {
				persistent = persistentMap.get(characteristic.getId());
				newIds.add(persistent.getId());
			}
			BeanUtils.copyProperties(characteristic, persistent, "id", "constructType", "technicalIndicator", "unitOfMeasure");
			persistent.setConstructType(characteristic.getConstructType() != null ? constructTypeBean.load(characteristic.getConstructType().getId()) : null);
			persistent.setTechnicalIndicator(characteristic.getTechnicalIndicator() != null ? technicalIndicatorBean.load(characteristic.getTechnicalIndicator().getId()) : null);
			persistent.setUnitOfMeasure(characteristic.getUnitOfMeasure() != null ? okeiBean.load(characteristic.getUnitOfMeasure().getId()) : null);
			technicalCharacteristicBean.save(persistent);
		}
		List<TechnicalCharacteristic> toBeRemoved = persistentMap.entrySet().stream().filter(entry -> !newIds.contains(entry.getKey())).map(Map.Entry::getValue).collect(Collectors.toList());
		for (TechnicalCharacteristic entity : toBeRemoved) {
			technicalCharacteristicBean.remove(entity);
			persistentList.remove(entity);
		}
	}

	private void syncConstructiveElements(List<ConstructiveElement> persistentList, List<ConstructiveElement> newList) {
		if (newList == null || newList.size() == 0) {
			persistentList.clear();
		} else {
			Map<Long, ConstructiveElement> persistentMap = new HashMap<>();
			for (ConstructiveElement element : persistentList) {
				persistentMap.put(element.getId(), element);
			}
			Set<Long> newIds = new HashSet<>();
			for (ConstructiveElement element : newList) {
				ConstructiveElement persistent;
				if (element.getId() == null || element.getId() == 0) {
					persistent = new ConstructiveElement();
					persistentList.add(persistent);
				} else {
					persistent = persistentMap.get(element.getId());
					newIds.add(persistent.getId());
				}
				BeanUtils.copyProperties(element, persistent, "id", "type");
				persistent.setType(element.getType() != null ? constructiveElementTypeBean.load(element.getType().getId()) : null);
				constructiveElementBean.save(persistent);
			}
			List<ConstructiveElement> toBeRemoved = persistentMap.entrySet().stream().filter(entry -> !newIds.contains(entry.getKey())).map(Map.Entry::getValue).collect(Collectors.toList());
			for (ConstructiveElement entity : toBeRemoved) {
				constructiveElementBean.remove(entity);
				persistentList.remove(entity);
			}
		}
	}

	private boolean syncConstructionRights(List<ConstructionRight> persistentList, List<ConstructionRight> newList) {
		int oldPersistentListSize = persistentList.size();
		Map<Long, ConstructionRight> persistentMap = new HashMap<>();
		for (ConstructionRight right : persistentList) {
			persistentMap.put(right.getId(), right);
		}
		Set<Long> newIds = new HashSet<>();
		for (ConstructionRight right : newList) {
			ConstructionRight persistent;
			if (right.getId() == null || right.getId() == 0) {
				persistent = new ConstructionRight();
				persistentList.add(persistent);
			} else {
				persistent = persistentMap.get(right.getId());
				newIds.add(persistent.getId());
			}
			BeanUtils.copyProperties(right, persistent,
					"id",
					"documentsCertifyingRights",
					"registrationDocuments",
					"otherDocuments",
					"ownershipForm",
					"rightKind",
					"rightOwner"
			);
			if (right.getDocumentsCertifyingRights() == null || right.getDocumentsCertifyingRights().size() == 0) {
				persistent.getDocumentsCertifyingRights().clear();
			} else {
				persistent.setDocumentsCertifyingRights(documentBean.load(right.getDocumentsCertifyingRights().stream().map(Document::getId).collect(Collectors.toList())));
			}
			if (right.getRegistrationDocuments() == null || right.getRegistrationDocuments().size() == 0) {
				persistent.getRegistrationDocuments().clear();
			} else {
				persistent.setRegistrationDocuments(documentBean.load(right.getRegistrationDocuments().stream().map(Document::getId).collect(Collectors.toList())));
			}
			if (right.getOtherDocuments() == null || right.getOtherDocuments().size() == 0) {
				persistent.getOtherDocuments().clear();
			} else {
				persistent.setOtherDocuments(documentBean.load(right.getOtherDocuments().stream().map(Document::getId).collect(Collectors.toList())));
			}
			persistent.setOwnershipForm(right.getOwnershipForm() != null ? okfsBean.load(right.getOwnershipForm().getId()) : null);
			persistent.setRightKind(right.getRightKind() != null ? landRightKindBean.load(right.getRightKind().getId()) : null);
			persistent.setRightOwner(right.getRightOwner() != null ? personBean.load(right.getRightOwner().getId()) : null);
			constructionRightBean.save(persistent);
		}
		List<ConstructionRight> toBeRemoved = persistentMap.entrySet().stream().filter(entry -> !newIds.contains(entry.getKey())).map(Map.Entry::getValue).collect(Collectors.toList());
		for (ConstructionRight entity : toBeRemoved) {
			constructionRightBean.remove(entity);
			persistentList.remove(entity);
		}
		return !toBeRemoved.isEmpty() || oldPersistentListSize != persistentList.size();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public CapitalConstruction read(@PathVariable Long id) {
		return capitalConstructBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		capitalConstructBean.remove(capitalConstructBean.load(id));
	}

	@RequestMapping(value = "/remove-selected", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public ResultIds delete(@RequestBody Long[] ids) {
		List<Long> list = Arrays.asList(ids);
		capitalConstructBean.load(list).forEach(capitalConstructBean::remove);
		ResultIds resultIds = new ResultIds();
		resultIds.setIds(list);
		return resultIds;
	}

	@RequestMapping(value = "/{id}/spatial-attribute", method = RequestMethod.POST)
	@Transactional
	public boolean saveGeospatialAttribute(@PathVariable("id") Long id, @RequestBody(required = true) String wktString) {
		return capitalConstructBean.saveGeospatialAttribute(id, wktString);
	}

	@RequestMapping(value = "/parent-lands/{id}", method = RequestMethod.GET)
	@Transactional
	public List<Land> getParentLands(@PathVariable("id") Long id) {
		List<LandIncludedObjects> includedObjects = landIncludedObjectsBean.getIncludedObjectsByCapitalConstruct(id);
		if(includedObjects.size() == 0) return null;
		return landBean.getByIncludedObjects(includedObjects).stream().map(Land::clone).collect(Collectors.toList());
	}

	@RequestMapping(value = "/parent-oks/{id}", method = RequestMethod.GET)
	@Transactional
	public List<CapitalConstruction> getParentCapitalConstructs(@PathVariable("id") Long id) {
		List<LandIncludedObjects> includedObjects = landIncludedObjectsBean.getIncludedObjectsByCapitalConstruct(id);
		if(includedObjects.size() == 0) return null;
		return capitalConstructBean.getByIncludedObjects(includedObjects).stream().map(CapitalConstruction::clone).collect(Collectors.toList());
	}

}
