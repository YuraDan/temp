package ru.sovzond.mgis2.property.dao.oks.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.property.dao.oks.ITechnicalCharacteristicDao;
import ru.sovzond.mgis2.property.model.oks.characteristics.technical.TechnicalCharacteristic;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 *
 */
@Repository
public class TechnicalCharacteristicDao extends CRUDDaoBase<TechnicalCharacteristic> implements ITechnicalCharacteristicDao {
}
