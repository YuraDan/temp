package ru.sovzond.mgis2.documents.services.property;

import ru.sovzond.mgis2.documents.model.property.ConstitutiveDocument;
import ru.sovzond.mgis2.documents.services.common.IDocumentsService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IConstitutiveDocumentService extends IDocumentsService<ConstitutiveDocument> {
	ConstitutiveDocument load(Long id);
}
