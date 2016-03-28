package ru.sovzond.mgis2.taxes.services.property.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.taxes.dao.property.IPropertyTaxPeriodicPaymentDetailsDao;
import ru.sovzond.mgis2.taxes.model.property.PropertyTaxPeriodicPaymentDetails;
import ru.sovzond.mgis2.taxes.services.property.IPropertyTaxPeriodicPaymentDetailsService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class PropertyTaxPeriodicPaymentDetailsService
		extends CRUDBeanBase<PropertyTaxPeriodicPaymentDetails> implements IPropertyTaxPeriodicPaymentDetailsService {

	@Autowired
	private IPropertyTaxPeriodicPaymentDetailsDao dao;

	@Override
	protected IPageableDAOBase<PropertyTaxPeriodicPaymentDetails> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<PropertyTaxPeriodicPaymentDetails> getIIdentifiableDao() {
		return null;
	}

}

