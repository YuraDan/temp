package ru.sovzond.mgis2.property.web.oks;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.property.model.oks.constructive_elements.ConstructiveElementType;
import ru.sovzond.mgis2.property.services.oks.IConstructiveElementTypeService;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 05.11.15.
 *
 */
@RestController
@RequestMapping("/oks/constructive-element-types")
@Scope("session")
public class ConstructiveElementTypeRESTController {

	@Autowired
	private IConstructiveElementTypeService constructiveElementTypeService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<ConstructiveElementType> list(@RequestParam(value = "orderBy", defaultValue = "id DESC") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return constructiveElementTypeService.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public ConstructiveElementType save(@PathVariable Long id, @RequestBody ConstructiveElementType constructiveElementType) {
		ConstructiveElementType constructiveElementType2;
		if (id == 0) {
			constructiveElementType2 = new ConstructiveElementType();
		} else {
			constructiveElementType2 = constructiveElementTypeService.load(id);
		}
		BeanUtils.copyProperties(constructiveElementType, constructiveElementType2, "id");
		constructiveElementTypeService.save(constructiveElementType2);
		return constructiveElementType2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public ConstructiveElementType read(@PathVariable Long id) {
		return constructiveElementTypeService.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		constructiveElementTypeService.remove(constructiveElementTypeService.load(id));
	}

}
