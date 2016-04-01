package ru.sovzond.mgis2.property.services.oks.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.oks.IEconomicCharacteristicDao;
import ru.sovzond.mgis2.property.model.oks.characteristics.economical.EconomicCharacteristic;
import ru.sovzond.mgis2.property.services.oks.IEconomicCharacteristicService;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 *
 */
@Service
public class EconomicCharacteristicService
		extends CRUDBeanBase<EconomicCharacteristic>
		implements IEconomicCharacteristicService {

	@Autowired
	private IEconomicCharacteristicDao dao;

	@Override
	protected IPageableDAOBase<EconomicCharacteristic> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<EconomicCharacteristic> getIIdentifiableDao() {
		return dao;
	}
}
