package ru.sovzond.mgis2.documents.services.property;

import ru.sovzond.mgis2.documents.model.property.OtherPropertyDocument;
import ru.sovzond.mgis2.documents.services.common.IDocumentsService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IOtherPropertyDocumentService extends IDocumentsService<OtherPropertyDocument> {
	OtherPropertyDocument load(Long id);
}
