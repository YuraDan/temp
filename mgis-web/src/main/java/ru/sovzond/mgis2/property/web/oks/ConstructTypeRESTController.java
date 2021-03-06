package ru.sovzond.mgis2.property.web.oks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.property.model.oks.ConstructionType;
import ru.sovzond.mgis2.property.services.oks.IConstructTypeService;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 11.11.15.
 *
 */
@RestController
@RequestMapping("/oks/construct-types")
@Scope("session")
public class ConstructTypeRESTController {

	@Autowired
	private IConstructTypeService constructTypeService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<ConstructionType> list(@RequestParam(defaultValue = "name") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return constructTypeService.list(orderBy, first, max);
	}
}
