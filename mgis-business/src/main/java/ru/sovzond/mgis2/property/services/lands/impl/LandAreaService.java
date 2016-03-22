package ru.sovzond.mgis2.property.services.lands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.lands.ILandAreaDao;
import ru.sovzond.mgis2.property.model.lands.LandArea;
import ru.sovzond.mgis2.property.services.lands.ILandAreaService;

/**
 * Created by Alexander Arakelyan on 03.08.15.
 *
 *
 */
@Service
public class LandAreaService extends CRUDBeanBase<LandArea> implements ILandAreaService {

	@Autowired
	private ILandAreaDao dao;

	@Override
	protected IPageableDAOBase<LandArea> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandArea> getIIdentifiableDao() {
		return dao;
	}
}
