package ru.sovzond.mgis2.property.services.oks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.property.dao.oks.EconomicCharacteristicDao;
import ru.sovzond.mgis2.property.model.oks.characteristics.economical.EconomicCharacteristic;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 */
@Service
public class EconomicCharacteristicBean extends CRUDBeanBase<EconomicCharacteristic> {

	@Autowired
	private EconomicCharacteristicDao dao;

	@Override
	protected IPageableDAOBase<EconomicCharacteristic> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<EconomicCharacteristic> getIIdentifiableDao() {
		return dao;
	}
}
