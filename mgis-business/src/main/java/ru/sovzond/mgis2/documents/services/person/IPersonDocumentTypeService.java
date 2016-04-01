package ru.sovzond.mgis2.documents.services.person;

import ru.sovzond.mgis2.documents.model.person.PersonDocumentType;
import ru.sovzond.mgis2.documents.services.common.IDocumentsService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IPersonDocumentTypeService extends IDocumentsService<PersonDocumentType> {
	PersonDocumentType load(Long id);
}
