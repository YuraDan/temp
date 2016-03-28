package ru.sovzond.mgis2.taxes.services.land.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.taxes.dao.land.ILandTaxPeriodicPaymentDetailsDao;
import ru.sovzond.mgis2.taxes.model.land.LandTaxPeriodicPaymentDetails;
import ru.sovzond.mgis2.taxes.services.land.ILandTaxPeriodicPaymentDetailsService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class LandTaxPeriodicPaymentDetailsService
		extends CRUDBeanBase<LandTaxPeriodicPaymentDetails>
		implements ILandTaxPeriodicPaymentDetailsService {

	@Autowired
	private ILandTaxPeriodicPaymentDetailsDao dao;

	@Override
	protected IPageableDAOBase<LandTaxPeriodicPaymentDetails> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<LandTaxPeriodicPaymentDetails> getIIdentifiableDao() {
		return null;
	}

}

