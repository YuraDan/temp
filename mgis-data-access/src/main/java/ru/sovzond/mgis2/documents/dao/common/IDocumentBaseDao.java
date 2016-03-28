package ru.sovzond.mgis2.documents.dao.common;

import ru.sovzond.mgis2.dataaccess.base.IDAOBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Sergey Lvov on 28.03.16.
 *
 * Basic interface for documents DAO layer
 */
public interface IDocumentBaseDao<T> extends IPageableDAOBase<T>, IIdentifiableDao<T>, IDAOBase<T> {
}
