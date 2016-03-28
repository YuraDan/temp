package ru.sovzond.mgis2.taxes.services.land.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.taxes.dao.land.ILandTaxDao;
import ru.sovzond.mgis2.taxes.model.land.LandTax;
import ru.sovzond.mgis2.taxes.services.land.ILandTaxService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class LandTaxService extends CRUDBeanBase<LandTax> implements ILandTaxService {
	@Autowired
	private ILandTaxDao dao;

	@Override
	protected IPageableDAOBase<LandTax> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<LandTax> getIIdentifiableDao() {
		return null;
	}

}

