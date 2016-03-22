package ru.sovzond.mgis2.property.dao.lands.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.property.dao.lands.ILandTypeOfEngineeringSupportAreaDao;
import ru.sovzond.mgis2.property.model.lands.characteristics.LandCharacteristicsEngineeringSupportArea;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 *
 */
@Repository
public class LandTypeOfEngineeringSupportAreaDao
		extends CRUDDaoBase<LandCharacteristicsEngineeringSupportArea>
		implements ILandTypeOfEngineeringSupportAreaDao {
}
