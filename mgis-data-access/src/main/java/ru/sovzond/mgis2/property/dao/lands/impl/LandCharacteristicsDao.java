package ru.sovzond.mgis2.property.dao.lands.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.property.dao.lands.ILandCharacteristicsDao;
import ru.sovzond.mgis2.property.model.lands.characteristics.LandCharacteristics;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 *
 */
@Repository
public class LandCharacteristicsDao extends CRUDDaoBase<LandCharacteristics> implements ILandCharacteristicsDao {
}
