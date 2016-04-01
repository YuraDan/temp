package ru.sovzond.mgis2.documents.services.person;

import ru.sovzond.mgis2.documents.model.person.PersonDocument;
import ru.sovzond.mgis2.documents.services.common.IDocumentsService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IPersonDocumentService extends IDocumentsService<PersonDocument> {
	PersonDocument load(Long id);
}
