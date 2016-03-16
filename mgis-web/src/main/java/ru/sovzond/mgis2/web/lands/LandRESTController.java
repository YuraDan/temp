package ru.sovzond.mgis2.web.lands;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.address.AddressBean;
import ru.sovzond.mgis2.capital_construct.CapitalConstructBean;
import ru.sovzond.mgis2.capital_constructs.CapitalConstruction;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.geo.SpatialDataBean;
import ru.sovzond.mgis2.geo.SpatialGroup;
import ru.sovzond.mgis2.isogd.business.DocumentBean;
import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.lands.*;
import ru.sovzond.mgis2.lands.characteristics.LandCharacteristics;
import ru.sovzond.mgis2.lands.control.LandControl;
import ru.sovzond.mgis2.lands.includes.LandIncludedObjects;
import ru.sovzond.mgis2.lands.rights.LandRight;
import ru.sovzond.mgis2.lands.rights.LandRights;
import ru.sovzond.mgis2.national_classifiers.*;
import ru.sovzond.mgis2.persons.ExecutivePersonBean;
import ru.sovzond.mgis2.persons.PersonBean;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lands/land")
@Scope("session")
public class LandRESTController implements Serializable {

	private static final long serialVersionUID = -1259104464434967077L;

	@Autowired
	private LandBean landBean;

	@Autowired
	private PersonBean personBean;

	@Autowired
	private LandAllowedUsageBean allowedUsageByDocumentBean;

	@Autowired
	private OKTMOBean oktmoBean;

	@Autowired
	private TerritorialZoneBean allowedUsageByTerritorialZoneBean;

	@Autowired
	private AddressBean addressBean;

	@Autowired
	private LandCategoryBean landCategoryBean;

	@Autowired
	private OKFSBean landOwnershipFormBean;

	@Autowired
	private LandRightKindBean landRightKindBean;

	@Autowired
	private LandRightsBean landRightsBean;

	@Autowired
	private LandRightBean landRightBean;

	@Autowired
	private LandCharacteristicsBean landCharacteristicsBean;

	@Autowired
	private OKATOBean okatoBean;

	@Autowired
	private LandTypeOfEngineeringSupportAreaBean typeOfEngineeringSupportAreaBean;

	@Autowired
	private LandEncumbranceBean landEncumbranceBean;

	@Autowired
	private LandControlBean landControlBean;

	@Autowired
	private LandControlInspectionKindBean landControlInspectionKindBean;

	@Autowired
	private LandControlInspectionResonBean landControlInspectionReasonBean;

	@Autowired
	private LandControlInspectionResultAvailabilityOfViolationsBean landControlInspectionResultAvailabilityOfViolationsBean;

	@Autowired
	private LandControlInspectionSubjectBean landControlInspectionSubjectBean;

	@Autowired
	private LandControlInspectionTypeBean landControlInspectionTypeBean;

	@Autowired
	private ExecutivePersonBean executivePersonBean;

	@Autowired
	private LandAreaBean landAreaBean;

	@Autowired
	private LandAreaTypeBean landAreaTypeBean;

	@Autowired
	private DocumentBean documentBean;

	@Autowired
	private SpatialDataBean spatialDataBean;

	@Autowired
	private LandIncludedObjectsBean landIncludedObjectsBean;

	@Autowired
	private CapitalConstructBean capitalConstructBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<Land> list(@RequestParam(value = "cadastralNumber", defaultValue = "") String cadastralNumber, @RequestParam(value = "orderBy", defaultValue = "") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "") String ids) {
		Long[] idsAsLong = null;
		if (ids != null && ids.length() > 0) {
			String[] idsAsString = ids.split(",");
			idsAsLong = new Long[idsAsString.length];
			for (int i = 0; i < idsAsString.length; i++) {
				String idAsString = idsAsString[i];
				if (idAsString != null && !"".equals(idAsString)) {
					idsAsLong[i] = Long.parseLong(idAsString);
				}
			}
		}
		return landBean.list(cadastralNumber, idsAsLong, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Land save(@PathVariable("id") Long id, @RequestBody Land land) {
		Land land2;
		if (id == 0) {
			land2 = new Land();
			landBean.save(land2);
		} else {
			land2 = landBean.load(id);
		}
		land2.setCadastralNumber(land.getCadastralNumber());
		land2.setStateRealEstateCadastreaStaging(land.getStateRealEstateCadastreaStaging());
		if (land.getLandCategory() != null) {
			land2.setLandCategory(landCategoryBean.load(land.getLandCategory().getId()));
		}
		if (land.getEncumbrance() != null) {
			land2.setEncumbrance(landEncumbranceBean.load(land.getEncumbrance().getId()));
		}
		//		land2.setLandAreas();
		if (land.getAllowedUsageByDictionary() != null) {
			land2.setAllowedUsageByDictionary(allowedUsageByDocumentBean.load(land.getAllowedUsageByDictionary().getId()));
		}
		land2.setAllowedUsageByDocument(land.getAllowedUsageByDocument());
		if (land.getAllowedUsageByTerritorialZone() != null) {
			land2.setAllowedUsageByTerritorialZone(allowedUsageByTerritorialZoneBean.load(land.getAllowedUsageByTerritorialZone().getId()));
		}
		if (land.getAddressOfMunicipalEntity() != null) {
			land2.setAddressOfMunicipalEntity(oktmoBean.load(land.getAddressOfMunicipalEntity().getId()));
		}
		land2.setAddressPlacement(land.getAddressPlacement());
		if (land.getAddress() != null) {
			land2.setAddress(addressBean.load(land.getAddress().getId()));
		}
		land2.setAddress(land.getAddress());

		// Land area
		land2.getLandAreas().clear();
		for (LandArea landArea : land.getLandAreas()) {
			LandArea landArea2;
			if (landArea.getId() == 0) {
				landArea2 = new LandArea();
			} else {
				landArea2 = landAreaBean.load(landArea.getId());
				landAreaBean.save(landArea2);
			}
			landArea2.setValue(landArea.getValue());
			landArea2.setLandAreaType(landAreaTypeBean.load(landArea.getLandAreaType().getId()));
			landAreaBean.save(landArea2);
			land2.getLandAreas().add(landArea2);
		}

		// Rights
		LandRights rights = land.getRights();
		LandRights rights2;
		if (rights == null || rights.getId() == null || rights.getId() == 0) {
			rights2 = new LandRights();
		} else {
			rights2 = landRightsBean.load(rights.getId());
		}
		if (rights != null) {
			if(syncLandRights(rights2.getRights(), rights.getRights())) {
				landRightsBean.save(rights2);
				land2.setRights(rights2);
			}
		} else {
			if(rights2.getId() != null && rights2.getId() != 0) {
				landRightsBean.remove(rights2);
			}
		}

		LandCharacteristics chars = land.getCharacteristics();
		if (chars != null) {
			LandCharacteristics chars2 = land2.getCharacteristics();
			if (chars2 == null) {
				chars2 = new LandCharacteristics();
				land2.setCharacteristics(chars2);
				landCharacteristicsBean.save(chars2);
			}
			chars2.setCadastralCost(chars.getCadastralCost());
			chars2.setSpecificIndexOfCadastralCost(chars.getSpecificIndexOfCadastralCost());
			chars2.setMarketCost(chars.getMarketCost());
			chars2.setMortgageValue(chars.getMortgageValue());
			chars2.setCadastralCostImplementationDate(chars.getCadastralCostImplementationDate());
			chars2.setMarketCostImplementationDate(chars.getMarketCostImplementationDate());
			chars2.setStandardCost(chars.getStandardCost());
			chars2.setTypeOfEngineeringSupportArea(chars.getTypeOfEngineeringSupportArea() != null ? typeOfEngineeringSupportAreaBean.load(chars.getTypeOfEngineeringSupportArea().getId()) : null);
			chars2.setDistanceToTheConnectionPoint(chars.getDistanceToTheConnectionPoint());
			chars2.setDistanceToTheConnectionPointLength(chars.getDistanceToTheConnectionPointLength());
			chars2.setDistanceToTheObjectsOfTransportInfrastructure(chars.getDistanceToTheObjectsOfTransportInfrastructure());
			chars2.setNearestMunicipalEntity(chars.getNearestMunicipalEntity() != null ? oktmoBean.load(chars.getNearestMunicipalEntity().getId()) : null);
			chars2.setDistanceToTheNearestMunicipalEntity(chars.getDistanceToTheNearestMunicipalEntity());
			chars2.setCountrySubject(chars.getCountrySubject() != null ? okatoBean.load(chars.getCountrySubject().getId()) : null);
			chars2.setDistanceToTheCountrySubjectCenter(chars.getDistanceToTheCountrySubjectCenter());
		}

		LandControl control = land.getControl();
		if (control != null) {
			LandControl control2 = land2.getControl();
			if (control2 == null) {
				control2 = new LandControl();
				land2.setControl(control2);
				landControlBean.save(control2);
			}
			control2.setExecutivePerson(control.getExecutivePerson() != null ? executivePersonBean.load(control.getExecutivePerson().getId()) : null);
			control2.setInspectedPerson(control.getInspectedPerson() != null ? personBean.load(control.getInspectedPerson().getId()) : null);
			control2.setInspectionDate(control.getInspectionDate());
			control2.setInspectionKind(control.getInspectionKind() != null ? landControlInspectionKindBean.load(control.getInspectionKind().getId()) : null);
			control2.setInspectionReason(control.getInspectionReason() != null ? landControlInspectionReasonBean.load(control.getInspectionReason().getId()) : null);
			control2.setInspectionReasonDescription(control.getInspectionReasonDescription());
			control2.setInspectionResultAvailabilityOfViolations(control.getInspectionResultAvailabilityOfViolations() != null ? landControlInspectionResultAvailabilityOfViolationsBean.load(control.getInspectionResultAvailabilityOfViolations().getId()) : null);
			control2.setInspectionResultDescription(control.getInspectionResultDescription());
			// Inspection result Documents
			control2.getInspectionResultDocuments().clear();
			if (control.getInspectionResultDocuments() != null && control.getInspectionResultDocuments().size() > 0) {
				List<Document> load = documentBean.load(control.getInspectionResultDocuments().stream().map(Document::getId).collect(Collectors.toList()));
				control2.getInspectionResultDocuments().addAll(load);
			}

			control2.setInspectionSubject(control.getInspectionSubject() != null ? landControlInspectionSubjectBean.load(control.getInspectionSubject().getId()) : null);
			control2.setInspectionType(control.getInspectionType() != null ? landControlInspectionTypeBean.load(control.getInspectionType().getId()) : null);
			control2.setPenaltyAmount(control.getPenaltyAmount());
			control2.setTimelineForViolations(control.getTimelineForViolations());
		}
		LandIncludedObjects includedObjects = land.getIncludedObjects();
		if (includedObjects != null) {
			LandIncludedObjects includedObjects2 = land2.getIncludedObjects();
			if (includedObjects2 == null) {
				includedObjects2 = new LandIncludedObjects();
				land2.setIncludedObjects(includedObjects2);
				landIncludedObjectsBean.save(includedObjects2);
			}
			includedObjects2.setInventoryDealDocument(includedObjects.getInventoryDealDocument() != null ? documentBean.load(includedObjects.getInventoryDealDocument().getId()) : null);
			includedObjects2.setLandDealDocument(includedObjects.getLandDealDocument() != null ? documentBean.load(includedObjects.getLandDealDocument().getId()) : null);

			List<Land> includedLands = includedObjects.getIncludedLands();
			includedObjects2.getIncludedLands().clear();
			if (includedLands != null && includedLands.size() > 0) {
				includedObjects2.getIncludedLands().addAll(landBean.load(includedLands.stream().map(Land::getId).collect(Collectors.toList())));
			}

			List<CapitalConstruction> includedConstructs = includedObjects.getIncludedCapitalConstructions();
			includedObjects2.getIncludedCapitalConstructions().clear();
			if (includedConstructs != null && includedConstructs.size() > 0) {
				includedObjects2.getIncludedCapitalConstructions().addAll(capitalConstructBean.load(includedConstructs.stream().map(CapitalConstruction::getId).collect(Collectors.toList())));
			}
		}

		SpatialGroup spatialGroup = land.getSpatialData();
		if (spatialGroup != null) {
			SpatialGroup spatialGroup2 = land2.getSpatialData();
			if (spatialGroup2 == null) {
				spatialGroup2 = new SpatialGroup();
			}
			spatialGroup2 = spatialDataBean.save(spatialGroup, spatialGroup2);
			land2.setSpatialData(spatialGroup2);
			land2.setGeometry(spatialDataBean.buildGeometry(spatialGroup2));
		}

		landBean.save(land2);
		return land2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public Land read(@PathVariable("id") Long id) {
		return landBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		landBean.remove(landBean.load(id));
	}

	@RequestMapping(value = "/remove-selected", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public ResultIds delete(@RequestBody Long[] ids) {
		List<Long> list = Arrays.asList(ids);
		landBean.load(list).forEach(landBean::remove);
		ResultIds resultIds = new ResultIds();
		resultIds.setIds(list);
		return resultIds;
	}

	@RequestMapping(value = "/{id}/spatial-attribute", method = RequestMethod.POST)
	@Transactional
	public boolean saveGeospatialAttribute(@PathVariable("id") Long id, @RequestBody(required = true) String wktString) {
		return landBean.saveGeospatialAttribute(id, wktString);
	}

	@RequestMapping(value = "/parent-lands/{id}", method = RequestMethod.GET)
	@Transactional
	public List<Land> getParentLands(@PathVariable("id") Long id) {
		List<LandIncludedObjects> includedObjects = landIncludedObjectsBean.getIncludedObjectsByLand(id);
		if(includedObjects.size() == 0) return null;
		return landBean.getByIncludedObjects(includedObjects).stream().map(Land::clone).collect(Collectors.toList());
	}

	@RequestMapping(value = "/parent-oks/{id}", method = RequestMethod.GET)
	@Transactional
	public List<CapitalConstruction> getParentCapitalConstructs(@PathVariable("id") Long id) {
		List<LandIncludedObjects> includedObjects = landIncludedObjectsBean.getIncludedObjectsByLand(id);
		if(includedObjects.size() == 0) return null;
		return capitalConstructBean.getByIncludedObjects(includedObjects).stream().map(CapitalConstruction::clone).collect(Collectors.toList());
	}

	private boolean syncLandRights(List<LandRight> persistentList, List<LandRight> newList) {
		int oldPersistentListSize = persistentList.size();
		Map<Long, LandRight> persistentMap = new HashMap<>();
		for (LandRight right : persistentList) {
			persistentMap.put(right.getId(), right);
		}
		Set<Long> newIds = new HashSet<>();
		for (LandRight right : newList) {
			LandRight persistent;
			if (right.getId() == null || right.getId() == 0) {
				persistent = new LandRight();
				persistentList.add(persistent);
			} else {
				persistent = persistentMap.get(right.getId());
				newIds.add(persistent.getId());
			}
			BeanUtils.copyProperties(right, persistent,
					"id",
					"ownershipForm",
					"rightKind",
					"rightOwner",
					"encumbrance",
					"obligations",
					"ownershipDate",
					"terminationDate",
					"comment",
					"share",
					"annualTax",
					"totalArea",
					"registrationDocuments",
					"documentsCertifyingRights",
					"otherDocuments"
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
			persistent.setOwnershipForm(right.getOwnershipForm() != null ? landOwnershipFormBean.load(right.getOwnershipForm().getId()) : null);
			persistent.setRightKind(right.getRightKind() != null ? landRightKindBean.load(right.getRightKind().getId()) : null);
			persistent.setRightOwner(right.getRightOwner() != null ? personBean.load(right.getRightOwner().getId()) : null);
			landRightBean.save(persistent);
		}
		List<LandRight> toBeRemoved = persistentMap.entrySet().stream().filter(entry -> !newIds.contains(entry.getKey())).map(Map.Entry::getValue).collect(Collectors.toList());
		for (LandRight entity : toBeRemoved) {
			landRightBean.remove(entity);
			persistentList.remove(entity);
		}
		return !toBeRemoved.isEmpty() || oldPersistentListSize != persistentList.size();
	}

}
