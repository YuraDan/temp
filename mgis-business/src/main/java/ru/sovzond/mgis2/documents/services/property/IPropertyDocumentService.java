package ru.sovzond.mgis2.documents.services.property;

import ru.sovzond.mgis2.documents.model.property.PropertyDocument;
import ru.sovzond.mgis2.documents.services.common.IDocumentsService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IPropertyDocumentService extends IDocumentsService<PropertyDocument> {
	PropertyDocument load(Long id);
}
