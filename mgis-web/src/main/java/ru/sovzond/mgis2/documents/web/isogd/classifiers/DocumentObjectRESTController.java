package ru.sovzond.mgis2.documents.web.isogd.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentObject;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentSubObject;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentClassService;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentObjectService;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentSubObjectService;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@RestController
@RequestMapping("/isogd/classifiers/documents/objects")
public class DocumentObjectRESTController implements Serializable {

	@Autowired
	private IIsogdDocumentClassService documentClassService;

	@Autowired
	private IIsogdDocumentObjectService documentObjectService;

	@Autowired
	private IIsogdDocumentSubObjectService documentSubObjectService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<IsogdDocumentObject> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		PageableContainer<IsogdDocumentObject> pager = documentObjectService.list(first, max);
		return new PageableContainer<>(pager.getList().stream().map(IsogdDocumentObject::clone).collect(Collectors.toList()), pager.getTotalNumberOfItems(), first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@Transactional
	public IsogdDocumentObject save(@PathVariable("id") Long id, @RequestBody IsogdDocumentObject documentObject) {
		IsogdDocumentObject result;
		if (id == 0) {
			result = new IsogdDocumentObject();
		} else {
			result = documentObjectService.load(id);
		}
		result.setCode(documentObject.getCode());
		result.setName(documentObject.getName());
		result.setDocumentClass(documentClassService.load(documentObject.getDocumentClass().getId()));
		if (documentObject.getDocumentSubObjects().size() > 0) {
			result.setDocumentSubObjects(documentSubObjectService.load(documentObject.getDocumentSubObjects().stream().map(IsogdDocumentSubObject::getId).collect(Collectors.toList())));
		} else {
			result.getDocumentSubObjects().clear();
		}
		documentObjectService.save(result);
		return result.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public IsogdDocumentObject read(@PathVariable Long id) {
		return documentObjectService.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		documentObjectService.remove(documentObjectService.load(id));
	}
}
