package ru.sovzond.mgis2.property.services.lands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.lands.ILandAreaTypeDao;
import ru.sovzond.mgis2.property.model.lands.LandAreaType;
import ru.sovzond.mgis2.property.services.lands.ILandAreaTypeService;

/**
 * Created by Alexander Arakelyan on 03.08.15.
 *
 */
@Service
public class LandAreaTypeService extends CRUDBeanBase<LandAreaType> implements ILandAreaTypeService {

	@Autowired
	private ILandAreaTypeDao dao;

	@Override
	protected IPageableDAOBase<LandAreaType> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandAreaType> getIIdentifiableDao() {
		return dao;
	}
}
