package ru.sovzond.mgis2.documents.services.isogd.document;

import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocument;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentClass;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentSubObject;
import ru.sovzond.mgis2.documents.model.isogd.section.Section;
import ru.sovzond.mgis2.documents.model.isogd.section.Volume;
import ru.sovzond.mgis2.documents.services.common.IDocumentsService;

import java.util.Date;
import java.util.List;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IIsogdDocumentService extends IDocumentsService<IsogdDocument> {
	IsogdDocument load(Long id);
	IsogdDocument save(IsogdDocument document);
	void delete(IsogdDocument document);
	PageableContainer<IsogdDocument> pageDocuments(Volume volume, String orderBy, int first, int max);
	List<IsogdDocumentSubObject> listDocumentSubObjectsByVolume(Volume volume);
	IsogdDocumentClass readDocumentClassByVolume(Volume volume);
	PageableContainer<IsogdDocument> find(Section section,
										  String documentName,
										  Date documentDate,
										  Date documentDateFrom,
										  Date documentDateTill,
										  String documentNumber,
										  String orderBy,
										  int first,
										  int max);
}
