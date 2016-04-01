package ru.sovzond.mgis2.taxes.services.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.taxes.dao.common.ITaxPeriodicPaymentDetailsDao;
import ru.sovzond.mgis2.taxes.model.common.TaxPeriodicPaymentDetails;
import ru.sovzond.mgis2.taxes.services.common.ITaxPeriodicPaymentDetailsService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class TaxPeriodicPaymentDetailsService
		extends CRUDBeanBase<TaxPeriodicPaymentDetails>
		implements ITaxPeriodicPaymentDetailsService {

	@Autowired
	private ITaxPeriodicPaymentDetailsDao dao;

	@Override
	protected IPageableDAOBase<TaxPeriodicPaymentDetails> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<TaxPeriodicPaymentDetails> getIIdentifiableDao() {
		return null;
	}

}
