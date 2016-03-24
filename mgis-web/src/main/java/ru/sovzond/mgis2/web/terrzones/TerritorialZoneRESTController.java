package ru.sovzond.mgis2.web.terrzones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.services.isogd.business.DocumentService;
import ru.sovzond.mgis2.national_classifiers.LandAllowedUsageBean;
import ru.sovzond.mgis2.national_classifiers.OKTMOBean;
import ru.sovzond.mgis2.property.model.lands.TerritorialZone;
import ru.sovzond.mgis2.property.services.lands.ITerritorialZoneService;
import ru.sovzond.mgis2.property.services.lands.ITerritorialZoneTypeService;
import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZoneType;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 29.07.15.
 *
 */
@RestController
@RequestMapping("/terr-zones/zones")
@Scope("session")
public class TerritorialZoneRESTController {
	@Autowired
	private ITerritorialZoneService territorialZoneService;

	@Autowired
	private ITerritorialZoneTypeService territorialZoneTypeService;

	@Autowired
	private OKTMOBean oktmoBean;

	@Autowired
	private LandAllowedUsageBean landAllowedUsageBean;

	@Autowired
	private DocumentService documentBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<TerritorialZone> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "name") String orderBy, @RequestParam(defaultValue = "") String name) {
		return territorialZoneService.list(orderBy, first, max, name);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public TerritorialZone read(@PathVariable Long id) {
		return territorialZoneService.load(id).clone();
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public TerritorialZone save(@PathVariable Long id, @RequestBody TerritorialZone zone) {
		TerritorialZone zone2;
		if (id == 0) {
			zone2 = new TerritorialZone();
		} else {
			zone2 = territorialZoneService.load(id);
		}
		zone2.setAdditionalDescription(zone.getAdditionalDescription());
		zone2.setAdministrativeTerritorialEntity(zone.getAdministrativeTerritorialEntity() != null ? oktmoBean.load(zone.getAdministrativeTerritorialEntity().getId()) : null);
		zone2.setCorrectionDate(zone.getCorrectionDate());
		zone2.setCreationDate(zone.getCreationDate());
		zone2.setIndex(zone.getIndex());
		zone2.setLiquidationDate(zone.getLiquidationDate());
		zone2.setName(zone.getName());
		zone2.setNumber(zone.getNumber());
		zone2.setPlacement(zone.getPlacement());
		TerritorialZoneType zoneType = zone.getZoneType();
		zone2.setZoneType(zoneType != null ? territorialZoneTypeService.load(zoneType.getId()) : null);
		zone2.setAccountNumber(zone.getAccountNumber());
		zone2.setAllowedUsageKind(zone.getAllowedUsageKind() != null ? landAllowedUsageBean.load(zone.getAllowedUsageKind().getId()) : null);
		zone2.setAllowedUsageKindAsText(zone.getAllowedUsageKindAsText());
		zone2.setAllowedUsageByDocument(zone.getAllowedUsageByDocument());
		zone2.setBasisDocument(zone.getBasisDocument() != null ? documentBean.load(zone.getBasisDocument().getId()) : null);
		zone2.setStateOnTheDate(zone.getStateOnTheDate());
		territorialZoneService.save(zone2);
		return zone2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		territorialZoneService.remove(territorialZoneService.load(id));
	}

}
