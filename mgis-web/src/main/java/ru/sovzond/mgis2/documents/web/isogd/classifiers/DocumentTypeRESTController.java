package ru.sovzond.mgis2.documents.web.isogd.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentType;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentTypeService;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@RestController
@RequestMapping("/isogd/classifiers/documents/types")
@Scope("session")
public class DocumentTypeRESTController implements Serializable {
	@Autowired
	private IIsogdDocumentTypeService documentTypeService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<IsogdDocumentType> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		PageableContainer<IsogdDocumentType> pager = documentTypeService.list(first, max);
		return new PageableContainer<>(pager.getList().stream().map(IsogdDocumentType::clone).collect(Collectors.toList()), pager.getTotalNumberOfItems(), first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public IsogdDocumentType save(@PathVariable("id") Long id, @RequestBody IsogdDocumentType documentType) {
		IsogdDocumentType documentType2;
		if (id == 0) {
			documentType2 = new IsogdDocumentType();
		} else {
			documentType2 = documentTypeService.load(id);
		}
		documentType2.setCode(documentType.getCode());
		documentType2.setName(documentType.getName());
		documentTypeService.save(documentType2);
		return documentType2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public IsogdDocumentType read(@PathVariable Long id) {
		return documentTypeService.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		documentTypeService.remove(documentTypeService.load(id));
	}
}
