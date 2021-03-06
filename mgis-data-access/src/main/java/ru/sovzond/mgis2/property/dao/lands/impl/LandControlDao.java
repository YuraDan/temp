package ru.sovzond.mgis2.property.dao.lands.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.property.dao.lands.ILandControlDao;
import ru.sovzond.mgis2.property.model.lands.control.LandControl;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@Repository
public class LandControlDao extends CRUDDaoBase<LandControl> implements ILandControlDao {
}
