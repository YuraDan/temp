package ru.sovzond.mgis2.documents.web.isogd.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentClass;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentObject;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentClassService;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentObjectService;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@RestController
@RequestMapping("/isogd/classifiers/documents/classes")
public class DocumentClassRESTController implements Serializable {

	@Autowired
	private IIsogdDocumentClassService documentClassService;

	@Autowired
	private IIsogdDocumentObjectService documentObjectService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<IsogdDocumentClass> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		PageableContainer<IsogdDocumentClass> pager = documentClassService.list(first, max);
		return new PageableContainer<>(pager.getList().stream().map(IsogdDocumentClass::clone).collect(Collectors.toList()), pager.getTotalNumberOfItems(), first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public IsogdDocumentClass save(@PathVariable("id") Long id, @RequestBody IsogdDocumentClass documentClass) {
		IsogdDocumentClass result;
		if (id == 0) {
			result = new IsogdDocumentClass();
		} else {
			result = documentClassService.load(id);
		}
		result.setCode(documentClass.getCode());
		result.setName(documentClass.getName());
		result.setHasCommonPart(documentClass.isHasCommonPart());
		result.setHasSpecialPart(documentClass.isHasSpecialPart());
		if (documentClass.getDocumentObjects().size() > 0) {
			result.setDocumentObjects(documentObjectService.load(documentClass.getDocumentObjects().stream().map(IsogdDocumentObject::getId).collect(Collectors.toList())));
		} else {
			result.getDocumentObjects().clear();
		}
		documentClassService.save(result);
		return result.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public IsogdDocumentClass read(@PathVariable Long id) {
		return documentClassService.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		documentClassService.remove(documentClassService.load(id));
	}
}
