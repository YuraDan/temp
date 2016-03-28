package ru.sovzond.mgis2.taxes.services.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.taxes.dao.common.ITaxPeriodicPayerPaymentDetailsDao;
import ru.sovzond.mgis2.taxes.model.common.TaxPeriodicPayerPaymentDetails;
import ru.sovzond.mgis2.taxes.services.common.ITaxPeriodicPayerPaymentDetailsService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class TaxPeriodicPayerPaymentDetailsService
		extends CRUDBeanBase<TaxPeriodicPayerPaymentDetails>
		implements ITaxPeriodicPayerPaymentDetailsService {

	@Autowired
	private ITaxPeriodicPayerPaymentDetailsDao dao;

	@Override
	protected IPageableDAOBase<TaxPeriodicPayerPaymentDetails> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<TaxPeriodicPayerPaymentDetails> getIIdentifiableDao() {
		return null;
	}

}
