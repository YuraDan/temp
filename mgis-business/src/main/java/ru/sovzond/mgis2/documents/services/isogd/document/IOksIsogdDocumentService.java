package ru.sovzond.mgis2.documents.services.isogd.document;

import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentClass;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentSubObject;
import ru.sovzond.mgis2.documents.model.isogd.document.OksIsogdDocument;
import ru.sovzond.mgis2.documents.model.isogd.section.Section;
import ru.sovzond.mgis2.documents.model.isogd.section.Volume;
import ru.sovzond.mgis2.documents.services.common.IDocumentsService;

import java.util.Date;
import java.util.List;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IOksIsogdDocumentService extends IDocumentsService<OksIsogdDocument> {
	OksIsogdDocument load(Long id);
	OksIsogdDocument save(OksIsogdDocument document);
	void delete(OksIsogdDocument document);
	PageableContainer<OksIsogdDocument> pageDocuments(Volume volume, String orderBy, int first, int max);
	List<IsogdDocumentSubObject> listDocumentSubObjectsByVolume(Volume volume);
	IsogdDocumentClass readDocumentClassByVolume(Volume volume);
	PageableContainer<OksIsogdDocument> find(Section section,
											  String documentName,
											  Date documentDate,
											  Date documentDateFrom,
											  Date documentDateTill,
											  String documentNumber,
											  String orderBy,
											  int first,
											  int max);

}
