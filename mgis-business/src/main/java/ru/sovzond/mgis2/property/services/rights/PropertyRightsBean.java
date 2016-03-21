package ru.sovzond.mgis2.property.services.rights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.PropertyRightsDao;
import ru.sovzond.mgis2.property.model.rights.PropertyRights;

/**
 * Created by Alexander Arakelyan on 30.07.15.
 *
 */
@Service
public class PropertyRightsBean extends CRUDBeanBase<PropertyRights> {

	@Autowired
	private PropertyRightsDao dao;

	@Override
	protected IPageableDAOBase<PropertyRights> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<PropertyRights> getIIdentifiableDao() {
		return dao;
	}
}
