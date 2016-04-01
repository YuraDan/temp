package ru.sovzond.mgis2.taxes.dao.land.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.taxes.dao.land.ILandTaxRateDao;
import ru.sovzond.mgis2.taxes.model.land.LandTaxRate;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Repository
public class LandTaxRateDao extends CRUDDaoBase<LandTaxRate> implements ILandTaxRateDao {
}
