package ru.sovzond.mgis2.documents.dao.isogd.document;

import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.documents.dao.common.IDocumentBaseDao;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentClass;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentObject;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IIsogdDocumentObjectDao extends IDocumentBaseDao<IsogdDocumentObject> {
	PagerBuilderCriteria<IsogdDocumentObject> createFilter(IsogdDocumentClass documentClass, int first, int max);
}
