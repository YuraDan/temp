package ru.sovzond.mgis2.property.dao.oks.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.property.dao.oks.IEconomicCharacteristicDao;
import ru.sovzond.mgis2.property.model.oks.characteristics.economical.EconomicCharacteristic;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 *
 */
@Repository
public class EconomicCharacteristicDao extends CRUDDaoBase<EconomicCharacteristic> implements IEconomicCharacteristicDao {
}
