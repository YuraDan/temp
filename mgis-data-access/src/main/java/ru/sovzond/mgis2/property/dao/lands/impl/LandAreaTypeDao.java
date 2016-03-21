package ru.sovzond.mgis2.property.dao.lands.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.property.dao.lands.ILandAreaTypeDao;
import ru.sovzond.mgis2.property.model.lands.LandAreaType;

/**
 * Created by Alexander Arakelyan on 03.08.15.
 *
 */
@Repository
public class LandAreaTypeDao extends CRUDDaoBase<LandAreaType> implements ILandAreaTypeDao {
}
