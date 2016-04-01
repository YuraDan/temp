package ru.sovzond.mgis2.property.web.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.property.model.lands.control.LandControlAvailabilityOfViolations;
import ru.sovzond.mgis2.property.services.lands.ILandControlInspectionResultAvailabilityOfViolationsService;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 *
 */
@RestController
@RequestMapping("/lands/availability_of_violations")
@Scope("session")
public class InspectionResultAvailabilityOfViolationsRESTController implements Serializable {

	@Autowired
	private ILandControlInspectionResultAvailabilityOfViolationsService inspectionResultAvailabilityOfViolationsService;


	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<LandControlAvailabilityOfViolations> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "name") String orderBy) {
		return inspectionResultAvailabilityOfViolationsService.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public LandControlAvailabilityOfViolations read(@PathVariable Long id) {
		return inspectionResultAvailabilityOfViolationsService.load(id).clone();
	}

}
