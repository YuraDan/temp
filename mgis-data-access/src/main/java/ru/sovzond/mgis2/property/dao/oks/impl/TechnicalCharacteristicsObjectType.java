package ru.sovzond.mgis2.property.dao.oks.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.property.dao.oks.ITechnicalCharacteristicsObjectType;
import ru.sovzond.mgis2.property.model.oks.characteristics.technical.TechnicalCharacteristicObjectType;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 *
 */
@Repository
public class TechnicalCharacteristicsObjectType
		extends CRUDDaoBase<TechnicalCharacteristicObjectType>
		implements ITechnicalCharacteristicsObjectType {
}
