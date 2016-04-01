package ru.sovzond.mgis2.documents.services.property;

import ru.sovzond.mgis2.documents.model.property.CertifyingDocument;
import ru.sovzond.mgis2.documents.services.common.IDocumentsService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface ICertifyingDocumentService extends IDocumentsService<CertifyingDocument> {
	CertifyingDocument load(Long id);
}
