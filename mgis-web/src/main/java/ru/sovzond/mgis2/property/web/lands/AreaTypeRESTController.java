package ru.sovzond.mgis2.property.web.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.property.model.lands.LandAreaType;
import ru.sovzond.mgis2.property.services.lands.ILandAreaTypeService;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by Alexander Arakelyan on 03.08.15.
 *
 */
@RestController
@RequestMapping("/lands/area_types")
@Scope("session")
public class AreaTypeRESTController implements Serializable {

	@Autowired
	private ILandAreaTypeService landAreaTypeService;


	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<LandAreaType> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "name") String orderBy) {
		return landAreaTypeService.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public LandAreaType read(@PathVariable Long id) {
		return landAreaTypeService.load(id).clone();
	}

}
