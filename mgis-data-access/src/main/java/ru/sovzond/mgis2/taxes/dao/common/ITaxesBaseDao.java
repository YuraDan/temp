package ru.sovzond.mgis2.taxes.dao.common;

import ru.sovzond.mgis2.dataaccess.base.IDAOBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by donchenko-y on 3/22/16.
 */
public interface ITaxesBaseDao<T> extends IPageableDAOBase<T>, IIdentifiableDao<T>, IDAOBase<T> {
}
