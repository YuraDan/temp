package ru.sovzond.mgis2.documents.web.isogd.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
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
@RequestMapping("/isogd/classifiers/documents/representations/formats")
@Scope("session")
public class RepresentationFormatRESTController implements Serializable {
	@Autowired
	private IRepresentationFormService representationFormService;

	@Autowired
	private IRepresentationFormatService representationFormatService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<RepresentationFormat> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "code") String orderBy) {
		PageableContainer<RepresentationFormat> pager = representationFormatService.list(orderBy, first, max);
		return new PageableContainer<>(pager.getList().stream().map(RepresentationFormat::clone).collect(Collectors.toList()), pager.getTotalNumberOfItems(), first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public RepresentationFormat save(@PathVariable("id") Long id, @RequestBody RepresentationFormat representationFormat) {
		RepresentationFormat representationFormat2;
		if (id == 0) {
			representationFormat2 = new RepresentationFormat();
			representationFormat2.setRepresentationForm(representationFormService.load(representationFormat.getRepresentationForm().getId()));
		} else {
			representationFormat2 = representationFormatService.load(id);
		}
		representationFormat2.setCode(representationFormat.getCode());
		representationFormat2.setName(representationFormat.getName());
		representationFormat2.getFormats().clear();
		representationFormat2.getFormats().addAll(representationFormat.getFormats());
		representationFormatService.save(representationFormat2);
		return representationFormat2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public RepresentationFormat read(@PathVariable Long id) {
		return representationFormatService.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		representationFormatService.remove(representationFormatService.load(id));
	}
}
