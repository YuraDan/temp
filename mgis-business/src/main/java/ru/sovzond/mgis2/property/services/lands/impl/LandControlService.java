package ru.sovzond.mgis2.property.services.lands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.lands.ILandControlDao;
import ru.sovzond.mgis2.property.model.lands.control.LandControl;
import ru.sovzond.mgis2.property.services.lands.ILandControlService;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 *
 */
@Service
public class LandControlService extends CRUDBeanBase<LandControl> implements ILandControlService {

	@Autowired
	private ILandControlDao dao;

	@Override
	protected IPageableDAOBase<LandControl> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandControl> getIIdentifiableDao() {
		return dao;
	}
}
