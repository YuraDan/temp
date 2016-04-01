package ru.sovzond.mgis2.documents.web.isogd;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentClass;
import ru.sovzond.mgis2.documents.model.isogd.section.Section;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentClassService;
import ru.sovzond.mgis2.documents.services.isogd.section.ISectionService;

import javax.transaction.Transactional;
import java.io.Serializable;

@RestController
@RequestMapping("/isogd/sections")
@Scope("session")
public class SectionRESTController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4574289150137039686L;

	@Autowired
	private ISectionService sectionService;

	@Autowired
	private IIsogdDocumentClassService documentClassService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<Section> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "sortOrder") String orderBy, @RequestParam(defaultValue = "") String name) {
		return sectionService.pageSections(orderBy, first, max, name);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Section save(@PathVariable Long id, @RequestBody Section section) {
		Section section2 = (id == 0) ? new Section() : sectionService.readSection(id);
		BeanUtils.copyProperties(section, section2, "id", "documentClass", "books");
		IsogdDocumentClass documentClass = section.getDocumentClass();
		if (documentClass != null && documentClass.getId() > 0) {
			section2.setDocumentClass(documentClassService.load(documentClass.getId()));
		} else {
			section2.setDocumentClass(null);
		}
		sectionService.save(section2);
		return section2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public Section read(@PathVariable Long id) {
		return sectionService.readSection(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		sectionService.delete(sectionService.readSection(id));
	}

	@RequestMapping(value = "/swap-orders", method = RequestMethod.POST)
	@Transactional
	public void swapOrders(@RequestBody SwapIdPair pair) {
		SwapManager.byOrder(pair, (CRUDBeanBase) sectionService);
	}

}
