package ru.sovzond.mgis2.taxes.services.property.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.taxes.dao.property.IPropertyTaxPeriodicPayerPaymentDetailsDao;
import ru.sovzond.mgis2.taxes.model.property.PropertyTaxPeriodicPayerPaymentDetails;
import ru.sovzond.mgis2.taxes.services.property.IPropertyTaxPeriodicPayerPaymentDetailsService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class PropertyTaxPeriodicPayerPaymentDetailsService
		extends CRUDBeanBase<PropertyTaxPeriodicPayerPaymentDetails>
		implements IPropertyTaxPeriodicPayerPaymentDetailsService {

	@Autowired
	private IPropertyTaxPeriodicPayerPaymentDetailsDao dao;

	@Override
	protected IPageableDAOBase<PropertyTaxPeriodicPayerPaymentDetails> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<PropertyTaxPeriodicPayerPaymentDetails> getIIdentifiableDao() {
		return null;
	}

}

