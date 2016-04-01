package ru.sovzond.mgis2.documents.dao.isogd.document;

import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.documents.dao.common.IDocumentBaseDao;
import ru.sovzond.mgis2.documents.model.isogd.section.Section;
import ru.sovzond.mgis2.documents.model.isogd.section.Volume;
import ru.sovzond.mgis2.documents.model.isogd.document.OksIsogdDocument;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentClass;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentSubObject;

import java.util.Date;
import java.util.List;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IOksIsogdDocumentDao extends IDocumentBaseDao<OksIsogdDocument> {
	OksIsogdDocument findById(Long id);
	List<IsogdDocumentSubObject> listAvailableDocumentSubObjects(Volume volume);
	IsogdDocumentClass readDocumentClassByVolume(Volume volume);
	PagerBuilderCriteria<OksIsogdDocument> createFilter(Volume volume, String order, int first, int max);
	PagerBuilderCriteria<OksIsogdDocument> createSearchDocumentFilter(Section section,
																	   String documentName,
																	   Date documentDate,
																	   Date documentDateFrom,
																	   Date documentDateTill,
																	   String documentNumber,
																	   String orderBy,
																	   int first,
																	   int max);
}
