package ru.sovzond.mgis2.property.dao.oks.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.property.dao.oks.IConstructiveElementTypeDao;
import ru.sovzond.mgis2.property.model.oks.constructive_elements.ConstructiveElementType;

/**
 * Created by Alexander Arakelyan on 13/11/15.
 *
 */
@Repository
public class ConstructiveElementTypeDao extends CRUDDaoBase<ConstructiveElementType> implements IConstructiveElementTypeDao {
}
