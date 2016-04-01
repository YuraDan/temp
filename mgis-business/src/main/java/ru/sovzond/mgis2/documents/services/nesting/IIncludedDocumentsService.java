package ru.sovzond.mgis2.documents.services.nesting;

import ru.sovzond.mgis2.documents.model.nesting.IncludedDocuments;
import ru.sovzond.mgis2.documents.services.common.IDocumentsService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IIncludedDocumentsService extends IDocumentsService<IncludedDocuments> {
	IncludedDocuments load(Long id);
	IncludedDocuments syncIncludedDocuments(IncludedDocuments persistIncludedDocuments, IncludedDocuments newIncludedDocuments);

}
