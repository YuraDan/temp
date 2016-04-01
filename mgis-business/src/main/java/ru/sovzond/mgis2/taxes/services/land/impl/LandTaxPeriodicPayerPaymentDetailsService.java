package ru.sovzond.mgis2.taxes.services.land.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.taxes.dao.land.ILandTaxPeriodicPayerPaymentDetailsDao;
import ru.sovzond.mgis2.taxes.model.land.LandTaxPeriodicPayerPaymentDetails;
import ru.sovzond.mgis2.taxes.services.land.ILandTaxPeriodicPayerPaymentDetailsService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class LandTaxPeriodicPayerPaymentDetailsService
		extends CRUDBeanBase<LandTaxPeriodicPayerPaymentDetails>
		implements ILandTaxPeriodicPayerPaymentDetailsService {

	@Autowired
	private ILandTaxPeriodicPayerPaymentDetailsDao dao;

	@Override
	protected IPageableDAOBase<LandTaxPeriodicPayerPaymentDetails> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<LandTaxPeriodicPayerPaymentDetails> getIIdentifiableDao() {
		return null;
	}

}

