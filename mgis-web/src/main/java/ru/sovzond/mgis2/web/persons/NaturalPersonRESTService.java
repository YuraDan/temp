package ru.sovzond.mgis2.web.persons;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.address.AddressBean;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.services.isogd.business.IncludedDocumentsService;
import ru.sovzond.mgis2.documents.model.isogd.document.IncludedDocuments;
import ru.sovzond.mgis2.national_classifiers.OKVEDBean;
import ru.sovzond.mgis2.persons.NaturalPersonBean;
import ru.sovzond.mgis2.persons.NaturalPersonCertificateTypeBean;
import ru.sovzond.mgis2.registers.persons.NaturalPerson;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by Alexander Arakelyan on 30/08/15.
 *
 */
@RestController
@RequestMapping("/persons/natural-persons")
@Scope("session")
public class NaturalPersonRESTService implements Serializable {
	@Autowired
	private NaturalPersonBean naturalPersonBean;

	@Autowired
	private AddressBean addressBean;

	@Autowired
	private OKVEDBean okvedBean;

	@Autowired
	private NaturalPersonCertificateTypeBean naturalPersonCertificateTypeBean;

	@Autowired
	private IncludedDocumentsService includedDocumentsBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<NaturalPerson> list(@RequestParam(value = "name", defaultValue = "") String name, @RequestParam(value = "orderBy", defaultValue = "surname") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return naturalPersonBean.list(name, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public NaturalPerson save(@PathVariable("id") Long id, @RequestBody NaturalPerson naturalPerson) {
		NaturalPerson naturalPerson1;
		if (id == 0) {
			naturalPerson1 = new NaturalPerson();
		} else {
			naturalPerson1 = naturalPersonBean.load(id);
		}
		BeanUtils.copyProperties(naturalPerson, naturalPerson1, "id", "actualAddress", "legalAddress", "activityType", "documents");
		Long actualAddressId = naturalPerson.getActualAddress() != null ? naturalPerson.getActualAddress().getId() : null;
		if (actualAddressId != null && actualAddressId != 0) {
			naturalPerson1.setActualAddress(addressBean.load(actualAddressId));
		}
		Long legalAddressId = naturalPerson.getLegalAddress() != null ? naturalPerson.getLegalAddress().getId() : null;
		if (legalAddressId != null && legalAddressId != 0) {
			naturalPerson1.setLegalAddress(addressBean.load(legalAddressId));
		}
		Long activityTypeId = naturalPerson.getActivityType() != null ? naturalPerson.getActivityType().getId() : null;
		if (activityTypeId != null && activityTypeId != 0) {
			naturalPerson1.setActivityType(okvedBean.load(activityTypeId));
		}
		Long naturalPersonSertTypeId = naturalPerson.getCertificateType() != null ? naturalPerson.getCertificateType().getId() : null;
		if (naturalPersonSertTypeId != null && naturalPersonSertTypeId != 0) {
			naturalPerson1.setCertificateType(naturalPersonCertificateTypeBean.load(naturalPersonSertTypeId));
		}
		// IncludedDocuments
		IncludedDocuments documents = naturalPerson.getDocuments();
		IncludedDocuments documents1 = includedDocumentsBean.syncIncludedDocuments(naturalPerson1.getDocuments(), documents);
		if(documents1 != null) {
			naturalPerson1.setDocuments(documents1);
			includedDocumentsBean.save(documents1);
		}

		return naturalPersonBean.save(naturalPerson1).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public NaturalPerson read(@PathVariable("id") Long id) {
		return naturalPersonBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		naturalPersonBean.remove(naturalPersonBean.load(id));
	}

}
