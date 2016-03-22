package ru.sovzond.mgis2.property.services.oks.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.oks.IConstructiveElementTypeDao;
import ru.sovzond.mgis2.property.model.oks.constructive_elements.ConstructiveElementType;
import ru.sovzond.mgis2.property.services.oks.IConstructiveElementTypeService;

/**
 * Created by Alexander Arakelyan on 13/11/15.
 *
 */
@Service
public class ConstructiveElementTypeService
		extends CRUDBeanBase<ConstructiveElementType>
		implements IConstructiveElementTypeService {

	@Autowired
	private IConstructiveElementTypeDao dao;

	@Override
	protected IPageableDAOBase<ConstructiveElementType> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<ConstructiveElementType> getIIdentifiableDao() {
		return dao;
	}
}
