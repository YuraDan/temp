package ru.sovzond.mgis2.documents.web.isogd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocument;
import ru.sovzond.mgis2.documents.model.isogd.section.Section;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentService;
import ru.sovzond.mgis2.documents.services.isogd.section.ISectionService;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Alexander Arakelyan on 30.09.15.
 */
@RestController
@RequestMapping("/isogd/search")
@Scope("session")
public class DocumentSearchRESTController implements Serializable {

	@Autowired
	private ISectionService sectionService;

	@Autowired
	private IIsogdDocumentService isogdDocumentService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<IsogdDocument> list(@RequestParam(value = "sectionId", required = false) Long sectionId,
											@RequestParam(value = "docName", required = false) String documentName,
											@RequestParam(value = "docDate", required = false) Date documentDate,
											@RequestParam(value = "docDateFrom", required = false) Date documentDateFrom,
											@RequestParam(value = "docDateTill", required = false) Date documentDateTill,
											@RequestParam(value = "docNumber", required = false) String documentNumber,
											@RequestParam(defaultValue = "name") String orderBy,
												 @RequestParam(defaultValue = "0") int first,
												 @RequestParam(defaultValue = "0") int max
	) {
		Section section = null;
		if (sectionId != null && sectionId != 0) {
			section = sectionService.load(sectionId);
			if (section == null) {
				throw new IllegalArgumentException("NO_SECTION_FOUND");
			}
		}
		return isogdDocumentService.find(section, documentName, documentDate, documentDateFrom, documentDateTill, documentNumber, orderBy, first, max);
	}
}
