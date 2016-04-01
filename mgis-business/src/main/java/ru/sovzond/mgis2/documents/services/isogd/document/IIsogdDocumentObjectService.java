package ru.sovzond.mgis2.documents.services.isogd.document;

import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentClass;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentObject;
import ru.sovzond.mgis2.documents.services.common.IDocumentsService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IIsogdDocumentObjectService extends IDocumentsService<IsogdDocumentObject> {
	PageableContainer<IsogdDocumentObject> list(IsogdDocumentClass documentClass, int first, int max);
}
