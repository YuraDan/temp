package ru.sovzond.mgis2.taxes.services.property.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.taxes.dao.property.IPropertyTaxDao;
import ru.sovzond.mgis2.taxes.model.property.PropertyTax;
import ru.sovzond.mgis2.taxes.services.property.IPropertyTaxService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class PropertyTaxService extends CRUDBeanBase<PropertyTax> implements IPropertyTaxService {

	@Autowired
	private IPropertyTaxDao dao;

	@Override
	protected IPageableDAOBase<PropertyTax> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<PropertyTax> getIIdentifiableDao() {
		return null;
	}

}

