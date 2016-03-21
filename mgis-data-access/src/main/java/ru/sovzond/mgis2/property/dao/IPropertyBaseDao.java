package ru.sovzond.mgis2.property.dao;

import ru.sovzond.mgis2.dataaccess.base.IDAOBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Sergey Lvov on 21.03.16.
 *
 */
public interface IPropertyBaseDao<T> extends IPageableDAOBase<T>, IIdentifiableDao<T>, IDAOBase<T> {
}
