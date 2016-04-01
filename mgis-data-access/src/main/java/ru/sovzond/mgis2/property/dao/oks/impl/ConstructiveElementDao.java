package ru.sovzond.mgis2.property.dao.oks.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.property.dao.oks.IConstructiveElementDao;
import ru.sovzond.mgis2.property.model.oks.constructive_elements.ConstructiveElement;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 *
 */
@Repository
public class ConstructiveElementDao extends CRUDDaoBase<ConstructiveElement> implements IConstructiveElementDao {
}
