package ru.sovzond.mgis2.property.services.rights.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.rights.IPropertyRightsDao;
import ru.sovzond.mgis2.property.model.rights.PropertyRights;
import ru.sovzond.mgis2.property.services.rights.IPropertyRightsService;

/**
 * Created by Alexander Arakelyan on 30.07.15.
 *
 */
@Service
public class PropertyRightsService extends CRUDBeanBase<PropertyRights> implements IPropertyRightsService {

	@Autowired
	private IPropertyRightsDao dao;

	@Override
	protected IPageableDAOBase<PropertyRights> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<PropertyRights> getIIdentifiableDao() {
		return dao;
	}
}
