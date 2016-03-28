package ru.sovzond.mgis2.documents.web.isogd.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentObject;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentSubObject;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentObjectService;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentSubObjectService;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@RestController
@RequestMapping("/isogd/classifiers/documents/subObjects")
public class DocumentSubObjectRESTController implements Serializable {

	@Autowired
	private IIsogdDocumentObjectService documentObjectService;

	@Autowired
	private IIsogdDocumentSubObjectService documentSubObjectService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<IsogdDocumentSubObject> list(@RequestParam Long objectId, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		IsogdDocumentObject documentObject = documentObjectService.load(objectId);
		PageableContainer<IsogdDocumentSubObject> pager = documentSubObjectService.list(documentObject, first, max);
		return new PageableContainer<>(pager.getList().stream().map(IsogdDocumentSubObject::clone).collect(Collectors.toList()), pager.getTotalNumberOfItems(), first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@Transactional
	public IsogdDocumentSubObject save(@PathVariable("id") Long id, @RequestBody IsogdDocumentSubObject documentSubObject) {
		IsogdDocumentSubObject result;
		if (id == 0) {
			result = new IsogdDocumentSubObject();
		} else {
			result = documentSubObjectService.load(id);
		}
		result.setCode(documentSubObject.getCode());
		result.setName(documentSubObject.getName());
		result.setDocumentObject(documentObjectService.load(documentSubObject.getDocumentObject().getId()));
		documentSubObjectService.save(result);
		return result.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public IsogdDocumentSubObject read(@PathVariable Long id) {
		return documentSubObjectService.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		documentSubObjectService.remove(documentSubObjectService.load(id));
	}
}
