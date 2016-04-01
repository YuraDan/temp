package ru.sovzond.mgis2.documents.dao.isogd.document;

import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.documents.dao.common.IDocumentBaseDao;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentObject;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentSubObject;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IIsogdDocumentSubObjectDao extends IDocumentBaseDao<IsogdDocumentSubObject> {
	PagerBuilderCriteria<IsogdDocumentSubObject> createFilter(IsogdDocumentObject documentObject, int first, int max);
}
