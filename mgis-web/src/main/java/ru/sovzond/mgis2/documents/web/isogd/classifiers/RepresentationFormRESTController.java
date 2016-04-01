package ru.sovzond.mgis2.documents.web.isogd.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.isogd.document.representation.RepresentationForm;
import ru.sovzond.mgis2.documents.model.isogd.document.representation.RepresentationFormat;
import ru.sovzond.mgis2.documents.services.isogd.document.representation.IRepresentationFormService;
import ru.sovzond.mgis2.documents.services.isogd.document.representation.IRepresentationFormatService;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@RestController
@RequestMapping("/isogd/classifiers/documents/representations/forms")
@Scope("session")
public class RepresentationFormRESTController implements Serializable {

	@Autowired
	private IRepresentationFormService representationFormService;

	@Autowired
	private IRepresentationFormatService representationFormatService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<RepresentationForm> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "code") String orderBy) {
		PageableContainer<RepresentationForm> pager = representationFormService.list(orderBy, first, max);
		return new PageableContainer<>(pager.getList().stream().map(RepresentationForm::clone).collect(Collectors.toList()), pager.getTotalNumberOfItems(), first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public RepresentationForm save(@PathVariable("id") Long id, @RequestBody RepresentationForm representationForm) {
		RepresentationForm representationForm2;
		if (id == 0) {
			representationForm2 = new RepresentationForm();
		} else {
			representationForm2 = representationFormService.load(id);
		}
		representationForm2.setCode(representationForm.getCode());
		representationForm2.setName(representationForm.getName());
		if (representationForm.getRepresentationFormats().size() > 0) {
			representationForm2.setRepresentationFormats(representationFormatService.load(representationForm.getRepresentationFormats().stream().map(RepresentationFormat::getId).collect(Collectors.toList())));
		} else {
			representationForm2.getRepresentationFormats().clear();
		}
		representationFormService.save(representationForm2);
		return representationForm2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public RepresentationForm read(@PathVariable Long id) {
		return representationFormService.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		representationFormService.remove(representationFormService.load(id));
	}
}
