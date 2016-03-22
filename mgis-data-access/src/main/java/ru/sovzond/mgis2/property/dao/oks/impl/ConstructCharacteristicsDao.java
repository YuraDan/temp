package ru.sovzond.mgis2.property.dao.oks.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.property.dao.oks.IConstructCharacteristicsDao;
import ru.sovzond.mgis2.property.model.oks.characteristics.ConstructionCharacteristics;

/**
 * Created by Alexander Arakelyan on 14/11/15.
 *
 */
@Repository
public class ConstructCharacteristicsDao
		extends CRUDDaoBase<ConstructionCharacteristics>
		implements IConstructCharacteristicsDao {
}
