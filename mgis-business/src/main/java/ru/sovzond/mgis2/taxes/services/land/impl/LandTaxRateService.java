package ru.sovzond.mgis2.taxes.services.land.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.taxes.dao.land.ILandTaxRateDao;
import ru.sovzond.mgis2.taxes.model.land.LandTaxRate;
import ru.sovzond.mgis2.taxes.services.land.ILandTaxRateService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class LandTaxRateService extends CRUDBeanBase<LandTaxRate> implements ILandTaxRateService {

	@Autowired
	private ILandTaxRateDao dao;

	@Override
	protected IPageableDAOBase<LandTaxRate> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<LandTaxRate> getIIdentifiableDao() {
		return null;
	}

}

